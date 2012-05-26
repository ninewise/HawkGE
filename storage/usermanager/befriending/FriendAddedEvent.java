/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.usermanager.befriending;

import hawkge.event.Event;
import hawkge.storage.User;

/**
 * Signals the contained User has been added as a friend.
 * @author felix
 */
public class FriendAddedEvent extends Event<Void> {

    private User friend;

    public FriendAddedEvent(User friend) {
        this.friend = friend;
    }

    public User getFriend() { return this.friend; }

}
