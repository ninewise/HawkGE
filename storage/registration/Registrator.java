/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.registration;

import hawkge.event.Callable;
import hawkge.storage.registration.events.RegisterUserEvent;
import hawkge.storage.registration.events.VerifyLoginEvent;
import hawkge.storage.registration.events.CloseRegistratorEvent;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.event.QueueClosedEvent;
import hawkge.network.IPAddress;
import hawkge.storage.Storable;
import hawkge.storage.StoreException;
import hawkge.storage.User;
import hawkge.storage.events.RemoveAccountEvent;
import hawkge.storage.events.UserEvent;
import hawkge.storage.userstats.events.PasswordChangedEvent;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

/**
 *
 * @author felix
 */
public class Registrator extends Storable implements EventListener {

    private File usersFile;
    private Element usersElement;

    @SuppressWarnings("LeakingThisInConstructor")
    public Registrator() {
        usersFile = new File(getStorageRoot(), "userfile.hgedata");
        try {
            usersElement = getElement(usersFile, "12341234");
        } catch (StoreException ex) {
            throw new RuntimeException("Unable to load userdata: " + ex.getMessage());
        }
        if(usersElement == null) usersElement = new Element("userlist");
        EventQueue.getQueue().addEventListener(this);
    }

    /**
     * Verifies if there exists a user with the given username and password.
     * @param username The name of the user to verify.
     * @param password The password of the user to verify.
     * @return The user if correctly verified, null otherwise.
     */
    public User verify(String username, String password) {
        if(null == username) throw new IllegalArgumentException("null is not a user.");
        if(null == password) throw new IllegalArgumentException("null is not a pass.");
        List<Element> users = usersElement.getChildren("user");
        for(Element user : users) {
            if(username.equals(user.getChildText("name"))
            && hash(password).equals(user.getChildText("pass"))) {
                return new User(
                    new IPAddress(user.getChildText("ip")),
                    username,
                    user.getChildText("desc"));
            }
        }
        return null;
    }

    /**
     * Registers a new user for the game.
     * @param username The username for the new user.
     * @param password The password the new player would like.
     * @param ip The local IPAddress.
     * @return The newly created user.
     * @throws StoreException If a user with this name allready exists.
     */
    public User register(String username, String password, IPAddress ip, String description) throws StoreException {
        List<Element> users = usersElement.getChildren("user");
        for(Element user : users) {
            if(user.getChildText("name").equals(username)) {
                throw new StoreException("Username exists!");
            }
        }
        Element user = new Element("user");
        user.addContent(new Element("name").addContent(username));
        user.addContent(new Element("pass").addContent(hash(password)));
        user.addContent(new Element("ip").addContent(ip.toString()));
        user.addContent(new Element("desc").addContent(description));
        usersElement.addContent(user);
        return new User(ip, username, description);
    }

    public void changePass(String username, String password) {
        List<Element> users = usersElement.getChildren("user");
        for(Element user : users) {
            if(user.getChildText("name").equals(username)) {
                user.getChild("pass").setText(hash(password));
            }
        }
    }

    private String hash(String string) {
        java.security.MessageDigest d;
        try {
            d = java.security.MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            // We'd be doomed.
            throw new RuntimeException(ex.getMessage());
        }
        d.reset();
        d.update(string.getBytes());
        byte[] bytes = d.digest();
        String hash = "";
        for(int i = 0; i < bytes.length; i++) hash = hash + Byte.toString(bytes[i]);
        return hash;
  }

    @Override
    public Element getXML() {
        return usersElement;
    }

    @Override
    public File getLocation() {
        return usersFile;
    }

    public void remove(String username) {
        List<Element> users = usersElement.getChildren("user");
        Element toRemove = null;
        for(Element user : users) {
            if(user.getChildText("name").equals(username)) {
                toRemove = user;
            }
        }
        if(toRemove != null) usersElement.removeContent(toRemove);
    }

    public void onEvent(final Event event) {

        if(event instanceof RemoveAccountEvent) {
            EventQueue.queue(new UserEvent(new Callable<User>() {
                public void call(User param) {
                    remove(param.getName());
                    EventQueue.queue(new CloseRegistratorEvent(new Callable<Void>() {
                        public void call(Void param) {
                            event.callback(null);
                        }
                    }));
                }
            }));
            return;
        }

        if(event instanceof VerifyLoginEvent) {
            VerifyLoginEvent vle = (VerifyLoginEvent) event;
            vle.callback(verify(vle.getName(), vle.getPassword()));
            return;
        }

        if(event instanceof RegisterUserEvent) {
            RegisterUserEvent rue = (RegisterUserEvent) event;
            try {
                rue.callback(register(rue.getName(), rue.getPass(), rue.getIP(), rue.getDescription()));
            } catch (StoreException ex) {
                ex.printStackTrace();
                rue.callback(null);
            }
            return;
        }

        if(event instanceof QueueClosedEvent || event instanceof CloseRegistratorEvent) {
            try {
                System.out.println("Registrator received " + event.getClass().getSimpleName());
                save("12341234");
                System.out.println("Registrator saved.");
            } catch (StoreException ex) {
                ex.printStackTrace();
                // TODO Wat moet er in godsnaam gebeuren als dit faalt?
            }
            EventQueue.getQueue().removeEventListener(this);
            event.callback(null);
            return;
        }

        if(event instanceof PasswordChangedEvent) {
            PasswordChangedEvent passwordChangedEvent = (PasswordChangedEvent) event;
            changePass(passwordChangedEvent.getUser(), passwordChangedEvent.getPass());
            event.callback(null);
            return;
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Registrator other = (Registrator) obj;
        if (this.usersFile != other.usersFile && (this.usersFile == null || !this.usersFile.equals(other.usersFile))) {
            return false;
        }
        Iterator<Element> thisList = this.usersElement.getChildren().iterator();
        Iterator<Element> thatList = other.usersElement.getChildren().iterator();
        while(thisList.hasNext() && thatList.hasNext()) {
            String thisString = thisList.next().toString();
            String thatString = thatList.next().toString();
            if(thisString != null && !thisString.equals(thatString)) {
                return false;
            } else if(thisString == null && thatString != null) {
                return false;
            }
        }
        return thisList.hasNext() == thatList.hasNext();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.usersFile != null ? this.usersFile.hashCode() : 0);
        return hash;
    }

}
