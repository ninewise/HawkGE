
package hawkge.storage.usermanager;

import hawkge.event.EventQueue;
import hawkge.storage.Storable;
import hawkge.storage.StoreException;
import hawkge.storage.User;
import hawkge.storage.addfriendframe.AddFriendFrame;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.swing.SwingUtilities;
import org.jdom.Element;

public class UserManager extends Storable {

    private HashSet<User> friends;
    private HashSet<User> blocks;
    private User owner;

    public UserManager(User owner) {
        this.friends = new HashSet<User>();
        this.blocks = new HashSet<User>();
        this.owner = owner;
    }

    public synchronized boolean addFriend(User user) {
        if(user.equals(owner)) return false;
        return friends.add(user);
    }

    public synchronized boolean removeFriend(User user) {
        return friends.remove(user);
    }

    public synchronized boolean isFriend(User user) {
        return friends.contains(user);
    }

    public synchronized Collection<User> getFriends() {
        return this.friends;
    }

    public synchronized boolean addBlock(User user) {
        removeFriend(user);
        return blocks.add(user);
    }

    public synchronized boolean removeBlock(User user) {
        return blocks.remove(user);
    }

    public synchronized boolean isBlock(User user) {
        return blocks.contains(user);
    }

    public synchronized Collection<User> getBlocks() {
        return this.blocks;
    }

    public synchronized void showAddFriendDialog() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddFriendFrame(UserManager.this);
            }
        });
    }

    public synchronized File getLocation() {
        return new File(getStorageRoot(), "usermanager_" + owner.getName() + ".hgedata");
    }

    @Override
    public synchronized Element getXML() {
        Element userManager = new Element("usermanager");
        Element friendsElement = new Element("friends");
        Element blocksElement = new Element("blocks");
        for(User user : this.friends) friendsElement.addContent(user.getXML());
        for(User user : this.blocks) blocksElement.addContent(user.getXML());
        userManager.addContent(friendsElement);
        userManager.addContent(blocksElement);
        return userManager;
    }

    /**
     * Parses <code>element</code> to create a UserManager out of it.
     * @param element A Xml element representing a UserManager.
     * @return The UserManager represented by <code>element</code>
     * @throws StoreException If <code>element</code> does not represent a
     * UserManager.
     */
    public static UserManager readXML(Element element, User user) throws StoreException {
        if(element == null || ! "usermanager".equals(element.getName())) {
            throw new StoreException("Error parsing element as UserManager.");
        }
        UserManager userManager = new UserManager(user);
        List<Element> friends = element.getChild("friends").getChildren("user");
        for(Element e : friends) userManager.addFriend((User) User.readXML(e));
        List<Element> blocks = element.getChild("blocks").getChildren("user");
        for(Element e : blocks) userManager.addBlock((User) User.readXML(e));
        return userManager;
    }

    @Override
    public synchronized boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserManager other = (UserManager) obj;
        if (this.friends != other.friends && (this.friends == null || !this.friends.equals(other.friends))) {
            return false;
        }
        if (this.blocks != other.blocks && (this.blocks == null || !this.blocks.equals(other.blocks))) {
            return false;
        }
        if (this.owner != other.owner && (this.owner == null || !this.owner.equals(other.owner))) {
            return false;
        }
        return true;
    }

    @Override
    public synchronized int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.owner != null ? this.owner.hashCode() : 0);
        return hash;
    }

}
