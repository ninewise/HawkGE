/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.listener;

import hawkge.chat.color.ColorFrame;
import hawkge.chat.dialog.ColorChoosingDialog;
import hawkge.chat.model.ChatModel;
import hawkge.chat.model.TextColorModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Sels
 */
public class ColorWindowListener extends WindowAdapter {
    
    private TextColorModel colorModel;
    private ChatModel chatModel;
    private ColorFrame frame;
    
    public ColorWindowListener(TextColorModel colorModel,ChatModel chatModel, ColorFrame frame){
        this.colorModel = colorModel;
        this.chatModel = chatModel;
        this.frame = frame;
    }
    
    @Override
    public void windowClosing(WindowEvent event){
        ColorChoosingDialog dialog = new ColorChoosingDialog(chatModel.getOwnUser());
        if (dialog.getAnswer()){
            colorModel.acceptChanges();
        }
        else{
            colorModel.cancelChanges();           
        }
        frame.dispose();
    }
}
