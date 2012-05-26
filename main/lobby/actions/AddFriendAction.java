package hawkge.main.lobby.actions;

import hawkge.event.EventQueue;
import hawkge.storage.usermanager.befriending.AddFriendEvent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @create on Apr 30, 2012
 * @author jorisvi
 */
public class AddFriendAction extends AbstractAction {
    
    public AddFriendAction() {
        super("Add friend...");
    }

    /**
     * Throws an addFriendEvent on the queue
     * @param e an ActionEvent object
     */
    public void actionPerformed(ActionEvent e) {
        EventQueue.queue(new AddFriendEvent());
    }
}