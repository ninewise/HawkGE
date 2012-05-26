package hawkge.network;

import hawkge.event.NetworkEvent;
import hawkge.network.events.AcceptConnection;
import hawkge.network.events.ConnectFriendEvent;
import hawkge.network.events.HeartBeat;
import hawkge.network.events.RequestConnectionEvent;
import hawkge.storage.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.Timer;

/**
 * Thread for Socket and ObjectInputStream incomming communication from a client
 * to the server, (thread for the server). @create on Apr 23, 2012
 *
 * @author jorisvi
 */
public class ClientThread implements Runnable, ActionListener {

    private Socket client;
    private ObjectInputStream in;
    private NetworkModel model;
    private User user;
    private User server;
    private Timer timer;

    /**
     * @param client a Socket object of the client
     * @param model a NetworkModel object
     */
    public ClientThread(Socket client, NetworkModel model) {
        this.client = client;
        this.model = model;
        this.timer = new Timer(20000, this);
    }

    /**
     * Start a ObjectInputStream when a client connect to the server.
     */
    public void run() {
        try {
            in = new ObjectInputStream(client.getInputStream());
            readMessages();
        } catch (IOException ex) {
            System.out.println("Accept inputstream failed: " + ex);
            try {
                client.close();
            } catch (IOException ex1) {
                System.out.println("Failed to close ClientSocket: " + ex1);
            }
        }
    }

    /**
     * Continue reading from the ObjectInputStream, reads the events from the
     * client.
     */
    private void readMessages() {
        System.out.println("Reading"); // TODO Remove
        timer.start();
        System.out.println("Start clientthreadtimer");
        while (in != null) {
            try {
                Object received = in.readObject();
                if (received != null) {
                    if (received instanceof HeartBeat) {
                        timer.restart();
                    } else if (received instanceof NetworkEvent) {
                        model.putNetworkEventOnQeueu((NetworkEvent) received);
                    } else if (received instanceof RequestConnectionEvent) {
                        RequestConnectionEvent request = (RequestConnectionEvent) received;
                        user = request.getClient();
                        server = request.getServer();
                        model.validateConnection(request);
                    } else if (received instanceof AcceptConnection) {
                        AcceptConnection accept = (AcceptConnection) received;
                        user = accept.getUser();
                        server = accept.getServer();
                        model.validateAccept(accept);
                        if (!accept.isAccept()) {
                            closeConnection();
                        }
                    } else if (received instanceof ConnectFriendEvent) {
                        if (!((ConnectFriendEvent)received).isOnline()) {
                            System.out.println(received);
                            closeConnection();
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println("Stop reading messages: " + ex);
                closeConnection();
            } catch (ClassNotFoundException ex) {
                System.out.println("Reading none existing class: " + ex);
            }
        }
    }

    private void closeConnection() {
        timer.stop();
        try {
            in.close();
            client.close();
            System.out.println("Client closed: " + user); // TODO Remove
        } catch (IOException ex) {
            System.out.println("Closing connection failed: " + ex);
        } finally {
            model.closeConnection(user, server);
        }
        in = null;
    }

    /**
     * When this is started the connection between the user and this server is lost.
     * @param e an ActionEvent object
     */
    public void actionPerformed(ActionEvent e) {
        closeConnection();
    }
}