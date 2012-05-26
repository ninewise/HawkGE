/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.userstats.events;

import hawkge.event.Callable;
import hawkge.event.Event;

/**
 *
 * @author felix
 */
public class PasswordChangedEvent extends Event<Void> {

    private String user;
    private String pass;

    public PasswordChangedEvent(String user, String pass, Callable<Void> callback) {
        super(callback);
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return this.user;
    }

    public String getPass() {
        return this.pass;
    }

}
