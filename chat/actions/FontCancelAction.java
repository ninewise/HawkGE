/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.actions;

import hawkge.chat.font.FontSelectorFrame;
import hawkge.chat.model.FontSelectorModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Sels
 */
public class FontCancelAction extends AbstractAction {

    private FontSelectorFrame frame;
    private FontSelectorModel selectorModel;
    
    public FontCancelAction(FontSelectorModel selectorModel,FontSelectorFrame frame){
        super("Cancel");
        this.frame = frame;
        this.selectorModel = selectorModel;
    }
    
    public void actionPerformed(ActionEvent e) {
        selectorModel.cancelChanged();
        frame.dispose();
    }
}
