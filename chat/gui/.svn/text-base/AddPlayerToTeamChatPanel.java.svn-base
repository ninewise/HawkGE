/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.gui;

import hawkge.chat.actions.AcceptAction;
import hawkge.chat.actions.AcceptTeamAction;
import hawkge.chat.actions.CancelTeamAction;
import hawkge.chat.model.TeamChatModel;
import hawkge.game.models.GameModel;
import hawkge.game.models.OnlineUserListModel;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Sels
 */
public class AddPlayerToTeamChatPanel extends JPanel {
    
    private TeamChatModel teamModel;
    private AddPlayerToTeamChatFrame frame;
    private GameModel gameModel;
    
    public AddPlayerToTeamChatPanel(TeamChatModel teamModel,AddPlayerToTeamChatFrame frame, GameModel gameModel){
        this.teamModel = teamModel;
        this.frame = frame;
        this.gameModel = gameModel;  
        initializeLayout();
        
    }
        private void initializeLayout() {

        JLabel text = new JLabel("Select users from the list:");
        JLabel explain = new JLabel("Choose team");
        
        JCheckBox box = new JCheckBox("Same team");
        box.setSelected(true);
        
        box.setToolTipText("To add a user to the other team, leave the box blanc. \n To add a user to your team, select the box.");
        //FriendListModel friendmodel = new FriendListModel(model.getFriendList());
        OnlineUserListModel listmodel = new OnlineUserListModel();
        
        JList list = new JList(listmodel);
        
        if(gameModel!=null) list.setSelectionMode(0);
        else list.setSelectionMode(1);
        
        list.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "OK");
        list.getActionMap().put("INVITE", new AcceptTeamAction(list, frame, teamModel, gameModel,box));

        list.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "CANCEL");
        list.getActionMap().put("CANCEL", new CancelTeamAction(frame));

        JScrollPane listpane = new JScrollPane(list);
        listpane.setMaximumSize(new Dimension(150, 300));

        JButton add = new JButton(new AcceptTeamAction(list, frame, teamModel, gameModel,box));
        add.setMinimumSize(new Dimension(70, 25));
        
        JButton cancel = new JButton(new CancelTeamAction(frame));
        cancel.setMinimumSize(new Dimension(70, 25));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(add)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(cancel)
                            )
                            
                        .addComponent(box,GroupLayout.Alignment.CENTER)
                        .addComponent(explain,GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(text, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(listpane)
                        )
                     )
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(text)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(listpane, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(explain)
                            .addComponent(box)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(add)
                                .addComponent(cancel)
                            )
                            .addContainerGap(14, Short.MAX_VALUE)
                        )
                 );
    }

}
