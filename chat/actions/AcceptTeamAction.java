/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.actions;

import hawkge.chat.event.AddToTeamChatRequest;
import hawkge.chat.gui.AddPlayerToTeamChatFrame;
import hawkge.chat.model.TeamChatModel;
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
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Sels
 */
public class AcceptTeamAction extends AbstractAction implements ChangeListener {

    private JList list;
    private AddPlayerToTeamChatFrame frame;
    private TeamChatModel model;
    private GameModel gmodel;
    private Collection<User> onlinefriends;
    private final Object wait;
    private boolean waitCondition;
    private JCheckBox box;
    /*
     * Klasse die zorgt voor het afhandelen als de gebruiker op OK drukt in het
     * addFriendFrame
     */

    public AcceptTeamAction(JList list, AddPlayerToTeamChatFrame frame, TeamChatModel model, GameModel gmodel, JCheckBox box) {
        super("Invite");
        wait = new Object();
        this.frame = frame;
        this.list = list;
        this.model = model;
        this.gmodel = gmodel;
        this.box = box;
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

    /**
     * Wijzig de lijst met online friends voor een user.
     *
     * @param users De lijst met friends die ingesteld dient te worden. *
     */
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
                if (!model.getUsersInChat().contains(user)) {
                    if (gmodel == null) {
                        if (model.isUserInBlueTeam()) {
                            if (box.isSelected()) {
                                EventQueue.queue(new AddToTeamChatRequest(model.getOwnUser(), user, model.getUsersInChat(), model.getId(), true));
                            } else {
                                EventQueue.queue(new AddToTeamChatRequest(model.getOwnUser(), user, model.getUsersInChat(), model.getId(), false));
                            }
                        }
                        else {
                           if (box.isSelected()) {
                                EventQueue.queue(new AddToTeamChatRequest(model.getOwnUser(), user, model.getUsersInChat(), model.getId(), false));
                            } else {
                                EventQueue.queue(new AddToTeamChatRequest(model.getOwnUser(), user, model.getUsersInChat(), model.getId(), true));
                            } 
                        }
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
