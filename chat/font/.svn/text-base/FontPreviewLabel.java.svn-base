/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.font;

import hawkge.chat.model.FontSelectorModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Sels
 */
public class FontPreviewLabel extends JLabel implements ChangeListener{
    private FontSelectorModel selectorModel;
    
    public FontPreviewLabel(FontSelectorModel selectorModel){
        super("Preview Text");
        setFont(selectorModel.getResultFont());
        this.selectorModel = selectorModel;
        selectorModel.addChangeListener(this);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public void stateChanged(ChangeEvent e) {
        setFont(new Font(selectorModel.getSelectedFont(),selectorModel.getBoldSelected()+selectorModel.getItalicSelected(), selectorModel.getSelectedSize()));
    }
}
