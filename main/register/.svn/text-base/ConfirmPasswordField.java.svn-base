package hawkge.main.register;

import java.awt.Dimension;
import javax.swing.JPasswordField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @create on Apr 27, 2012
 * @author jorisvi
 */
public class ConfirmPasswordField extends JPasswordField implements ChangeListener {
    
    private RegisterModel model;
    
    /**
     * 
     * @param model an RegisterModel object
     */
    public ConfirmPasswordField(RegisterModel model) {
        this.model = model;
        model.addChangeListener(this);
        setMaximumSize(new Dimension(120, 20));
        setPreferredSize(new Dimension(120, 20));
    }

    /**
     * Set the passwordfield when the model change
     * @param e an ChangeEvent object
     */
    public void stateChanged(ChangeEvent e) {
        setText(model.getPasswordConfirm());
    }

}