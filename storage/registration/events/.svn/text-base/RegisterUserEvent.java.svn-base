/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.registration.events;

import hawkge.event.Callable;
import hawkge.event.Event;
import hawkge.network.IPAddress;
import hawkge.storage.User;

/**
 * Tries to reigsters a user with given username, password and IP. Calls back
 * the new User, or null in case the username is occupied.
 * @author felix
 */
public class RegisterUserEvent extends Event<User> {

    private String name;
    private String pass;
    private IPAddress ip;
    private String description;

    public RegisterUserEvent(String name, String pass, IPAddress ip, String description
            , Callable<User> callback) {
        super(callback);
        this.name = name;
        this.pass = pass;
        this.ip = ip;
        this.description = description;
    }

    public String getName() { return this.name; }
    public String getPass() { return this.pass; }
    public IPAddress getIP() { return this.ip; }
    public String getDescription() { return this.description; }

}
