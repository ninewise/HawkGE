/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.usermanager.blocking;

import hawkge.event.Callable;
import hawkge.event.Event;
import hawkge.storage.User;
import java.util.Collection;

/**
 *
 * @author felix
 */
public class BlocksListEvent extends Event<Collection<User>> {

    public BlocksListEvent(Callable<Collection<User>> callback) {
        super(callback);
    }
}
