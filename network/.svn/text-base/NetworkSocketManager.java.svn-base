package hawkge.network;

import hawkge.storage.User;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

/**
 * Sends information to the server of another user,
 * contains all open Sockets and ObjectOutputStreams
 * @create on Apr 16, 2012
 * @author jorisvi
 */
public class NetworkSocketManager {

    private HashMap<SocketKey, Socket> sockets;
    private HashMap<SocketKey, ObjectOutputStream> outStreams;

    /**
     * 
     * @param model a NetworkModel object
     */
    public NetworkSocketManager(NetworkModel model) {
        sockets = new HashMap<SocketKey, Socket>();
        outStreams = new HashMap<SocketKey, ObjectOutputStream>();
    }

    /**
     * Returns the socket of the ip if this socket exists.
     * @param ip a String object that represent an ip
     * @return a Socket object.
     */
    public Socket getSocket(SocketKey key) {
        return sockets.get(key);
    }
    
    /**
     * Put a Socket object in a hashmap, key is the ip.
     * @param ip a String object that represent the ip of the Socket.
     * @param socket a Socket object that is connected to a server
     */
    public void addSocket(SocketKey key, Socket socket) {
        sockets.put(key, socket);
    }

    /**
     * Removes the Socket object out the hashmap.
     * @param ip a String that represent the ip of the Socket
     */
    public void removeSocket(SocketKey key) {
        sockets.remove(key);
    }

    /**
     * Returns the ObjectOutputStream of the ip if this stream exists.
     * @param ip a String object that represent an ip
     * @return a ObjectOutputStream object.
     */
    public ObjectOutputStream getObjectOutputStream(SocketKey key) {
        return outStreams.get(key);
    }
    
    /**
     * Put a ObjectOutputStream object in a hashmap, key is the ip.
     * @param ip a String object that represent the ip of the ObjectOutputStream.
     * @param stream a ObjectOutputStream object that is connected to a server
     */
    public void addObjectStream(SocketKey key, ObjectOutputStream stream) {
        outStreams.put(key, stream);
    }
    
    /**
     * Removes the ObjectOutputStream object out the hashmap.
     * @param ip a String that represent the ip of the ObjectOuputStream
     */
    public void removeObjectOutputStream(SocketKey key) {
        outStreams.remove(key);
    }
}