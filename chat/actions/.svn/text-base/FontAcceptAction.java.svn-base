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
public class FontAcceptAction extends AbstractAction{
    
    private FontSelectorModel selectorModel;
    private FontSelectorFrame frame;
    
    public FontAcceptAction(FontSelectorModel selectorModel,FontSelectorFrame frame){
        super("Accept");
        this.frame = frame;
        this.selectorModel = selectorModel;
    }
    
    public void actionPerformed(ActionEvent e) {
        selectorModel.acceptChanged();
        frame.dispose();
    }
}
