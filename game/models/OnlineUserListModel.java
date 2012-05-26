package hawkge.game.models;

import hawkge.event.Callable;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.network.events.ConnectFriendEvent;
import hawkge.network.events.OnlineUserListEvent;
import hawkge.storage.User;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.DefaultListModel;

/**
 * Model voor een JList die online spelers toont
 * @author michaelkint
 */
public class OnlineUserListModel extends DefaultListModel implements EventListener {

    private ArrayList<User> users;

    /** Constructoor voor een extensie van DefaultListModel, bedoeld voor Users. */
    public OnlineUserListModel() {
        users = new ArrayList<User>();
        EventQueue.getQueue().addEventListener(this);
        getOnlineFriends();
    }

    /** @return Het aantal users in de lijst. **/
    @Override
    public int getSize() {
        if (users == null) {
            return 0;
        }
        return users.size();
    }

    /** @return Geef de User op index i terug. 
    @param i De index waarop de User dient afgehaald te worden. **/
    @Override
    public Object getElementAt(int i) {
        if (users == null) {
            return null;
        }
        return users.get(i);
    }

    /** Voeg een Event toe aan de EventQueue. **/
    public void getOnlineFriends() {
        EventQueue.queue(new OnlineUserListEvent(new Callable<Collection<User>>() {
            public void call(Collection<User> users) {
                initList(users);
            }
        }));
    }

    /** Initialiseer de lijst met gebruikers, die in dit model worden bijgehouden. 
    @param users De lijst met users die in de jlist dient te worden weergeven. */
    public void initList(Collection<User> users) {
        this.users.clear();
        for (User user : users) {
            this.users.add(user);
        }
        fireContentsChanged(this, 0, this.users.size());
    }

    /** Deze klasse luistert naar de EventQueue, bijvoorbeeld om te handelen wanneer
    een speler offline gaat. 
    @param event Het event dat op de EventQueue werd geworpen. **/
    public void onEvent(Event event) {
        if (event instanceof ConnectFriendEvent) {
            getOnlineFriends();
        }
    }
}
