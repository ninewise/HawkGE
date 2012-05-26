/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.chatsession;

import hawkge.chat.model.ChatModel;
import hawkge.chat.pane.ChatEnterPane;
import hawkge.chat.area.ChatFieldArea;
import hawkge.chat.pane.ChatWindowPane;
import hawkge.chat.area.OnLineUserArea;
import hawkge.chat.pane.OnLineUserPane;
import hawkge.chat.gui.OnLineUsersLabel;
import hawkge.chat.actions.SendAction;
import hawkge.chat.actions.AddFriendAction;
import hawkge.chat.model.FontSelectorModel;
import hawkge.chat.model.TextColorModel;
import hawkge.chat.textpane.ChatWindowTextPane;
import javax.swing.GroupLayout;
import javax.swing.JButton;

/**
 *
 * @author Sels
 */
public class NormalChatSession extends ChatSession {

    /*
     * Maakt een paneel aan met alle componenten die in een normale chatsessie
     * zullen worden gebruikt.
     */
    public NormalChatSession(ChatModel model,TextColorModel colorModel,FontSelectorModel selectorModel) {
        super(model,colorModel,selectorModel);
        initializeLayout();
    }

    public final void initializeLayout() {
       
        ChatWindowTextPane chatwindow = new ChatWindowTextPane(chatmodel);
        
        ChatFieldArea chatenter = new ChatFieldArea(chatmodel,colorModel,selectorModel);            
        OnLineUserArea onlineusers = new OnLineUserArea(chatmodel.getUserModel());
        
        JButton sendButton = new JButton(new SendAction(chatenter,chatmodel,colorModel,selectorModel));
        JButton friendButton = new JButton(new AddFriendAction(chatmodel));
        
        OnLineUsersLabel users = new OnLineUsersLabel(chatmodel);
        
        ChatWindowPane windowpane = new ChatWindowPane(chatwindow);
        ChatEnterPane chatpane = new ChatEnterPane(chatenter);
        OnLineUserPane onlinepane = new OnLineUserPane(onlineusers);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

         layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(windowpane)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chatpane,GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(friendButton, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                    .addComponent(sendButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(onlinepane, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(users)
                        .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(users)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(windowpane, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(friendButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                            .addComponent(chatpane, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
                    .addComponent(onlinepane))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );


    }
}
