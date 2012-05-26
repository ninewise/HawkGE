package hawkge.network;

import hawkge.event.Event;
import hawkge.storage.User;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @create on May 5, 2012
 * @author jorisvi
 */
public class DataSendingThread implements Runnable {
    
    private NetworkSocketManager manager;
    private SocketKey key;
    private Event event;
    private NetworkModel model;
    
    /**
     * 
     * @param manager a NetworkManager object
     */
    public DataSendingThread(NetworkSocketManager manager, SocketKey key, Event event,
            NetworkModel model) {
        this.manager = manager;
        this.key = key;
        this.event = event;
        this.model = model;
    }
    
    /**
     * Start the thread to send a Event over the network.
     */
    public void run() {
        sendData();
    }
    
     /**
     * Use the ObjectOutputStream to send data to the server,
     * all objects in the event must implements serializable.
     * @param event a Event object to send over the network
     * @param user a User object to send the event to
     */
    private void sendData() {
        ObjectOutputStream stream = manager.getObjectOutputStream(key);
        if (stream != null) {
            synchronized(stream) {
                try {
                    stream.writeObject(event);
                } catch (IOException ex) {
                    System.out.println("Failed to send event to: " + key.getClient().getIP().toString() + " " + ex);
                    model.closeConnection(key.getClient(), key.getServer());
                }
            }
        } else {
            System.out.println("ObjectOutputStream doesn't exists: " + key.getClient().getIP().toString());
        }
    }
}