/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.actions;

import hawkge.chat.color.ColorFrame;
import hawkge.chat.model.TextColorModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Sels
 */
public class ColorCancel extends AbstractAction {

    private TextColorModel colorModel;
    private ColorFrame frame;
    
    public ColorCancel(TextColorModel colorModel,ColorFrame frame){
        super("Cancel");
        this.colorModel = colorModel;
        this.frame = frame;
    }
    
    public void actionPerformed(ActionEvent e) {
        colorModel.cancelChanges();
        frame.dispose();
    }
    
}
