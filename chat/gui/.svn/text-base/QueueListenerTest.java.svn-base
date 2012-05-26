package hawkge.chat.gui;

import hawkge.network.*;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.storage.User;
import hawkge.storage.usermanager.befriending.FriendsListEvent;
import hawkge.storage.events.UserEvent;
import java.util.ArrayList;


/**
 * @create on Apr 21, 2012
 * @author jorisvi
 */
public class QueueListenerTest implements EventListener {
    
    public QueueListenerTest() {
        EventQueue.getQueue().addEventListener(this);
    }
    

    static final ArrayList<User> users = new ArrayList<User>();
    static {
        users.add(new User(new IPAddress("192.168.2.3"), "Joris", "noob"));
        users.add(new User(new IPAddress("192.168.2.2"), "Andre", "noob"));
        users.add(new User(new IPAddress("172.16.86.116"), "Felix", "noob"));
        users.add(new User(new IPAddress("172.16.82.45"), "Kint", "noob"));
        users.add(new User(new IPAddress("192.168.0.102"), "Sels", "noob"));
        users.add(new User(new IPAddress("192.168.0.100"), "Sels2", "noob"));
    }

    static final User home = users.remove(4);

    public void onEvent(Event event) {
        if (event instanceof UserEvent) {
            ((UserEvent)event).callback(home);
        } else if (event instanceof FriendsListEvent) {
            ((FriendsListEvent)event).callback(users);
        }
    }

}