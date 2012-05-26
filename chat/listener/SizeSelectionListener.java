/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.listener;

import hawkge.chat.font.SizeList;
import hawkge.chat.model.FontSelectorModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Sels
 */
public class SizeSelectionListener implements ListSelectionListener {

    private FontSelectorModel selectorModel;
    private SizeList sizeList;

    public SizeSelectionListener(FontSelectorModel selectorModel, SizeList sizeList) {
        this.selectorModel = selectorModel;
        this.sizeList = sizeList;
    }

    public void valueChanged(ListSelectionEvent lse) {
        if (!lse.getValueIsAdjusting()) {
            selectorModel.setSelectedSize((Integer) sizeList.getSelectedValue());
            selectorModel.setSelectedSizeIndex(sizeList.getSelectedIndex());
        }
    }
}
