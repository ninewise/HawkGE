/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.color;

import hawkge.chat.model.TextColorModel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Sels
 */
public class GreenSlider extends JSlider implements ChangeListener {

    private TextColorModel model;

    public GreenSlider(TextColorModel model) {       
        this.model = model;    
        addChangeListener(this);
        setMaximum(100);
        setMinimum(0);
        setMinorTickSpacing(10);
        setMajorTickSpacing(20);
        setPaintTicks(true);
        getModel().setValue(model.getSelectedGreenIntValue());
    }

    public void stateChanged(ChangeEvent e) {
        model.setSelectedGreenValue(getValue());
        
    }
}

