package hawkge.main.lobby.list;

import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.network.events.OnlineUserListEvent;
import hawkge.storage.User;
import hawkge.storage.usermanager.blocking.BlocksListEvent;
import hawkge.storage.usermanager.befriending.FriendsListEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.swing.AbstractListModel;

/**
 * @create on Apr 20, 2012
 *
 * @author jorisvi
 */
public class LobbyListModel extends AbstractListModel {

    private final ArrayList<UserListComponent> users;
    private final Object waitObject;
    private boolean wait;

    /**
     * Initialisate the list.
     */
    public LobbyListModel() {
        waitObject = new Object();
        users = new ArrayList<UserListComponent>();
        setWait(true);
        loadUsers();
        waitOnCallback();
        setWait(true);
        new LobbyEventListener(this);
        setOnlineUsers();
        // Add EventListener at this possition, to prevent that some
        // online users aren't in the list.
        waitOnCallback();
        setWait(true);
        loadBlockedUsers();
        waitOnCallback();
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
     * Fills the list with all friends of this user.
     *
     * @param users a Collection object
     */
    private void loadUsers() {
        EventQueue.queue(new FriendsListEvent(new Callable<Collection<User>>() {

            public void call(Collection<User> callbackList) {
                for (User user : callbackList) {
                    addUser(UserState.OFFLINE, user);
                }
                notifyWaitingThread();
            }
        }));
    }

    /**
     * Initiate the online users in teh list.
     *
     * @param users
     */
    private void setOnlineUsers() {
        EventQueue.queue(new OnlineUserListEvent(new Callable<Collection<User>>() {

            public void call(Collection<User> users) {
                for (User user : users) {
                    setUserState(UserState.ONLINE, user);
                }
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
                    addUser(UserState.BLOCKED, user);
                }
            }
        }));
        notifyWaitingThread();
    }

    /**
     * Add a new User to this list
     *
     * @param user an User object
     */
    public void addUser(UserState state, User user) {
        UserListComponent component = new UserListComponent(state, user);
        synchronized (users) {
            if (!users.contains(component)) {
                users.add(component);
            }
        }
        refreshList();
    }

    /**
     * Remove a User from the list.
     */
    public void removeUser(User user) {
        synchronized (users) {
            users.remove(new UserListComponent(user));
        }
        refreshList();
    }

    /**
     * Set the user to online, if the user isn't in the list than the user will
     * be add
     *
     * @param user a User object that represent the user
     * @param state a UserState object, the state of the user
     */
    public void setUserState(UserState state, User user) {
        int position;
        synchronized (users) {
            position = users.indexOf(new UserListComponent(user));
            if (position != -1) {
                UserListComponent component = users.get(position);
                component.setState(state);
            }
        }
        if (position == -1) {
            addUser(state, user);
        } else {
            refreshList();
        }
    }

    /**
     * Sort the list.
     */
    public void sortList() {
        Collections.sort(users);
    }

    /**
     * Return the size of the list.
     *
     * @return the size of the list
     */
    public int getSize() {
        return users.size();
    }

    /**
     * Return element at position index.
     *
     * @param index a Integer object that represents the index
     * @return the object at index position, returns null if list is empty
     */
    public Object getElementAt(int index) {
        if (!users.isEmpty()) {
            return users.get(index);
        } else {
            return null;
        }
    }

    /**
     * Update the list.
     */
    private void refreshList() {
        sortList();
        fireContentsChanged(this, 0, users.size() - 1);
    }
}
