package hawkge.main.register;

import hawkge.network.IPAddress;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @create on Apr 27, 2012
 *
 * @author jorisvi
 */
public class RegisterButton extends JButton implements ActionListener {

    private JTextField name;
    private JPasswordField password;
    private JPasswordField confirmPassword;
    private IPRegisterField ip;
    private JTextArea description;
    private RegisterModel model;

    /**
     *
     * @param name a JTextField object
     * @param password a JPasswordField object
     * @param confirmPassword a JPasswordField object
     * @param ip a IPRegisterField object
     * @param description a JTextArea object
     * @param model a RegisterModel object
     */
    public RegisterButton(JTextField name, JPasswordField password, JPasswordField confirmPassword,
            IPRegisterField ip, JTextArea description, RegisterModel model) {
        super("register");
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.ip = ip;
        this.description = description;
        this.model = model;
        addActionListener(this);
    }

    /**
     * Set every variables of the model.
     *
     * @param e an ActionEvent object
     */
    public void actionPerformed(ActionEvent e) {
        model.setName(name.getText());
        model.setPassword(new String(password.getPassword()));
        model.setPasswordConfirm(new String(confirmPassword.getPassword()));
        model.setDescription(description.getText());
        IPAddress ipString = ip.getIP();
        if (ipString != null) {
            model.setIp(ipString.toString());
        }
        model.register();
    }
}