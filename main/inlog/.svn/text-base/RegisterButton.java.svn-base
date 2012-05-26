package hawkge.main.inlog;

import hawkge.main.register.RegisterFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @create on Apr 27, 2012
 * @author jorisvi
 */
public class RegisterButton extends JButton implements ActionListener {
    
    private JFrame login;

    /**
     * 
     * @param queue an EventQueue object
     * @param login an JFrame object
     */
    public RegisterButton(JFrame login) {
        super("Register");
        addActionListener(this);
        this.login = login;
    }

    /**
     * Hide the loginframe and opens a new RegistrationFrame
     * @param e an ActionEvent object
     */
    public void actionPerformed(ActionEvent e) {
        login.setVisible(false);
        new RegisterFrame(login);
    }
}