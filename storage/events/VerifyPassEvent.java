/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.events;

import hawkge.event.Callable;
import hawkge.event.Event;

/**
 *
 * @author felix
 */
public class VerifyPassEvent extends Event<Boolean> {

    private String pass;

    public VerifyPassEvent(String pass, Callable<Boolean> callback) {
        super(callback);
        this.pass = pass;
    }

    public String getPass() {
        return this.pass;
    }

}
