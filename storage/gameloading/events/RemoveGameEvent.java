/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.gameloading.events;

import hawkge.event.Event;

/**
 *
 * @author felix
 */
public class RemoveGameEvent extends Event<Void> {

    private String name;

    public RemoveGameEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
