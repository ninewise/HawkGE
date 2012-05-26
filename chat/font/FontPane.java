/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.font;

import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 *
 * @author Sels
 */
public class FontPane extends JScrollPane {
    
    public FontPane(FontList fontList){
        super(fontList);
    }
}
