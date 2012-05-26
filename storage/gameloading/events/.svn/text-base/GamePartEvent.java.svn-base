/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.gameloading.events;

import hawkge.event.NetworkEvent;
import hawkge.storage.User;
import java.util.Collection;

/**
 *
 * @author felix
 */
public class GamePartEvent extends NetworkEvent {

    private final String game;
    private final int index;
    private final byte[] part;

    /**
     * An event to deliver the part with index index to the destination.
     * @param destination The user who requested the game.
     * @param index The index of the byte[] part.
     * @param part A part of the file to be sent.
     */
    public GamePartEvent(User destination, String game, int index, byte[] part) {
        super(destination);
        this.game = game;
        this.index = index;
        this.part = part;
    }

    public String getGame() {
        return game;
    }

    public int getIndex() {
        return index;
    }

    public byte[] getPart() {
        return part;
    }

}
