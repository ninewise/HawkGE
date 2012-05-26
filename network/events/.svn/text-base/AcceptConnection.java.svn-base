package hawkge.network.events;

import hawkge.event.Event;
import hawkge.storage.User;

/**
 * Class to determin if the connection is accept or not
 * @create on May 5, 2012
 * @author jorisvi
 */
public class AcceptConnection extends Event<Void> {

    private boolean accept;
    private User user;
    private User server;

    /**
     * 
     * @param accept a Boolean object
     * @param user a User object
     */
    public AcceptConnection(boolean accept, User user, User server) {
        super();
        this.accept = accept;
        this.user = user;
        this.server = server;
    }

    /**
     * 
     * @return a boolean, true when the connection is accept
     * false when not.
     */
    public boolean isAccept() {
        return accept;
    }

    /**
     * 
     * @return a User that is the information of the server
     */
    public User getUser() {
        return user;
    }
    
    /**
     * 
     * @return a User that is the information of the client
     */
    public User getServer() {
        return server;
    }
}