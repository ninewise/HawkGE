/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.color;

import hawkge.chat.actions.ColorCancel;
import hawkge.chat.actions.ColorAccept;
import hawkge.chat.model.TextColorModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Sels
 */
public class ColorSliderPanel extends JPanel {

    public ColorSliderPanel(TextColorModel model,ColorFrame frame) {        
        setLayout(new GridLayout(0,1));        
        add(new RedSliderPanel(model));
        add(new GreenSliderPanel(model));
        add(new BlueSliderPanel(model));    
        
        JPanel panel = new JPanel();
       
        JButton accept = new JButton(new ColorAccept(model, frame));
        JButton cancel = new JButton(new ColorCancel(model, frame));
             
        accept.setPreferredSize(new Dimension(120,35));
        cancel.setPreferredSize(new Dimension(120,35));
        
        panel.add(accept);  
        panel.add(cancel);
        
        add(panel);
    }
}
