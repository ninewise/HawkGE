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
public class GreenSliderPanel extends JPanel {

    public GreenSliderPanel(TextColorModel model) {
        Border border = BorderFactory.createTitledBorder("Green");
        setBorder(border);
        GreenLabel green = new GreenLabel(model);
        GreenSlider greenSlider = new GreenSlider(model);        
        add(greenSlider);
        add(green);
    }
}
