/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.listener;

import hawkge.chat.dialog.FontChoosingDialog;
import hawkge.chat.font.FontSelectorFrame;
import hawkge.chat.model.ChatModel;
import hawkge.chat.model.FontSelectorModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Sels
 */
public class FontWindowListener extends WindowAdapter{
    
    private FontSelectorModel selectionModel;
    private FontSelectorFrame frame;
    private ChatModel chatModel;
    
    public FontWindowListener(FontSelectorModel selectorModel,ChatModel chatModel, FontSelectorFrame frame){
        this.selectionModel = selectorModel;
        this.chatModel = chatModel;
        this.frame = frame;
    }
    
    @Override
    public void windowClosing(WindowEvent event){
        FontChoosingDialog dialog = new FontChoosingDialog(chatModel.getOwnUser());
        if (dialog.getAnswer()){
            selectionModel.acceptChanged();
        }
        else{
            selectionModel.cancelChanged();           
        }
        frame.dispose();
    }
}
