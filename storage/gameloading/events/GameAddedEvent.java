/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.gameloading.events;

import hawkge.event.Event;
import java.io.File;

/**
 *
 * @author felix
 */
public class GameAddedEvent extends Event<Void> {

    private final String name;

    public GameAddedEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
