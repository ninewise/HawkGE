/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.color;

import hawkge.chat.model.TextColorModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Sels
 */
public class ColorLabel extends JLabel implements ChangeListener {

    private TextColorModel model;

    public ColorLabel(TextColorModel model) {
        this.model = model;
        model.addChangeListener(this);   
        setLayout(new FlowLayout());
        setOpaque(true);
        setPreferredSize(new Dimension(50, 50));        
        Color color = new Color(model.getRedSelectedValue(), model.getSelectedGreenValue(), model.getSelectedBlueValue());       
        setBackground(color);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        setBorder(border);     
    }
    
    public void stateChanged(ChangeEvent e) {     
        Color color = new Color(model.getRedSelectedValue(), model.getSelectedGreenValue(), model.getSelectedBlueValue());
        setBackground(color);        
    }
}
