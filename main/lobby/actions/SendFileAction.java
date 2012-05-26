package hawkge.main.lobby.actions;

import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.main.lobby.list.UserListComponent;
import hawkge.storage.User;
import hawkge.storage.gameloading.events.RequestGameEvent;
import hawkge.storage.events.UserEvent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JList;

/**
 * @create on May 14, 2012
 * @author jorisvi
 */
public class SendFileAction extends AbstractAction {
    
    private JList list;

    public SendFileAction(JList list) {
        super("Send file");
        this.list = list;
    }

    public void actionPerformed(ActionEvent e) {
        final UserListComponent listComponent = (UserListComponent) list.getSelectedValue();
        if (listComponent != null) {
            EventQueue.queue(new UserEvent(new Callable<User>() {
                public void call(User u) {
                    EventQueue.queue(new RequestGameEvent("game", u, listComponent.getUser()));
                }
            }));
        }
    }

}