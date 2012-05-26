package hawkge.main.inlog;

import hawkge.Model;
import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.event.ResetQueueEvent;
import hawkge.main.NameModelInterface;
import hawkge.main.lobby.LobbyFrame;
import hawkge.network.NetworkDataReceiver;
import hawkge.network.NetworkEventListener;
import hawkge.network.NetworkModel;
import hawkge.storage.User;
import hawkge.storage.events.SaveEvent;
import hawkge.storage.events.StorageEventListener;
import hawkge.storage.registration.Registrator;
import hawkge.storage.registration.events.CloseRegistratorEvent;
import hawkge.storage.registration.events.VerifyLoginEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @create on Apr 27, 2012
 *
 * @author jorisvi
 */
public class InlogModel extends Model implements NameModelInterface {
    
    private String name;
    private String password;
    private JFrame loginFrame;
    private NetworkModel networkModel;
    private NetworkDataReceiver receiver;

    /**
     *
     * @param queue EventQueue internal communication
     * @param loginFrame LoginFrame needed to dispose
     */
    public InlogModel(JFrame loginFrame) {
        super();
        name = new String();
        password = new String();
        this.loginFrame = loginFrame;
        new Registrator();
    }

    /**
     * Send an VerifyLoginEvent on the eventqueue and calls methode checkLogin,
     * Storage react on this event with the callback function.
     */
    public void sendLoginRequest() {
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(loginFrame, "Fill in a username",
                    "NameField empty", JOptionPane.ERROR_MESSAGE);
        } else if (password.length() == 0) {
            JOptionPane.showMessageDialog(loginFrame, "Fill in a password",
                    "PasswordField empty", JOptionPane.ERROR_MESSAGE);
        } else {
            EventQueue.queue(new VerifyLoginEvent(new Callable<User>() {
                
                public void call(User user) {
                    confirm(user);
                }
            }, name, password));
        }
    }

    /**
     * Method that dispose the loginwindow and open the lobby if the user exist.
     *
     * @param user User
     */
    private void confirm(User user) {
        if (user == null) {
            JOptionPane.showMessageDialog(loginFrame, "Login failed:\nname or password wrong", "Login failed", JOptionPane.ERROR_MESSAGE);
        } else {
            EventQueue.queue(new CloseRegistratorEvent());
            startLobby(user);
        }
        clearFields();
    }

    /**
     * Start everything for the lobby
     */
    private void startLobby(User user) {
        loginFrame.setVisible(false);
        StorageEventListener storage = new StorageEventListener(user, password);
        // Start network
        networkModel = new NetworkModel();
        // Load the lists
        networkModel.loadLists();
        NetworkEventListener listener = new NetworkEventListener(networkModel);
        final Object waitObject = new Object();
        receiver = new NetworkDataReceiver(networkModel, waitObject);
        receiver.start();
        // Wait until the receiver start.
        synchronized (waitObject) {
            try {
                while (!receiver.isStarted()) {
                    waitObject.wait();
                }
            } catch (InterruptedException ex) {
                System.out.println("Waiting interrupt: " + ex);
            }
        }
        networkModel.pingFriends();
        new LobbyFrame("Login as: " + user.getName() + " " + user.getIP().toString(), loginFrame, this);
    }

    /**
     * Clears the fields name and password.
     */
    private void clearFields() {
        password = "";
        name = "";
        fireStateChanged();
    }

    /**
     * Close the connection and storage, remove the networklistener en
     * storagelistener from the queue.
     */
    public void logout() {
        EventQueue.queue(new SaveEvent());
        receiver.closeServer();
        networkModel.disconnect();
        EventQueue.queue(new ResetQueueEvent());
        new Registrator();
    }

    /**
     * Set password variable.
     *
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the password string.
     *
     * @return String of the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set name varialbe.
     *
     * @param name Name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the user.
     *
     * @return String of the name
     */
    public String getName() {
        return name;
    }
}
