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
public class AddGameEvent extends Event<Void> {

    private final File g;

    public AddGameEvent(File g) {
        this.g = g;
    }

    public File getGame() {
        return g;
    }

}
