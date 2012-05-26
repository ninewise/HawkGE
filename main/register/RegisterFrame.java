package hawkge.main.register;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 * @create on Apr 27, 2012
 * @author jorisvi
 */
public class RegisterFrame extends JFrame implements WindowListener {
    
    private RegisterModel model;
    
    public RegisterFrame(JFrame login) {
        super();
        model = new RegisterModel(this, login);
        setContentPane(new RegisterPanel(model));
        setResizable(false);
        pack();
        addWindowListener(this);
        setVisible(true);
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        model.closeWindow();
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
}