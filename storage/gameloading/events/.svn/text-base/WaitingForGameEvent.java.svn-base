/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.gameloading.events;

import hawkge.event.NetworkEvent;
import hawkge.storage.User;

/**
 *
 * @author felix
 */
public class WaitingForGameEvent extends NetworkEvent {

    private final String name;

    public WaitingForGameEvent(User requestor, String name) {
        super(requestor);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
