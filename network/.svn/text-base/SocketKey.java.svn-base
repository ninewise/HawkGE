package hawkge.network;

import hawkge.storage.User;

/**
 * This represent the key of the hashmap that hold the sockets and outputstreams
 * @create on May 24, 2012
 * @author jorisvi
 */
public class SocketKey {

    private User client;
    private User server;
    
    public SocketKey(User client, User server) {
        this.client = client;
        this.server = server;
    }
    
    /**
     * Returns the client of the socketkey
     * @return a User object
     */
    public User getClient() {
        return client;
    }
    
    /**
     * Returns the client of the socketkey
     * @return a User object
     */
    public User getServer() {
        return server;
    }

    /**
     * Returns a boolean that represent if the object is equal or not.
     * @param obj an Object object
     * @return boolean that is true when the object are the same, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SocketKey other = (SocketKey) obj;
        if (this.client != other.client && (this.client == null || !this.client.equals(other.client))) {
            return false;
        }
        if (this.server != other.server && (this.server == null || !this.server.equals(other.server))) {
            return false;
        }
        return true;
    }

    /**
     * Returns the hashcode of this object.
     * @return a Integer object
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.client != null ? this.client.hashCode() : 0);
        hash = 17 * hash + (this.server != null ? this.server.hashCode() : 0);
        return hash;
    }
    
    /**
     * Returns the name of this object.
     * @return a String object
     */
    @Override
    public String toString() {
        return "client: " + client.getName() + " " + server.getName();
    }
}