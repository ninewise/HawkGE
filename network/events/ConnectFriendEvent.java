package hawkge.network.events;

import hawkge.event.Event;
import hawkge.storage.User;

/**
 * Class that network put on queue when friend connect or disconnect
 * @create on Apr 17, 2012
 * @author jorisvi
 */
public class ConnectFriendEvent extends Event<Void> {
    
    private User user;
    private boolean online;
    
    /**
     * 
     * @param user: event over this friend
     * @param online: set boolean for online
     */
    public ConnectFriendEvent(User user, boolean online) {
        super();
        this.user = user;
        this.online = online;
    }
    /**
     * Returns the state of a friend is online
     * @return boolean if friend is online
     */
    public boolean isOnline() {
        return online;
    }
    
    /**
     * Return a user which contains userinfo
     * @return User
     */
    public User getUser() {
        return user;
    }
}