/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.font;

import hawkge.chat.listener.FontWindowListener;
import hawkge.chat.model.ChatModel;
import hawkge.chat.model.FontSelectorModel;
import javax.swing.JFrame;

/**
 *
 * @author Sels
 */
public class FontSelectorFrame extends JFrame {
    
    private FontSelectorModel selectorModel;
    private ChatModel chatModel;
    
    public FontSelectorFrame(FontSelectorModel selectorModel,ChatModel chatModel){
        super("Select font");
        this.chatModel = chatModel;
        this.selectorModel = selectorModel;
        setContentPane(new FontSelectorPanel(selectorModel,this));
        setVisible(true);
        addWindowListener(new FontWindowListener(selectorModel,chatModel,this));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pack();
    }
}
