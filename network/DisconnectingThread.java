package hawkge.network;

import hawkge.network.events.ConnectFriendEvent;
import hawkge.storage.User;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A thread to close the connecting to a server, close the socket and the
 * ObjectOutputStream. @create on May 4, 2012
 *
 * @author jorisvi
 */
public class DisconnectingThread implements Runnable {

    private NetworkSocketManager dataSender;
    private SocketKey key;
    private CountDownLatch lock = null;

    /**
     *
     * @param dataSender a NetworkDataSender object
     * @param user a User object of the to closing Socket
     */
    public DisconnectingThread(NetworkSocketManager dataSender, SocketKey key) {
        this.dataSender = dataSender;
        this.key = key;
    }

    public DisconnectingThread(NetworkSocketManager dataSender, SocketKey key, CountDownLatch lock) {
        this(dataSender, key);
        this.lock = lock;
    }

    public void run() {
        ObjectOutputStream stream = dataSender.getObjectOutputStream(key);
        if (stream != null) {
            try {
                stream.writeObject(new ConnectFriendEvent(key.getServer(), false));
            } catch (IOException ex) {
                System.out.println("Disconnect send failed: " + ex);
            }
        }
        closeConnection();
        if (lock != null) {
            lock.countDown();
        }
    }

    /**
     * Close a socket and ObjectOutputStream.
     *
     * @param ip a String object of the ip of the socket
     */
    private void closeConnection() {
        closeObjectOutputStream();
        closeSocket();
    }

    /**
     * Close the ObjectOutputStream.
     *
     * @param ip a String object of the ip of the ObjectOutputStream
     */
    private void closeObjectOutputStream() {
        try {
            ObjectOutputStream out = dataSender.getObjectOutputStream(key);
            if (out != null) {
                out.close();
            }
        } catch (IOException ex) {
            System.out.println("OjbectOutputStream closing failed: " + key + " " + ex);
        } finally {
            System.out.println("remove stream: " + key);
            dataSender.removeObjectOutputStream(key);
        }
    }

    /**
     * Close the Socket.
     *
     * @param ip a String object of the ip of the Socket
     */
    private void closeSocket() {
        try {
            Socket socket = dataSender.getSocket(key);
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Socket closing failed: " + key + " " + ex);
        } finally {
            System.out.println("remove socket: " + key);
            dataSender.removeSocket(key);
        }
    }
}