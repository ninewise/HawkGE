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
public class GameRequestedEvent extends NetworkEvent {

    private final String gameName;
    private final int parts;
    private final User owner;

    public GameRequestedEvent(String gameName, User requestor, int parts, User owner) {
        super(requestor);
        this.gameName = gameName;
        this.parts = parts;
        this.owner = owner;
    }

    public String getGameName() {
        return gameName;
    }

    public int getParts() {
        return parts;
    }

    public User getOwner() {
        return owner;
    }

}
