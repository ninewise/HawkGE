package hawkge.main.inlog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * InlogAction action for inlog button
 * @create on Apr 26, 2012
 * @author jorisvi
 */
public class InlogAction extends AbstractAction implements ActionListener {
    
    private JTextField name;
    private JTextField password;
    private InlogModel model;

    /**
     * 
     * @param name a JTextField for the name
     * @param password a JPasswordField for the password
     * @param model a InlogModel for the InlogModel
     */
    public InlogAction(JTextField name, JPasswordField password, InlogModel model) {
        super("Log in");
        this.name = name;
        this.password = password;
        this.model = model;
    }

    /**
     * Set the name and password in the of the model,
     * and calls the RequestLogin in the login model.
     * @param e an ActionEvent object
     */
    public void actionPerformed(ActionEvent e) {
        model.setName(name.getText());
        model.setPassword(password.getText());
        model.sendLoginRequest();
    }
}