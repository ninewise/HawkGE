/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.storage.gameloading.events;

import hawkge.event.Event;
import hawkge.storage.User;

/**
 *
 * @author felix
 */
public class EnsureGameEvent extends Event<Void> {

    private final String game;
    private final User requestor;
    private final User owner;

    public EnsureGameEvent(String game, User requestor, User owner) {
        this.game = game;
        this.requestor = requestor;
        this.owner = owner;
    }

    public String getGame() {
        return this.game;
    }

    public User getOwner() {
        return owner;
    }

    public User getRequestor() {
        return requestor;
    }

}
