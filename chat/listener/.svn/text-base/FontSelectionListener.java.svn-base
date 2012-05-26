/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.listener;

import hawkge.chat.font.FontList;
import hawkge.chat.model.FontSelectorModel;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Alexander
 */
public class FontSelectionListener implements ListSelectionListener {

    private FontSelectorModel selectorModel;
    private FontList fontList;

    public FontSelectionListener(FontSelectorModel selectorModel, FontList fontList) {
        this.selectorModel = selectorModel;
        this.fontList = fontList;
    }

    public void valueChanged(ListSelectionEvent lse) {
        if ( !lse.getValueIsAdjusting()){
        selectorModel.setSelectedFontIndex(fontList.getSelectedIndex());
        selectorModel.setSelectedFont((String) fontList.getSelectedValue());
        }
    }
}
