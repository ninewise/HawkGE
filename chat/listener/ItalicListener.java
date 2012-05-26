/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.listener;

import hawkge.chat.font.ItalicCheckBox;
import hawkge.chat.model.FontSelectorModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Alexander
 */
public class ItalicListener implements ActionListener{

    private FontSelectorModel selectorModel;
    
    public ItalicListener(FontSelectorModel selectorModel){
        this.selectorModel = selectorModel;
    } 
        public void actionPerformed(ActionEvent ae) {
        ItalicCheckBox italicBox = (ItalicCheckBox) ae.getSource();
        if ( italicBox.isSelected()){
            selectorModel.setItalicSelected();
        }
        else{
            selectorModel.setItalicSelected();
        }
        
    }
}
