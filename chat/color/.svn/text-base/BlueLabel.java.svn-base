/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.color;

import hawkge.chat.model.TextColorModel;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Sels
 */
public class BlueLabel extends JLabel implements ChangeListener{
   
    private TextColorModel model;
    
    public BlueLabel(TextColorModel model){
        super(String.format("%.2f",model.getSelectedBlueValue()));
        this.model = model;
        model.addChangeListener(this);
    }
    
    
    public void stateChanged(ChangeEvent e) {
        setText(String.format("%.2f",model.getSelectedBlueValue()));        
    }
}
