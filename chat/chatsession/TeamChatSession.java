/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.chatsession;

import hawkge.chat.actions.TeamSendAction;
import hawkge.chat.pane.ChatEnterPane;
import hawkge.chat.pane.ChatWindowPane;
import hawkge.chat.area.OnLineUserArea;
import hawkge.chat.area.TeamChatFieldArea;
import hawkge.chat.gui.OnLineUsersTeamLabel;
import hawkge.chat.pane.OnLineUserPane;
import hawkge.chat.model.FontSelectorModel;
import hawkge.chat.model.TeamChatModel;
import hawkge.chat.model.TextColorModel;
import hawkge.chat.textpane.TeamChatWindowTextPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 *
 * @author Sels
 */
public class TeamChatSession extends ChatSession {

    /*
     * Maakt een paneel aan met alle componenten die in een normale chatsessie
     * zullen worden gebruikt.
     */
    public TeamChatSession(TeamChatModel teamModel, TextColorModel colorModel, FontSelectorModel selectorModel) {
        super(teamModel, colorModel, selectorModel);
        initializeLayout();
    }

    public final void initializeLayout() {

        TeamChatWindowTextPane chatwindow = new TeamChatWindowTextPane(teamModel);
        
        JCheckBox box = new JCheckBox("Team");
        
        TeamChatFieldArea chatenter = new TeamChatFieldArea(teamModel, colorModel, selectorModel, box);
        OnLineUserArea onlineusers1 = new OnLineUserArea(teamModel.getBlueUserModel());
        OnLineUserArea onlineusers2 = new OnLineUserArea(teamModel.getRedUserModel());

        JLabel team1 = new JLabel("Team 1:");
        JLabel team2 = new JLabel("Team 2:");


        JButton sendButton = new JButton(new TeamSendAction(chatenter, teamModel, colorModel, selectorModel, box));

        OnLineUsersTeamLabel users = new OnLineUsersTeamLabel(teamModel);

        ChatEnterPane chatpane = new ChatEnterPane(chatenter);
        ChatWindowPane chatwindowpane = new ChatWindowPane(chatwindow);
        OnLineUserPane onlinepane1 = new OnLineUserPane(onlineusers1);
        OnLineUserPane onlinepane2 = new OnLineUserPane(onlineusers2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(chatpane, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                .addComponent(box, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(chatwindowpane))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(users)
                            .addComponent(team1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(onlinepane2, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(onlinepane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(team2)).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(19, 19, 19).addComponent(users).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addComponent(chatwindowpane, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(chatpane, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addGap(7, 7, 7).addComponent(box).addGap(18, 18, 18).addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))).addGroup(layout.createSequentialGroup().addComponent(team1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(onlinepane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(team2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(onlinepane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap(26, Short.MAX_VALUE)));
    }
}
