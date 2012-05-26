
package hawkge.storage.gameloading.events;

import hawkge.event.NetworkEvent;
import hawkge.storage.User;
import java.io.File;
import java.util.Collection;

/**
 *
 * @author felix
 */
public class RequestGameEvent extends NetworkEvent {

    private final String name;
    private final User requestor;
    private final User owner;

    /**
     * An event to ask the owner of the game with the name to sent it to the
     * local user, the requestor.
     * @param name The name of the game.
     * @param requestor The name of the local user.
     * @param owner Someone who owns the game.
     */
    public RequestGameEvent(String name, User requestor, User owner) {
        super(owner);
        this.requestor = requestor;
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public User getRequestor() {
        return requestor;
    }

    public User getOwner() {
        return owner;
    }

}
