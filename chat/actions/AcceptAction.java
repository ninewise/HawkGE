/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.actions;

import hawkge.chat.event.AddToChatRequest;
import hawkge.chat.gui.AddFriendFrame;
import hawkge.chat.model.ChatModel;
import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.game.events.GameInvitationRequestEvent;
import hawkge.game.models.GameModel;
import hawkge.network.events.OnlineUserListEvent;
import hawkge.storage.User;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.AbstractAction;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Sels
 */
public class AcceptAction extends AbstractAction implements ChangeListener {

    private JList list;
    private AddFriendFrame frame;
    private ChatModel model;
    private GameModel gmodel;
    private Collection<User> onlinefriends;
    private final Object wait;
    private boolean waitCondition;
    /*
     * Klasse die zorgt voor het afhandelen als de gebruiker op OK drukt in het
     * addFriendFrame
     */

    public AcceptAction(JList list, AddFriendFrame frame, ChatModel model, GameModel gmodel) {
        super("Invite");
        wait = new Object();
        this.frame = frame;
        this.list = list;
        this.model = model;
        this.gmodel = gmodel;
        model.addChangeListener(this);
        checkEnable();
    }

    private void getOnlineFriends() {
        EventQueue.queue(new OnlineUserListEvent(new Callable<Collection<User>>() {

            public void call(Collection<User> users) {
                initList(users);
                synchronized (wait) {
                    setCondition(false);
                    wait.notifyAll();
                }
            }
        }));
    }

    /** Wijzig de lijst met online friends voor een user. 
    @param users De lijst met friends die ingesteld dient te worden. **/
    private void initList(Collection<User> users) {
        this.onlinefriends = users;
    }

    public void actionPerformed(ActionEvent ae) {
        if (list.getSelectedIndex() != -1) {
            ArrayList<User> invite = new ArrayList<User>();
            for (int i = 0; i < list.getSelectedValues().length; i++) {
                User user = (User) list.getSelectedValues()[i];
                invite.add(user);
            }

            for (User user : invite) {
                if (!model.getUserModel().containsUser(user)) {
                    if (gmodel == null) {
                        EventQueue.queue(new AddToChatRequest(model.getOwnUser(), user, model.getUsersInChat(), model.getId()));
                    } else {
                        if (!gmodel.isInvited(user)) {
                            setCondition(true);
                            getOnlineFriends();
                            synchronized (wait) {
                                try {
                                    while (waitCondition) {
                                        wait.wait();
                                    }
                                } catch (InterruptedException ex) {
                                    System.out.println("Waiting interrupt: " + ex);
                                }
                            }
                            EventQueue.queue(new GameInvitationRequestEvent(user, gmodel.getGameSessionInfo()));
                            gmodel.increaseNumberOfPendingInvitations();
                            gmodel.addInvited(user);
                        } else {
                            JOptionPane.showMessageDialog(frame, "User is already invited. \nPlease wait for his response.", "Invite user to game error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    if (gmodel == null) {
                        JOptionPane.showMessageDialog(frame, "User is already in chat", "Invite user to chat error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "User is already in game", "Invite user to game error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            frame.dispose();
        }
    }

    private synchronized void setCondition(Boolean condition) {
        this.waitCondition = condition;
    }

    private void checkEnable() {
        if (gmodel != null) {
            setEnabled(gmodel.getCurrentNumberOfPlayers() < gmodel.getRequiredNumberOfPlayers());
        }
    }

    public void stateChanged(ChangeEvent ce) {
        checkEnable();
    }
}
