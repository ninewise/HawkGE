/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.main.lobby.actions;

import hawkge.event.EventQueue;
import hawkge.main.lobby.list.UserState;
import hawkge.storage.User;
import hawkge.storage.usermanager.blocking.BlockEvent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JList;

/**
 *
 * @author felix
 */
public class BlockFriendAction extends SelectedUserAction {
    
    private JList list;

    public BlockFriendAction(JList list) {
        super("Block", list);
        this.list = list;
    }

    @Override
    public UserState actionPerformed(User u) {
        EventQueue.queue(new BlockEvent(u));
        list.clearSelection();
        return UserState.BLOCKED;
    }

    @Override
    public void userSelected(UserState s) {
        setEnabled(s != null && !UserState.BLOCKED.equals(s));
    }

}
