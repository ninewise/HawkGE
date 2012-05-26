package hawkge.main.register;

import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @create on Apr 28, 2012
 * @author jorisvi
 */
public class DescriptionArea extends JTextArea implements ChangeListener {
    
private RegisterModel model;

    /**
     * 
     * @param model an RegisterModel object
     */
    public DescriptionArea(RegisterModel model) {
        this.model = model;
        model.addChangeListener(this);
    }

    /**
     * Set the description field when the model change
     * @param e a ChangeEvent object
     */
    public void stateChanged(ChangeEvent e) {
        setText(model.getDescription());
    }    
}