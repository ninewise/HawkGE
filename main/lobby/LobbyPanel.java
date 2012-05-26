package hawkge.main.lobby;

import hawkge.game.PublicGameModel;
import hawkge.game.PublicGameRenderer;
import hawkge.game.actions.JoinGameAction;
import hawkge.main.lobby.actions.BlockFriendAction;
import hawkge.main.lobby.actions.DeblockFriendAction;
import hawkge.main.lobby.actions.RemoveFriendAction;
import hawkge.main.lobby.actions.UserStatAction;
import java.awt.Panel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

/**
 * @create on Apr 20, 2012
 * @author jorisvi
 */
public class LobbyPanel extends Panel {

    public LobbyPanel (JList userList) {
        super();

        // Preparation for games list.
        PublicGameModel listmodel = new PublicGameModel();
        JList publicGameList = new JList(listmodel);
        publicGameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        publicGameList.setCellRenderer(new PublicGameRenderer());

        // Making all components.
        JScrollPane userScrollPane = new JScrollPane(userList);
        JScrollPane gameScrollPane = new JScrollPane(publicGameList);
        JButton remove  = new JButton(new RemoveFriendAction(userList));
        JButton block   = new JButton(new BlockFriendAction(userList));
        JButton deblock = new JButton(new DeblockFriendAction(userList));
        JButton view    = new JButton(new UserStatAction(userList));
        JButton join    = new JButton(new JoinGameAction(listmodel,publicGameList));

        // Setting the layout.
        GroupLayout l = new GroupLayout(this);
        setLayout(l);

        l.setHorizontalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(userScrollPane)
                .addGroup(l.createSequentialGroup()
                    .addComponent(remove, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addComponent(block, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addComponent(deblock, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addComponent(view, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                )
            )
            .addGroup(l.createParallelGroup()
                //.addComponent(gameScrollPane, 100, GroupLayout.PREFERRED_SIZE, 300)
                //.addComponent(join, 0, GroupLayout.PREFERRED_SIZE, 300)
                .addComponent(gameScrollPane)
                .addComponent(join, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
            )
        );

        l.setVerticalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(userScrollPane)
                .addComponent(gameScrollPane)
            )
            .addGroup(l.createParallelGroup()
                .addComponent(remove)
                .addComponent(block)
                .addComponent(deblock)
                .addComponent(view)
                .addComponent(join)
            )
        );
    }
}