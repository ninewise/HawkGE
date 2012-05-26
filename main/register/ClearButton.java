package hawkge.main.register;

import hawkge.main.ClearModelInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * @create on Apr 28, 2012
 * @author jorisvi
 */
public class ClearButton extends JButton implements ActionListener {
    
    private ClearModelInterface model;
    
    /**
     * 
     * @param model an object that implements ClearModelInterface object
     */
    public ClearButton(ClearModelInterface model) {
        super("clear fields");
        this.model = model;
        addActionListener(this);
    }

    /**
     * Clears al field that the model contains
     * @param e a ChangeEvent object
     */
    public void actionPerformed(ActionEvent e) {
        model.clearAllFields();
    }

}