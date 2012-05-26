/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.usermanager.blocking;

import hawkge.event.Event;
import hawkge.storage.User;

/**
 *
 * @author felix
 */
public class DeblockedEvent extends Event<Void> {

    private User user;

    public DeblockedEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

}
