
package hawkge.storage;

import hawkge.storage.usermanager.UserSettings;
import hawkge.storage.usermanager.UserManager;
import hawkge.storage.gameloading.GameManager;
import hawkge.event.EventQueue;
import hawkge.network.IPAddress;
import java.io.File;
import org.jdom.Element;

public class LocalManager extends Storable {

    private GameManager gameManager;
    private UserManager userManager;
    private User user;
    private UserSettings userSettings;

    public LocalManager(User user) {
        this.user = user;
        userManager = new UserManager(user);
        userSettings = new UserSettings(user);
        gameManager = new GameManager(user);
    }

    public GameManager getGameManager() { return gameManager; }
    public UserManager getUserManager() { return userManager; }
    public User getUser() { return user; }
    public UserSettings getUserSettings() { return userSettings; }

    @Override
    public File getLocation() {
        return new File(getStorageRoot(), "localmanager_" + user.getName() + ".hgedata");
    }

    @Override
    public Element getXML() {
        Element localManager = new Element("localmanager");
        Element user = this.user.getXML();
        Element userSettings = this.userSettings.getXML();
        Element userManager = this.userManager.getXML();
        Element gameManager = this.gameManager.getXML();
        localManager.addContent(user);
        localManager.addContent(userSettings);
        localManager.addContent(userManager);
        localManager.addContent(gameManager);
        return localManager;
    }

    public static LocalManager load(File data, String pass) {
        LocalManager read = null;
        try {
            System.out.println("* Loading file " + data.getAbsoluteFile());
            read = readXML(getElement(data, pass));
        } catch (StoreException ex) {
            ex.printStackTrace();
            return null;
        }
        System.out.println("* Loaded file " + data.getAbsolutePath());
        return read;
    }

    /**
     * Parses <code>element</code> to create a LocalManager out of it.
     * @param element A Xml element representing a LocalManager.
     * @return The LocalManager represented by <code>element</code>
     * @throws StoreException If <code>element</code> does not represent a
     * LocalManager.
     */
    public static LocalManager readXML(Element element) throws StoreException {
        if(element == null) throw new StoreException("File not a(n existing) Localmanager. Also thrown when a new User was created.");
        User user = User.readXML(element.getChild("user"));
        LocalManager localManager = new LocalManager(user);
        localManager.userSettings = UserSettings.readXML(element.getChild("usersettings"), user);
        localManager.userManager = UserManager.readXML(element.getChild("usermanager"), user);
        localManager.gameManager = GameManager.readXML(element.getChild("gamemanager"), user);
        return localManager;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LocalManager other = (LocalManager) obj;
        if (this.gameManager != other.gameManager && (this.gameManager == null || !this.gameManager.equals(other.gameManager))) {
            return false;
        }
        if (this.userManager != other.userManager && (this.userManager == null || !this.userManager.equals(other.userManager))) {
            return false;
        }
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        if (this.userSettings != other.userSettings && (this.userSettings == null || !this.userSettings.equals(other.userSettings))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.user != null ? this.user.hashCode() : 0);
        return hash;
    }

}
