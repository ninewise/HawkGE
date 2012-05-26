/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.gui;

import hawkge.chat.actions.AcceptAction;
import hawkge.chat.actions.CancelAction;
import hawkge.chat.model.ChatModel;
import hawkge.game.models.GameModel;
import hawkge.game.models.OnlineUserListModel;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Sels
 */
public class AddFriendPanel extends JPanel {

    private ChatModel model;
    private AddFriendFrame frame;
    private GameModel gmodel;
    
    /*
     * Klasse die zorgt voor het aanmaken van een paneel met daarop de componenten
     * en keybinding die nodig zijn bij het AddFriendFrame.
     */

    public AddFriendPanel(ChatModel model, AddFriendFrame frame, GameModel gmodel) {
        this.model = model;
        this.frame = frame;
        this.gmodel = gmodel;
        initializeLayout();
    }

    private void initializeLayout() {

        JLabel text = new JLabel("Select users from the list:");

        //FriendListModel friendmodel = new FriendListModel(model.getFriendList());
        OnlineUserListModel listmodel = new OnlineUserListModel();
        
        JList list = new JList(listmodel);
        
        if(gmodel!=null) list.setSelectionMode(0);
        else list.setSelectionMode(1);
        
        list.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "OK");
        list.getActionMap().put("INVITE", new AcceptAction(list, frame, model, gmodel));

        list.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "CANCEL");
        list.getActionMap().put("CANCEL", new CancelAction(frame));

        JScrollPane listpane = new JScrollPane(list);
        listpane.setMaximumSize(new Dimension(150, 300));

        JButton add = new JButton(new AcceptAction(list, frame, model, gmodel));
        add.setMinimumSize(new Dimension(70, 25));
        
        JButton cancel = new JButton(new CancelAction(frame));
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
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(add)
                                .addComponent(cancel)
                            )
                            .addContainerGap(14, Short.MAX_VALUE)
                        )
                 );
    }
}
