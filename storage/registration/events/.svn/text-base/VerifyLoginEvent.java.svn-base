package hawkge.storage.registration.events;

import hawkge.event.Callable;
import hawkge.event.Event;
import hawkge.storage.User;

/**
 * An event to verify and retrieve the user described by this event.
 * The callback will be called with the retrieved user, or null in case the user
 * does not exists.
 *
 * requires: A running hawkge.storage.Registrator
 * @create on Apr 26, 2012
 * @author jorisvi
 */
public class VerifyLoginEvent extends Event<User> {
    
    private String name;
    private String password;

    public VerifyLoginEvent(Callable<User> callable, String name, String password) {
        super(callable);
        this.name = name;
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }
}