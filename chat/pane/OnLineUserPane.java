/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.pane;

import hawkge.chat.area.OnLineUserArea;
import java.awt.Dimension;
import javax.swing.JScrollPane;

/**
 *
 * @author Sels
 */
public class OnLineUserPane extends JScrollPane{
    
    /*
     * Maakt een ScrollPane aan voor het OnLineUserArea. 
     */
    
    public OnLineUserPane(OnLineUserArea userarea){
        super(userarea);
        setMinimumSize(new Dimension(50,355));
    }
    
}
