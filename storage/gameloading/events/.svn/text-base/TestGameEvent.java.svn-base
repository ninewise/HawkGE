/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.storage.gameloading.events;

import hawkge.event.Callable;
import hawkge.event.Event;
import java.io.File;

/**
 *
 * @author felix
 */
public class TestGameEvent extends Event<String> {

    private final File game;

    public TestGameEvent(File game, Callable<String> callback) {
        super(callback);
        this.game = game;
    }

    public File getGame() {
        return game;
    }
}
