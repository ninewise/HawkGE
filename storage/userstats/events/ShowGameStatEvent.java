/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.userstats.events;

import hawkge.event.Event;

/**
 *
 * @author felix
 */
public class ShowGameStatEvent extends Event<Void> {

    private String g;

    public ShowGameStatEvent(String g) {
        this.g = g;
    }

    public String getName() {
        return g;
    }

}
