package hawkge.network.events;

import hawkge.event.Event;
import hawkge.storage.User;

/**
 * @create on May 21, 2012
 * @author jorisvi
 */
public class UpdateUserEvent extends Event {
    
    private User user;
    
    public UpdateUserEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}