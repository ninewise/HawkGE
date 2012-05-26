/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.userstats.events;

import hawkge.event.Event;
import hawkge.storage.User;

/**
 *
 * @author felix
 */
public class ShowUserStatEvent extends Event<Void> {

    private User about;

    public ShowUserStatEvent(User about) {
        this.about = about;
    }

    public User getAbout() {
        return about;
    }

}
