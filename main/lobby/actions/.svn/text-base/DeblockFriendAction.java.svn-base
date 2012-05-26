package hawkge.main.lobby.actions;

import hawkge.event.EventQueue;
import hawkge.main.lobby.list.UserListComponent;
import hawkge.main.lobby.list.UserState;
import hawkge.storage.User;
import hawkge.storage.usermanager.blocking.DeblockEvent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JList;

/**
 * @create on May 15, 2012
 * @author jorisvi
 */
public class DeblockFriendAction extends SelectedUserAction {

    private JList list;
    
    public DeblockFriendAction(JList list) {
        super("Deblock", list);
        this.list = list;
    }

    @Override
    public UserState actionPerformed(User u) {
        EventQueue.queue(new DeblockEvent(u));
        list.clearSelection();
        return UserState.OFFLINE;
    }

    @Override
    public void userSelected(UserState s) {
        setEnabled(UserState.BLOCKED.equals(s));
    }

}