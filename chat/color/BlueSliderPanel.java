/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.color;

import hawkge.chat.model.TextColorModel;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Sels
 */
public class BlueSliderPanel extends JPanel {

    public BlueSliderPanel(TextColorModel model) {
        Border border = BorderFactory.createTitledBorder("Blue");
        setBorder(border);                
        BlueLabel blue = new BlueLabel(model);
        BlueSlider blueSlider = new BlueSlider(model);        
        add(blueSlider);
        add(blue);

    }
}
