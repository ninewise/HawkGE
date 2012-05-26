package hawkge.network;

import hawkge.event.Callable;
import hawkge.event.Event;
import hawkge.event.EventQueue;
import hawkge.event.NetworkEvent;
import hawkge.network.events.AcceptConnection;
import hawkge.network.events.ConnectFriendEvent;
import hawkge.network.events.HeartBeat;
import hawkge.network.events.OnlineUserListEvent;
import hawkge.network.events.RequestConnectionEvent;
import hawkge.network.events.UpdateUserEvent;
import hawkge.storage.User;
import hawkge.storage.events.UserEvent;
import hawkge.storage.usermanager.befriending.AddUserAsFriendEvent;
import hawkge.storage.usermanager.befriending.FriendsListEvent;
import hawkge.storage.usermanager.blocking.BlockEvent;
import hawkge.storage.usermanager.blocking.BlocksListEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Every lock is on the onlinUserlist! NetworkModel logic that coordinates all
 * network actions @create on Apr 16, 2012
 *
 * @author jorisvi
 */
public class NetworkModel implements ActionListener {

    private final NetworkSocketManager datasender;
    private final ArrayList<SocketKey> onlineUsers;
    private final ArrayList<SocketKey> offlineUsers;
    private final ArrayList<User> blockedUsers;
    private final ExecutorService modelPool;
    private final Object waitObject;
    private User ownUser;
    private boolean wait;
    private Timer hearbeatTimer;
    private int ping;

    /**
     *
     * @param queue an EventQueue object for the internal communication
     */
    public NetworkModel() {
        offlineUsers = new ArrayList<SocketKey>();
        onlineUsers = new ArrayList<SocketKey>();
        blockedUsers = new ArrayList<User>();
        modelPool = Executors.newCachedThreadPool();
        datasender = new NetworkSocketManager(this);
        waitObject = new Object();
        hearbeatTimer = new Timer(5000, this);
        ping = 0;
    }

    /**
     * Set the wait on true to wait until notify all is called.
     *
     * @param wait an Boolean object
     */
    private synchronized void setWait(boolean wait) {
        this.wait = wait;
    }

    /**
     * Notified the waiting thread by notifyall on waitObject.
     */
    private void notifyWaitingThread() {
        synchronized (waitObject) {
            setWait(false);
            waitObject.notifyAll();
        }
    }

    /**
     * Wait on callback to go on.
     */
    private void waitOnCallback() {
        synchronized (waitObject) {
            try {
                while (wait) {
                    waitObject.wait();
                }
            } catch (InterruptedException ex) {
                System.out.println("Waiting interrupt: " + ex);
            }
        }
    }

    /**
     * Start pinging friends, asks ownUser information, calls methode
     * createConnections to ping the friends.
     */
    public void loadLists() {
        setWait(true);
        loadUser();
        waitOnCallback();
        setWait(true);
        loadBlockedUsers();
        waitOnCallback();
    }

    /**
     * Initiate the ownUser.
     */
    private void loadUser() {
        EventQueue.queue(new UserEvent(new Callable<User>() {

            public void call(User user) {
                ownUser = user;
                notifyWaitingThread();
            }
        }));
    }

    /**
     * Initiate the blocked users in de list.
     */
    private void loadBlockedUsers() {
        EventQueue.queue(new BlocksListEvent(new Callable<Collection<User>>() {

            public void call(Collection<User> users) {
                for (User user : users) {
                    addBlockedUser(user);
                }
                notifyWaitingThread();
            }
        }));
    }

    /**
     * Pingfriend, start a ConnectingThread for each user, First loadLists must
     * be called.
     */
    public void pingFriends() {
        EventQueue.queue(new FriendsListEvent(new Callable<Collection<User>>() {

            public void call(Collection<User> users) {
                for (User user : users) {
                    createConnection(user);
                }
            }
        }));
        hearbeatTimer.start();
    }

    /**
     * Remove user
     *
     * @param user an User Object
     */
    public void removeUser(User user) {
        synchronized (onlineUsers) {
            offlineUsers.remove(new SocketKey(user, ownUser));
            if (onlineUsers.remove(new SocketKey(user, ownUser))) {
                closeConnection(user, ownUser);
            }
        }
    }

