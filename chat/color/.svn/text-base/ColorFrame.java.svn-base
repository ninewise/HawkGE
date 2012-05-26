/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.color;

import hawkge.chat.listener.ColorWindowListener;
import hawkge.chat.model.ChatModel;
import hawkge.chat.model.TextColorModel;
import javax.swing.JFrame;

/**
 *
 * @author Sels
 */
public class ColorFrame extends JFrame{
    
    public ColorFrame(TextColorModel colorModel,ChatModel chatModel){
        setTitle("Color chooser (Sels)");
        setContentPane(new ColorPanel(colorModel,this));
        setVisible(true);
        pack();
        addWindowListener(new ColorWindowListener(colorModel,chatModel, this));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
}
