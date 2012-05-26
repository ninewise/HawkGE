package hawkge.network;

import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.event.NetworkEvent;
import hawkge.event.QueueClosedEvent;
import hawkge.network.events.OnlineUserListEvent;
import hawkge.storage.usermanager.blocking.BlockedEvent;
import hawkge.storage.usermanager.befriending.FriendAddedEvent;
import hawkge.storage.usermanager.befriending.FriendRemovedEvent;
import hawkge.storage.usermanager.blocking.DeblockedEvent;

/**
 * Listener that calls the NetworkModel when an event must send over the network
 * or when a user list is requested
 * @create on Apr 16, 2012
 * @author jorisvi
 */
public class NetworkEventListener implements EventListener{
    
    private NetworkModel model;
    
    /**
     * @param model NetworkModel
     * @param queue EventQueue
     */
    public NetworkEventListener(NetworkModel model) {
        this.model = model;
        EventQueue.getQueue().addEventListener(this);
    }

    /**
     * Eventlistener methode
     * will be triggerd when an event is put on the queue
     * @param event 
     */
    public void onEvent(Event event) {
        // event that must be send over the network
        if (event instanceof NetworkEvent && ((NetworkEvent) event).isLocal()) {
            model.sendData((NetworkEvent) event);
        // event that asks an onlinUserlist
        } else if (event instanceof OnlineUserListEvent) {
            model.getOnlineUsers((OnlineUserListEvent) event);
        // event that says to close the connections
        } else if (event instanceof QueueClosedEvent) {
            model.disconnect();
            event.callback(null);
        } else if (event instanceof FriendAddedEvent) {
            model.createConnection(((FriendAddedEvent)event).getFriend());
        } else if (event instanceof FriendRemovedEvent) {
            model.removeUser(((FriendRemovedEvent)event).getFriend());
        } else if (event instanceof BlockedEvent) {
            model.addBlockedUser(((BlockedEvent)event).getBlock());
        } else if (event instanceof DeblockedEvent) {
            model.removeBlockedUser(((DeblockedEvent)event).getUser());
        }
    }
}