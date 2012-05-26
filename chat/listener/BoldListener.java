/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.listener;

import hawkge.chat.font.BoldCheckBox;
import hawkge.chat.model.FontSelectorModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Alexander
 */
public class BoldListener implements ActionListener{

    private FontSelectorModel selectorModel;
    
    public BoldListener(FontSelectorModel selectorModel){
        this.selectorModel = selectorModel;
    }
   
    public void actionPerformed(ActionEvent ae) {
        BoldCheckBox boldBox = (BoldCheckBox) ae.getSource();
        if ( boldBox.isSelected()){
            selectorModel.setBoldSelected();
        }
        else{
            selectorModel.setBoldSelected();
        }
    }

}
