package hawkge.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;

/**
 * Server of the client to receive data from other users
 * Wait for an incomming request and start a thread to read 
 * a stream of the socket.
 * @create on Apr 16, 2012
 * @author jorisvi
 */
public class NetworkDataReceiver extends Thread  {
    
    private ServerSocket server;
    private NetworkModel model;
    private final Object waitObject;
    private boolean started;
    private boolean run;
    private ExecutorService serverPool;
    
    /**
     * 
     * @param model a NetworkModel object
     */
    public NetworkDataReceiver(NetworkModel model, Object waitObject) {
        super();
        this.model = model;
        this.waitObject = waitObject;
        started = false;
        run = true;
    }
    
    /**
     * Return the state of the server
     * @return a Boolean object, true if the server is start,
     * false if not.
     */
    public boolean isStarted() {
        return started;
    }
    
    /**
     * Start the server of the client.
     */
    @Override
    public void run() {
        startServer();
    }
    
    /**
     * Opens a ServerSocket and wait for a client to connect,
     * opens a new ClientThread when a client connect.
     */
    private void startServer() {
        try {
            server = new ServerSocket(28960); //cod port 28960
            System.out.println("start server on port: 28960"); // TODO Remove
            synchronized(waitObject) {
                started = true;
                waitObject.notifyAll();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Server port already in use.",
                    "Server not running", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Couldn't start server on port 4444: " + ex);
        }
        serverPool = Executors.newCachedThreadPool();
        while (run) {
            try {
                Socket client = server.accept();
                System.out.println("Client accept" + client.getInetAddress().toString()); // TODO Remove
                serverPool.submit(new ClientThread(client, model));
            } catch (IOException ex) {
                System.out.println("Accept client failed: " + ex);
            }
        }
    }
    
    /**
     * Close the ServerSocket.
     */
    public void closeServer() {
        try {
            run = false;
            server.close();
            serverPool.shutdownNow();
        } catch (IOException ex) {
            System.out.println("Failed: socket closing: " + ex);
        }
    }
}