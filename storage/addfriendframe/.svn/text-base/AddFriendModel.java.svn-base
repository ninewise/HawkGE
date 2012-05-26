package hawkge.storage.addfriendframe;

import hawkge.Model;
import hawkge.event.EventQueue;
import hawkge.network.IPAddress;
import hawkge.storage.User;
import hawkge.storage.usermanager.UserManager;
import hawkge.storage.usermanager.befriending.FriendAddedEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @create on May 2, 2012
 * @author jorisvi
 */
public class AddFriendModel extends Model {
    
    private AddFriendFrame addFriendFrame;
    private UserManager userManager;
    
    public AddFriendModel(AddFriendFrame addFriendFrame, UserManager userManager) {
        super();
        this.addFriendFrame = addFriendFrame;
        this.userManager = userManager;
    }
    
    public void addFriend(String name, IPAddress ip) {
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(addFriendFrame, "Fill in a username",
                    "NameField empty", JOptionPane.ERROR_MESSAGE);
        } else if (ip == null) {
            JOptionPane.showMessageDialog(addFriendFrame, "Fill in a ip",
                    "IPField empty", JOptionPane.ERROR_MESSAGE);
        } else {
            User user = new User(ip, name);
            if(!userManager.addFriend(user)) {
                JOptionPane.showMessageDialog(addFriendFrame, "You're already"
                    + "befriended with that user.", "Duplicates not allowed",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(addFriendFrame, "Friend " +
                    name + " succesfully added.", "Friend added",
                    JOptionPane.INFORMATION_MESSAGE);
                addFriendFrame.close();
                EventQueue.queue(new FriendAddedEvent(user));
            }
        }
    }
}