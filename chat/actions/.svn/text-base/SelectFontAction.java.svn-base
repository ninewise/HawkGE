/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.actions;

import hawkge.chat.font.FontSelectorFrame;
import hawkge.chat.model.ChatModel;
import hawkge.chat.model.FontSelectorModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Sels
 */
public class SelectFontAction extends AbstractAction {

    private FontSelectorModel selectorModel;
    private ChatModel chatModel;

    public SelectFontAction(FontSelectorModel selectorModel,ChatModel chatModel) {
        super("Choose Font");
        this.chatModel = chatModel;
        this.selectorModel = selectorModel;
    }

    public void actionPerformed(ActionEvent e) {
        FontSelectorFrame frame = new FontSelectorFrame(selectorModel,chatModel);
    }
}
