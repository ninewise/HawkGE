package hawkge.main.lobby.actions;

import hawkge.event.EventQueue;
import hawkge.main.lobby.list.UserListComponent;
import hawkge.main.lobby.list.UserState;
import hawkge.storage.User;
import hawkge.storage.usermanager.befriending.RemoveFriendEvent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @create on Apr 30, 2012
 *
 * @author jorisvi
 */
public class RemoveFriendAction extends SelectedUserAction {

    private JList list;

    public RemoveFriendAction(JList list) {
        super("Remove", list);
        this.list = list;
    }

    @Override
    public UserState actionPerformed(User u) {
        EventQueue.queue(new RemoveFriendEvent(u));
        list.clearSelection();
        return null;
    }

    @Override
    public void userSelected(UserState s) {
        setEnabled(s != null && !UserState.BLOCKED.equals(s));
    }

}