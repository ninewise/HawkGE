package hawkge.main.register;

import java.awt.Dimension;
import javax.swing.JPasswordField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @create on Apr 27, 2012
 * @author jorisvi
 */
public class PasswordField extends JPasswordField implements ChangeListener {

    private RegisterModel model;

    /**
     * 
     * @param model an RegisterModel object
     */
    public PasswordField(RegisterModel model) {
        this.model = model;
        model.addChangeListener(this);
        setMaximumSize(new Dimension(120, 20));
        setPreferredSize(new Dimension(120, 20));
    }
    
    /**
     * Set the passwordfield when the model change
     * @param e a ChangeEvent object
     */
    public void stateChanged(ChangeEvent e) {
        setText(model.getPassword());
    }

}