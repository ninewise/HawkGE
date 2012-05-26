package hawkge.main.inlog;

import hawkge.main.NameModelInterface;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Hold a model to get the name
 * @create on Apr 27, 2012
 * @author jorisvi
 */
public class NameField extends JTextField implements ChangeListener {
    
    private NameModelInterface model;
    /**
     * 
     * @param model NameModelInterface
     */
    public NameField(NameModelInterface model) {
        super();
        this.model = model;
        model.addChangeListener(this);
        setMaximumSize(new Dimension(120, 20));
        setPreferredSize(new Dimension(120, 20));
    }

    /**
     * Change the textfield when name in the model change.
     * @param e a ChangeEvent object
     */
    public void stateChanged(ChangeEvent e) {
        setText(model.getName());
    }

}