/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.chatsession;

import hawkge.chat.actions.SelectColorAction;
import hawkge.chat.actions.SelectFontAction;
import hawkge.chat.model.ChatModel;
import hawkge.chat.pane.ChatEnterPane;
import hawkge.chat.area.ChatFieldArea;
import hawkge.chat.pane.ChatWindowPane;
import hawkge.chat.area.OnLineUserArea;
import hawkge.chat.pane.OnLineUserPane;
import hawkge.chat.gui.OnLineUsersLabel;
import hawkge.chat.actions.SendAction;
import hawkge.chat.model.FontSelectorModel;
import hawkge.chat.model.TextColorModel;
import hawkge.chat.textpane.ChatWindowTextPane;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author Sels/Kint
 */
public class GameChatSession extends ChatSession {
    
    private int width;
    private int areaheight;
    private int chatheight;

    /*
     * Maakt het paneel aan met alle componenten van de chat die in een
     * game environment zal worden gebruikt
     */
    public GameChatSession(ChatModel chatModel,TextColorModel colorModel,FontSelectorModel selectorModel) {
        super(chatModel,colorModel,selectorModel);
        width = 370;
        areaheight = 280;
        chatheight = 85;
        initializeLayout();
    }

     public final void initializeLayout() {
       
        
        ChatWindowTextPane chatwindow = new ChatWindowTextPane(chatmodel);
        ChatFieldArea chatenter = new ChatFieldArea(chatmodel, colorModel,selectorModel);
        OnLineUserArea onlineusers = new OnLineUserArea(chatmodel.getUserModel());
        
        JButton sendButton = new JButton(new SendAction(chatenter,chatmodel, colorModel,selectorModel));
        
        OnLineUsersLabel users = new OnLineUsersLabel(chatmodel);
        
        //ScrollsPanes aanmaken voor de chatomgevenig
        ChatWindowPane windowpane = new ChatWindowPane(chatwindow);
        ChatEnterPane chatpane = new ChatEnterPane(chatenter);        
        OnLineUserPane onlinepane = new OnLineUserPane(onlineusers);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
    
        JToolBar bar = new JToolBar();
        
        bar.add(new JButton(new SelectColorAction(colorModel, chatmodel)));
        bar.add(new JButton(new SelectFontAction(selectorModel, chatmodel)));
        bar.setFloatable(false);
         
         layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(windowpane, GroupLayout.PREFERRED_SIZE, width, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chatpane)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sendButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(onlinepane, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                        .addComponent(bar)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(users)
                        .addGap(66, 66, 66))))
        );
         
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bar)
                .addComponent(users)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(windowpane, GroupLayout.PREFERRED_SIZE, areaheight, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, chatheight - 5, GroupLayout.PREFERRED_SIZE)
                            .addComponent(chatpane, GroupLayout.PREFERRED_SIZE, chatheight, GroupLayout.PREFERRED_SIZE)))
                    .addComponent(onlinepane))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
     }

        public int getPanelWidth(){
            return width + 20;
        }
        
        public int getPanelHeight(){
            return areaheight + chatheight + 20;
        }

}
