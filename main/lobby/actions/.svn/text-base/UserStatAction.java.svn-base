/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.main.lobby.actions;

import hawkge.event.EventQueue;
import hawkge.main.lobby.list.UserState;
import hawkge.storage.User;
import hawkge.storage.userstats.events.ShowUserStatEvent;
import javax.swing.JList;

/**
 *
 * @author felix
 */
public class UserStatAction extends SelectedUserAction {

    public UserStatAction(JList list) {
        super("View Stats...", list);
    }

    @Override
    public UserState actionPerformed(User u) {
        EventQueue.queue(new ShowUserStatEvent(u));
        return UserState.OFFLINE;
    }

    @Override
    public void userSelected(UserState s) {
        setEnabled(s != null);
    }

}
