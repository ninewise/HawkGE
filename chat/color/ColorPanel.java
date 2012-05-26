/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.color;

import hawkge.chat.model.TextColorModel;
import javax.swing.JPanel;

/**
 *
 * @author Sels
 */
public class ColorPanel extends JPanel {
    
    public ColorPanel(TextColorModel model,ColorFrame frame){       
        add(new ColorSliderPanel(model,frame));
        add(new ColorLabel(model));        
    }
}