    /**
     * Create a connection to the server of the User Object, first add the user
     * to the offlineList
     *
     * @param user a User object that has the ip to connect to
     */
    public void createConnection(User user) {
        synchronized (onlineUsers) {
            if (!onlineUsers.contains(new SocketKey(user, ownUser))) {
                System.out.println("fail");
                offlineUsers.add(new SocketKey(user, ownUser));
                createConnectionThread(user, ownUser, new RequestConnectionEvent(user, ownUser));
            }
        }
    }

    /**
     * Send NetworkEvents with object that must be serializable through the
     * network, start a new DataSendingThread.
     *
     * @param event a NetworkEvent object
     */
    public void sendData(NetworkEvent event) {
        Collection<User> users = event.getDestinations();
        if (users != null) {
            for (User user : users) {
                // TODO Remove
                System.out.println("Sending to: " + user.getName() + " " + user.getIP().toString() + " " + event);
                sendData(user, event);
            }
        } else {
            // TODO Remove
            JOptionPane.showMessageDialog(null, "Send an event with no destinations: " + event.toString(), "Sending event failed:", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sendData(User user, Event event) {
        modelPool.submit(new DataSendingThread(datasender, new SocketKey(user, ownUser), event, this));
    }

    /**
     * Methode called by a client thread to the server when received an event.
     *
     * @param event an Event object
     */
    public void putNetworkEventOnQeueu(NetworkEvent event) {
        event.clearLocal();
        System.out.println("networkevent on queue" + event); // TODO Remove
        EventQueue.queue(event);
    }

    /**
     * Send an not network event on the queue, For NetworkEvent use
     * putNotworkEventOnqueue.
     *
     * @param event an Event object exclude from networkevent.
     */
    public void putEventOnQueue(Event event) {
        EventQueue.queue(event);
    }

    /**
     * Validate the incomming connection to the server, send an acceptConnection
     * wish contains a boolean, true if accept, false if not.
     *
     * @param event a RequestConnectionEvent object
     */
    public void validateConnection(RequestConnectionEvent event) {
        User client = event.getClient();
        User server = event.getServer();
        // TODO Joris ask felix to set this in storage
        if (client.equals(server)) {
            closeConnection(server, server);
            return;
        }
        // Check for the wright user on the pc and if user is not blocked
        if (ownUser.equals(server) && !blockedUsers.contains(client)) {
            // Check if the user is in the friendlist
            if (offlineUsers.contains(new SocketKey(client, ownUser))
                    || onlineUsers.contains(new SocketKey(client, ownUser))) {
                acceptConnection(event);
            } else {
                // Asks to accept the new user
                int answer = JOptionPane.showConfirmDialog(null, "Add new friend, press no to block user: " + client, "Friend request", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    EventQueue.queue(new AddUserAsFriendEvent(client));
                    acceptConnection(event);
                } else {
                    refuseConnection(client, server);
                    EventQueue.queue(new BlockEvent(client));
                }
            }
        } else {
            refuseConnection(client, server);
        }
    }

    private void acceptConnection(RequestConnectionEvent event) {
        System.out.println("accept send + add in list: " + event.getClient());
        addOnlineUser(new SocketKey(event.getClient(), event.getServer()));
        createConnectionThread(event.getClient(), ownUser, new AcceptConnection(true, ownUser, event.getClient()));
        putEventOnQueue(new UpdateUserEvent(event.getClient()));
    }

    /**
     * Refuse a connection send a AcceptConnection false to the client
     *
     * @param client a User object of the client that tries to connect
     * @param server a User object of the server the client tries to connect
     */
    private void refuseConnection(User client, User server) {
        createConnectionThread(client, server, new AcceptConnection(false, server, client));
    }

    /**
     * Add user to onlinelist if the server has accept the connection.
     *
     * @param event a AcceptConnection object
     */
    public void validateAccept(AcceptConnection event) {
        if (event.isAccept()) {
            System.out.println("accept received: " + event.getUser());
            addOnlineUser(new SocketKey(event.getUser(), event.getServer()));
            putEventOnQueue(new UpdateUserEvent(event.getUser()));
        } else {
            closeConnection(event.getUser(), event.getServer());
        }
    }

    /**
     * Close a socket and stream to user
     *
     * @param user whish connection to close
     */
    public void closeConnection(User user, User server) {
        modelPool.submit(new DisconnectingThread(datasender, new SocketKey(user, server)));
        if (!blockedUsers.contains(user)) {
            addOfflineUser(new SocketKey(user, server));
        }
    }

    /**
     * Called by the networkListener when an onlinUserList is asked and react
     * with a onlineUserlist with a callback system.
     *
     * @param event OnlineUserListEvent
     */
    public void getOnlineUsers(OnlineUserListEvent event) {
        synchronized (onlineUsers) {
            ArrayList<SocketKey> list = (ArrayList<SocketKey>) onlineUsers.clone();
            ArrayList<User> key = new ArrayList<User>();
            for (SocketKey index : list) {
                key.add(index.getClient());
            }
            event.callback(key);
        }
    }

    /**
     * Change user from offlineUserList to onlineUserList throw change on queue
     *
     * @param user an User object
     */
    private void addOnlineUser(SocketKey user) {
        synchronized (onlineUsers) {
            if (!onlineUsers.contains(user)) {
                offlineUsers.remove(user);
                onlineUsers.add(user);
                EventQueue.queue(new ConnectFriendEvent(user.getClient(), true));
                System.out.println(onlineUsers.size() + ": " + offlineUsers.size()); // TODO Remove
                System.out.println("add to onlinelist + on queue user: " + user + "\n---"); // TODO Remove
            }
        }
    }

    /**
     * Change user from onlineUserList to offlineUserList throw change on queue
     *
     * @param user an User object
     */
    private void addOfflineUser(SocketKey user) {
        synchronized (onlineUsers) {
            if (onlineUsers.remove(user)) {
                offlineUsers.add(user);
                EventQueue.queue(new ConnectFriendEvent(user.getClient(), false));
                System.out.println(onlineUsers.size() + ": " + offlineUsers.size()); // TODO Remove
                System.out.println("add to offlinelist + on queue user: " + user + "\n---"); // TODO Remove
            } else {
                System.out.println("refused connection"); // TODO Remove
            }
        }
    }

    /**
     * Send to everyone that the user will be offline.
     */
    public void disconnect() {
        hearbeatTimer.stop();
        synchronized (onlineUsers) {
            if (!onlineUsers.isEmpty()) {
                CountDownLatch lock = new CountDownLatch(onlineUsers.size());
                System.out.println(onlineUsers.size());
                for (int i = 0; i < onlineUsers.size(); i++) {
                    modelPool.submit(new DisconnectingThread(datasender, new SocketKey(onlineUsers.get(i).getClient(), ownUser), lock));
                }
                try {
                    lock.await();
                } catch (InterruptedException ex) {
                    System.out.println("Waiting interrupted: " + ex);
                }
            }
        }
    }

    /**
     * Add a new user to block to the blockedUserList
     *
     * @param user an User object to block
     */
    public void addBlockedUser(User user) {
        blockedUsers.add(user);
        synchronized (onlineUsers) {
            if (onlineUsers.contains(new SocketKey(user, ownUser))) {
                closeConnection(user, ownUser);
            }
        }
        synchronized (blockedUsers) {
            onlineUsers.remove(new SocketKey(user, ownUser));
            offlineUsers.remove(new SocketKey(user, ownUser));
        }
    }

    /**
     * Remove a block on a user, remove user from blockeduserlist, and ping this
     * user
     *
     * @param user an User object to unblock
     */
    public void removeBlockedUser(User user) {
        synchronized (blockedUsers) {
            blockedUsers.remove(user);
        }
        createConnection(user);
    }

    /**
     * Create a connection (Socket and ObjectOutputStream).
     */
    private void createConnectionThread(User client, User server, Event event) {
        modelPool.submit(new ConnectingThread(datasender, new SocketKey(client, server), event, this));
    }

    /**
     * Send a heartbeat to the user, to verify that this user is still online.
     *
     * @param e an ActionEvent object
     */
    public void actionPerformed(ActionEvent e) {
        ping++;
        synchronized (onlineUsers) {
            for (SocketKey user : onlineUsers) {
                sendData(user.getClient(), new HeartBeat());
            }
        }
        if (ping == 12) {
            repingFriends();
            ping = 0;
        }
    }

    /**
     * PingFriends after a while.
     */
    public void repingFriends() {
        System.out.println("reping");
        synchronized (onlineUsers) {
            for (SocketKey user : offlineUsers) {
                createConnectionThread(user.getClient(), ownUser, new RequestConnectionEvent(user.getClient(), ownUser));
            }
        }
    }
}
