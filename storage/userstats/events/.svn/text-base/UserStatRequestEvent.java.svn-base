/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.userstats.events;

import hawkge.event.NetworkEvent;
import hawkge.storage.User;

/**
 * Make this to receive a UserStatEvent from the about user.
 * @author felix
 */
public class UserStatRequestEvent extends NetworkEvent {

    private final User requestor;
    private final User about;

    public UserStatRequestEvent(User requestor, User about) {
        super(about);
        this.requestor = requestor;
        this.about = about;
    }

    public User getRequestor() {
        return requestor;
    }

    public User getAbout() {
        return about;
    }

}
