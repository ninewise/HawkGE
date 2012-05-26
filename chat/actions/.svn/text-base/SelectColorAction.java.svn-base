/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.actions;


import hawkge.chat.color.ColorFrame;
import hawkge.chat.model.ChatModel;
import hawkge.chat.model.TextColorModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Sels
 */
public class SelectColorAction extends AbstractAction {

    private TextColorModel colorModel;
    private ChatModel chatModel;

    public SelectColorAction(TextColorModel colorModel,ChatModel chatModel) {
        super("Choose Color");
        this.colorModel = colorModel;
        this.chatModel = chatModel;
    }

    public void actionPerformed(ActionEvent e) {
        ColorFrame frame = new ColorFrame(colorModel,chatModel);
    }
}
