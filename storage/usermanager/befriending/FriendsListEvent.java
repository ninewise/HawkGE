package hawkge.storage.usermanager.befriending;

import hawkge.event.Callable;
import hawkge.event.Event;
import hawkge.storage.User;
import java.util.Collection;

/**
 * Class to put on queue to receive a list off Users
 * Works with callable objects
 * @create on Apr 18, 2012
 * @author jorisvi
 */
public class FriendsListEvent extends Event<Collection<User>> {
    
    public FriendsListEvent(Callable<Collection<User>> callback) {
        super(callback);
    }
}