package hawkge.storage;

import hawkge.network.IPAddress;
import java.io.File;
import java.io.Serializable;
import org.jdom.Element;

public class User extends Storable implements Serializable {

    private IPAddress ip;
    private String name = "";
    private String description = "";

    public User(IPAddress ip, String name) {
        this.ip = ip;
        this.name = name;
    }

    public User(IPAddress ip, String name, String description) {
        this.ip = ip;
        this.name = name;
        this.description = description;
    }
    
    public User(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public IPAddress getIP() {
        return this.ip;
    }
    
    public void setIP(IPAddress ip) {
        this.ip = ip;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Element getXML() {
        Element user = new Element("user");
        Element name = new Element("name");
        Element description = new Element("description");
        name.setText(this.name);
        description.setText(this.description);
        user.addContent(name);
        user.addContent(description);
        user.setAttribute("ip", this.ip.toString());
        return user;
    }
    
    @Override
    public String toString(){
        return name;
    }

    /**
     * Parses <code>element</code> to create a User out of it.
     * @param element A Xml element representing a User.
     * @return The User represented by <code>element</code>
     * @throws StoreException If <code>element</code> does not represent a
     * User.
     */
    public static User readXML(Element element) throws StoreException {
        if(element == null || ! "user".equals(element.getName())) {
            throw new StoreException("Error parsing element as user.");
        }
        Element name = element.getChild("name");
        Element description = element.getChild("description");
        String ipString = element.getAttributeValue("ip", "0.0.0.0");
        return new User(new IPAddress(ipString), name.getText(), description.getText());
    }

    @Override
    public File getLocation() {
        return new File(getStorageRoot(), "user_" + ip.toString() + ".hgedata");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.ip != other.ip && (this.ip == null || !this.ip.equals(other.ip))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.ip != null ? this.ip.hashCode() : 0);
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

}
