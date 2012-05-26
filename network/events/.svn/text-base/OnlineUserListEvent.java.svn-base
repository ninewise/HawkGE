package hawkge.network.events;

import hawkge.event.Callable;
import hawkge.event.Event;
import hawkge.storage.User;
import java.util.Collection;

/**
 * Class to put on queue to receive online users
 * Works with callable objects
 * @create on Apr 21, 2012
 * @author jorisvi
 */
public class OnlineUserListEvent extends Event<Collection<User>> {

    /**
     * Returns a list of online users through a callback system
     * @param callback of Collections of user
     */
    public OnlineUserListEvent(Callable<Collection<User>> callback) {
        super(callback);
    }
}