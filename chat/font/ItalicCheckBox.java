/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.font;

import hawkge.chat.model.FontSelectorModel;
import javax.swing.JCheckBox;

/**
 *
 * @author Sels
 */
public class ItalicCheckBox extends JCheckBox {
    
    public ItalicCheckBox(FontSelectorModel selectorModel){
        super("Italic");
        setSelected(selectorModel.italicSelected());
    }
}
