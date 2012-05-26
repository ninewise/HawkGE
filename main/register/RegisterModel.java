package hawkge.main.register;

import hawkge.Model;
import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.main.ClearModelInterface;
import hawkge.main.IpModelInterface;
import hawkge.main.NameModelInterface;
import hawkge.network.IPAddress;
import hawkge.storage.User;
import hawkge.storage.registration.Registrator;
import hawkge.storage.registration.events.CloseRegistratorEvent;
import hawkge.storage.registration.events.RegisterUserEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @create on Apr 27, 2012
 * @author jorisvi
 */
public class RegisterModel extends Model implements NameModelInterface, ClearModelInterface, IpModelInterface {

    private String name;
    private String password;
    private String passwordConfirm;
    private String description;
    private String ip;
    private JFrame registerFrame;
    private JFrame login;

    /**
     * 
     * @param registerFrame a JFrame object
     * @param queue an EventQueue object
     * @param login a JFrame object
     */
    public RegisterModel(JFrame registerFrame, JFrame login) {
        super();
        name = new String();
        password = new String();
        passwordConfirm = new String();
        ip = new String();
        description = new String();
        this.registerFrame = registerFrame;
        this.login = login;
    }

    /**
     * Returns the a String of the discription.
     * @return a string of the discription
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Set the description.
     * @param description a String object
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Returns the a String of the name.
     * @return a string of the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the a String of the password.
     * @return a string of the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password.
     * @param password a String object
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the a String of the confirm password.
     * @return a string of the confirm password
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * Set the confirm of the password.
     * @param passwordConfirm 
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    /**
     * Set the ip.
     * @param ip 
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    /**
     * Returns the a String of the ip
     * @return a string of the ip
     */
    public String getIp() {
        return ip;
    }
    
    /**
     * Activated when the registerbutton is pressed,
     * control checks of the fields.
     */
    public void register() {
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(registerFrame, "Fill in a username",
                    "NameField empty", JOptionPane.ERROR_MESSAGE);
        } else if (password.length() == 0 || passwordConfirm.length() == 0) {
            JOptionPane.showMessageDialog(registerFrame, "Fill in a password",
                    "PasswordField empty", JOptionPane.ERROR_MESSAGE);
        } else if (ip.length() == 0) {
            JOptionPane.showMessageDialog(registerFrame, "Fill a correct ip in",
                    "IPField empty", JOptionPane.ERROR_MESSAGE);
        } else if (password.equals(passwordConfirm)) {
            System.out.println(ip);
            EventQueue.queue(new RegisterUserEvent(name, password, new IPAddress(ip), description, new Callable<User>() {
                public void call(User user) {
                    EventQueue.queue(new CloseRegistratorEvent());
                    confirm(user);
                }                
            }));
            System.out.println("send register event"); // TODO Remove
        } else {
            JOptionPane.showMessageDialog(registerFrame, "Password doesn't match",
                    "Password fault", JOptionPane.ERROR_MESSAGE);
            password = "";
            passwordConfirm = "";
            fireStateChanged();
        }
    }
    
    /**
     * Clears allFields of the registerpanel.
     */
    public void clearAllFields() {
        name = "";
        password = "";
        passwordConfirm = "";
        ip = "";
        description = "";
        fireStateChanged();
    }
    
    /**
     * Called by an callable object to confirm if the user was registrate
     * @param user a User object
     */
    private void confirm(User user) {
        if (user != null) {
            JOptionPane.showMessageDialog(registerFrame, "Registration complete",
                    "Registration passed", JOptionPane.INFORMATION_MESSAGE);
            closeWindow();
        } else {
            JOptionPane.showMessageDialog(registerFrame, "Username already exists",
                    "Username wrong", JOptionPane.ERROR_MESSAGE);
            name = "";
            fireStateChanged();
        }
    }
    
    public void closeWindow() {
        registerFrame.dispose();
        new Registrator();
        login.setVisible(true);
    }
}