/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.font;

import hawkge.chat.listener.ItalicListener;
import hawkge.chat.listener.BoldListener;
import hawkge.chat.listener.FontSelectionListener;
import hawkge.chat.listener.SizeSelectionListener;
import hawkge.chat.model.FontSelectorModel;
import hawkge.chat.actions.*;
import java.awt.GraphicsEnvironment;
import javax.swing.*;

/**
 *
 * @author Sels
 */
public class FontSelectorPanel extends JPanel {

    private Integer sizes[] = {8, 10, 11, 12, 14, 16, 18,
        20, 24, 30, 36, 40, 48, 60, 72};
    private String fonts[];

    public FontSelectorPanel(FontSelectorModel selectorModel, FontSelectorFrame frame) {
        initializeLayout(selectorModel, frame);
    }

    public void initializeLayout(FontSelectorModel selectorModel, FontSelectorFrame frame) {

        JButton apply = new JButton(new FontAcceptAction(selectorModel, frame));
        JButton cancel = new JButton(new FontCancelAction(selectorModel,frame));

        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        BoldCheckBox bold = new BoldCheckBox(selectorModel);
        bold.addActionListener(new BoldListener(selectorModel));

        ItalicCheckBox italic = new ItalicCheckBox(selectorModel);
        italic.addActionListener(new ItalicListener(selectorModel));

        FontPreviewLabel previewLabel = new FontPreviewLabel(selectorModel);

        FontList fontList = new FontList(fonts);
        selectorModel.setFontIndex(getIndexOfFont(selectorModel.getFont()));

        fontList.setSelectedIndex(selectorModel.getFontIndex());
        fontList.addListSelectionListener(new FontSelectionListener(selectorModel, fontList));

        SizeList sizeList = new SizeList(sizes);
        sizeList.setSelectedIndex(selectorModel.getSizeIndex());
        sizeList.addListSelectionListener(new SizeSelectionListener(selectorModel, sizeList));

        FontPane fontScroll = new FontPane(fontList);
        SizePane sizeScroll = new SizePane(sizeList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(apply, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(layout.createSequentialGroup().addComponent(fontScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(sizeScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(italic).addComponent(bold))).addComponent(previewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(10, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(sizeScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE).addComponent(fontScroll))).addGroup(layout.createSequentialGroup().addGap(68, 68, 68).addComponent(bold).addGap(18, 18, 18).addComponent(italic))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(previewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(apply, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE).addComponent(cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
    }

    public int getIndexOfFont(String name) {
        int counter = 0;
        boolean found = false;
        while (counter < fonts.length && !found) {
            if (fonts[counter].equals(name)) {
                found = true;
            } else {
                counter++;
            }
        }
        return counter;
    }
}
