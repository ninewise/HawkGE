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
public class RedSliderPanel extends JPanel {

    public RedSliderPanel(TextColorModel model) {       
        Border border = BorderFactory.createTitledBorder("Red");
        setBorder(border);
        RedLabel red = new RedLabel(model);
        RedSlider redSlider = new RedSlider(model);
        add(redSlider);
        add(red);
    }
}
