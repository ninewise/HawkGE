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
public class DeblockEvent extends Event<Void> {

    private final User user;

    public DeblockEvent(User user) {
        this.user = user;
    }

    public User getBlock() {
        return this.user;
    }

}
