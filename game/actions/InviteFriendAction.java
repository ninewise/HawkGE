package hawkge.game.actions;

import hawkge.chat.actions.AddFriendAction;
import hawkge.chat.gui.AddFriendFrame;
import hawkge.chat.model.ChatModel;
import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.game.models.GameModel;
import hawkge.storage.User;
import hawkge.storage.events.UserEvent;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author michaelkint
 */
public class InviteFriendAction extends AddFriendAction {

    private GameModel gmodel;
    private User user;

    public InviteFriendAction(ChatModel model, GameModel gmodel) {
        super(model);
        this.gmodel = gmodel;
        getCurrentUser();
    }

    private void getCurrentUser() {
        EventQueue.queue(new UserEvent(new Callable<User>() {

            public void call(User param) {
                setCurrentUser(param);
            }
        }));
    }

    private void setCurrentUser(User user) {
        this.user = user;
    }

    private void showInvitationError(String message) {
        JOptionPane.showMessageDialog(null, message, "Invite user error", 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gmodel != null) {
            if (user.equals(gmodel.getHost())) {
                new AddFriendFrame(model, gmodel);
            } else {
                if (gmodel.isActive()) {
                    showInvitationError("You can't invite users once a game has started.");
                } else if (gmodel.isFinished()) {
                    showInvitationError("You can't invite users once a game has ended.");
                } else if (!user.equals(gmodel.getHost())) {
                    showInvitationError("Only the host can invite users.");
                }
            }
        }
    }
}
