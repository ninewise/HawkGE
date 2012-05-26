
package hawkge.event;

import hawkge.storage.User;
import java.util.ArrayList;
import java.util.Collection;


public abstract class NetworkEvent extends Event<Void> {

    private Collection<User> destinations;
    private boolean local;
    
    /**
     * Local is set to true;
     * @param destinations is a Collection of IPAdress destinations
     */
    public NetworkEvent(Collection<User> destinations) {
        this.destinations = destinations;
        setLocal();
    }
    
    public NetworkEvent(User user) {
        destinations = new ArrayList<User>();
        destinations.add(user);
        setLocal();
    }

    /**
     * Returns a list with IPAdress destinations
     * @return the list with IPAdress destinations
     */
    public Collection<User> getDestinations() {
        return this.destinations;
    }

    /**
     * Returns whether this event was thrown locally or not.
     * @return Whether this event is local or not.
     */
    public boolean isLocal() {
        return this.local;
    }
    
    /**
     * Sets this NetworkEvent to not local.
     */
    public void clearLocal() {
        this.local = false;
    }

    private void setLocal() {
        local = true;
    }
}