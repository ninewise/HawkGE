package hawkge.network.events;

import hawkge.event.Event;
import hawkge.storage.User;

/**
 * Class to determin that the server of the user is the wright juister
 * @create on May 4, 2012
 * @author jorisvi
 */
public class RequestConnectionEvent extends Event<Void> {
    
    private User server;
    private User client;

    /**
     * 
     * @param server a Server object
     * @param client a Client object
     */
    public RequestConnectionEvent(User server, User client) {
        this.server = server;
        this.client = client;
    }

    /**
     * 
     * @return User that's client of the connection
     */
    public User getClient() {
        return client;
    }

    /**
     * 
     * @return User that's server of the connection
     */
    public User getServer() {
        return server;
    }
}