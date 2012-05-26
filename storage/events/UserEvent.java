package hawkge.storage.events;

import hawkge.event.Callable;
import hawkge.event.Event;
import hawkge.storage.User;

/**
 * An event to get the user information (intern program)
 * Works with callable objects
 * @author felix
 */
public class UserEvent extends Event<User> {

    public UserEvent(Callable<User> callback) {
        super(callback);
    }
}
