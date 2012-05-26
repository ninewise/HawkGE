package hawkge.network;

import hawkge.event.Event;
import hawkge.storage.User;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Thread to connect to the server of an other user, opens a socket and a
 * ObjectOutputStream, out going communication from a client to the server
 * (Thread for the client). @create on Apr 23, 2012
 *
 * @author jorisvi
 */
public class ConnectingThread implements Runnable {

    private NetworkSocketManager dataSender;
    private SocketKey key;
    private Event event;
    private NetworkModel model;

    /**
     *
     * @param dataSender a NetworkSocketManager object
     * @param client a User object
     * @param event a Event Object
     */
    public ConnectingThread(NetworkSocketManager dataSender, SocketKey key, Event event,
            NetworkModel model) {
        this.dataSender = dataSender;
        this.key = key;
        this.event = event;
        this.model = model;
    }

    /**
     * Start a Socket and a ObjectOutputStream when thread.excute is start.
     */
    public void run() {
        openConnection();
    }

    /**
     * Tries to open a Socket and an ObjectOutputStream if the server of the
     * user is online
     *
     * @param ip a String object that represent the ip of the server of the user
     */
    private void openConnection() {
        if (openSocket()) {
            openObjectInputStream();
        }
        if (dataSender.getObjectOutputStream(key) != null) {
            DataSendingThread thread = new DataSendingThread(dataSender, key, event, model);
            thread.run();
        }
    }

    /**
     * Tries to open a socket.
     *
     * @param ip a String object that represent the ip
     * @return return a boolean true when socket is created, false when socket
     * is not created.
     */
    private boolean openSocket() {
        if (dataSender.getSocket(key) == null) {
            // Create Socket
            try {
                System.out.println("try socket: " + key); // TODO Remove
                Socket socket = new Socket();
                dataSender.addSocket(key, socket);
                SocketAddress address = new InetSocketAddress(key.getClient().getIP().toString(), 28960);
                socket.connect(address, 10000);
                System.out.println("connect socket: " + key); // TODO Remove
                return true;
            } catch (IOException ex) {
                System.out.println("Create socket failed: " + key + ex);
                dataSender.removeSocket(key);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Start a ObjectInputStream
     *
     * @param server a String object that represent the ip
     */
    private void openObjectInputStream() {
        // Create ObjectOutputStream
        try {
            ObjectOutputStream stream = new ObjectOutputStream(dataSender.getSocket(key).getOutputStream());
            dataSender.addObjectStream(key, stream);
            System.out.println("created outputstream: " + key); // TODO Remove
        } catch (IOException ex) {
            dataSender.removeObjectOutputStream(key);
            dataSender.removeSocket(key);
            System.out.println("Create ObjectOutputStream failed " + key + ": " + ex);
        }
    }
}