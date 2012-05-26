package hawkge.main.register;

import hawkge.main.IpModelInterface;
import hawkge.storage.addfriendframe.IPField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @create on Apr 28, 2012
 * @author jorisvi
 */
public class IPRegisterField extends IPField implements ChangeListener {
    
    private IpModelInterface model;

    /**
     * 
     * @param model a object that implements IpModelInterface
     */
    public IPRegisterField(IpModelInterface model) {
        super();
        this.model = model;
        model.addChangeListener(this);
    }

    /**
     * Set the text field the same as the model.
     * @param e a ChangeEvent object
     */
    public void stateChanged(ChangeEvent e) {
        if (model.getIp().equals("")) {
            clear();
        }
    }
}