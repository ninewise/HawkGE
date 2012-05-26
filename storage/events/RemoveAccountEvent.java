/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.events;

import hawkge.event.Callable;
import hawkge.event.Event;

/**
 * Thrown to remove the current account.
 * @author felix
 */
public class RemoveAccountEvent extends Event<Void> {

    public RemoveAccountEvent(Callable<Void> callback) {
        super(callback);
    }

}
