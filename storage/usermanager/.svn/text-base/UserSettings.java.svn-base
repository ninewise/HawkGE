
package hawkge.storage.usermanager;

import hawkge.storage.Storable;
import hawkge.storage.StoreException;
import hawkge.storage.User;
import java.awt.Font;
import java.io.File;
import org.jdom.DataConversionException;
import org.jdom.Element;

public class UserSettings extends Storable {

    private User user;
    private String colour;
    private Font font;

    public UserSettings(User user) {
        this.user = user;
    }

    public String getChatColour() { return colour; }
    public Font getChatFont() { return font; }

    public void setChatColour(String rgb) {
        this.colour = rgb;
    }

    public void setChatFont(Font font) {
        this.font = font;
    }

    @Override
    public File getLocation() {
        return new File(getStorageRoot(), "usersetting_" + user.getName() + ".hgedata");
    }

    @Override
    public Element getXML() {
        Element userSettings = new Element("usersettings");
        Element colour = new Element("colour");
        Element font = new Element("font");
        if(this.colour != null) colour.setText(this.colour);
        if(this.font != null) {
            font.setAttribute("style", String.valueOf(this.font.getStyle()));
            font.setAttribute("size", String.valueOf(this.font.getSize()));
            font.setText(this.font.getFontName());
        }
        userSettings.addContent(colour);
        userSettings.addContent(font);
        return userSettings;
    }

    /**
     * Parses <code>element</code> to create a UserSettings out of it.
     * @param element A Xml element representing a UserSettings.
     * @return The UserSettings represented by <code>element</code>
     * @throws StoreException If <code>element</code> does not represent a
     * UserSettings.
     */
    public static UserSettings readXML(Element element, User user) throws StoreException {
        if(element == null || ! "usersettings".equals(element.getName())) {
            throw new StoreException("Failed parsing element as UserSettings.");
        }
        UserSettings userSettings = new UserSettings(user);
        userSettings.colour = element.getChild("colour").getText();
        if(userSettings.colour.equals("")) userSettings.colour = null;
        Element font = element.getChild("font");
        if(!font.getText().equals("")) {
            try {
                userSettings.font = new Font(font.getText(),
                    font.getAttribute("style").getIntValue(),
                    font.getAttribute("size").getIntValue());
            } catch(DataConversionException e) {
                throw new StoreException("Unable to read attributes as integers.");
            }
        } else {
            userSettings.font = null;
        }
        return userSettings;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserSettings other = (UserSettings) obj;
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        if ((this.colour == null) ? (other.colour != null) : !this.colour.equals(other.colour)) {
            return false;
        }
        if (this.font != other.font && (this.font == null || !this.font.equals(other.font))) {
            if(!this.font.getName().equals(other.font.getName()) ||
                    !(this.font.getSize() == other.font.getSize()) ||
                    !(this.font.getStyle() == other.font.getStyle())) {
                System.out.println("EEP!");
                return false;
            }

        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.user != null ? this.user.hashCode() : 0);
        return hash;
    }

}
