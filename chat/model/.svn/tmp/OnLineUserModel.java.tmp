package hawkge.chat.model;

import hawkge.Model;
import hawkge.chat.event.UserLeavingChatEvent;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.game.events.GameLeftEvent;
import hawkge.network.events.ConnectFriendEvent;
import hawkge.storage.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sels/Kint
 */
public class OnLineUserModel extends Model implements Serializable, EventListener {

    private ArrayList<User> users;
    private User removeduser;
    private String modelID = "";
    private User user; // own user

    /*
     * Model die info bijhoudt over de users in de chatsessie.
     */
    public OnLineUserModel() {
        users = new ArrayList<User>();
        removeduser = null;
        EventQueue.getQueue().addEventListener(this);
    }

    public void destroy() {
        users = null;
        removeduser = null;
        EventQueue.getQueue().removeEventListener(this);
    }

    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
        fireStateChanged();
    }

    public void removeUser(User user) {
        removeduser = user;
        users.remove(user);
        fireStateChanged();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUsers(List<User> users) {
        this.users.clear();
        for (User user : users) {
            this.users.add(user);
        }
        fireStateChanged();
    }

    public void changeModelID(String modelID) {
        this.modelID = modelID;
    }

    public int getUserIndex(User user) {
        return users.indexOf(user);
    }

    public int getNumberOfUsers() {
        return users.size();
    }

    public User getRemovedUser() {
        return removeduser;
    }

    public boolean containsUser(User user) {
        return users.contains(user);
    }

    /**
     * Verwijder een user uit de lijst wanneer deze offline gaat *
     */
    public void onEvent(Event event) {
        if (event instanceof ConnectFriendEvent) {
            ConnectFriendEvent evt = (ConnectFriendEvent) event;
            if (!evt.isOnline() && users.contains(evt.getUser())) {
                users.remove(evt.getUser());
                fireStateChanged();
            }
        } else if (event instanceof GameLeftEvent) {
            GameLeftEvent evt = (GameLeftEvent) event;
            if (users.contains(evt.getSender()) && evt.getDestinations().contains(user)) {
                users.remove(evt.getSender());
                fireStateChanged();
            }
        } else if (event instanceof UserLeavingChatEvent) {
            UserLeavingChatEvent leaving = (UserLeavingChatEvent) event;
            if (leaving.getModelID().equals(modelID)) {
                if (leaving.isLocal()) {
                    EventQueue.getQueue().removeEventListener(this);
                }
            }
        }
    }
}
