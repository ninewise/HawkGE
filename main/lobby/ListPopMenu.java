package hawkge.main.lobby;


import hawkge.main.lobby.actions.AddFriendAction;
import hawkge.main.lobby.actions.BlockFriendAction;
import hawkge.main.lobby.actions.DeblockFriendAction;
import hawkge.main.lobby.actions.RemoveFriendAction;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * @create on May 21, 2012
 * @author jorisvi
 */
public class ListPopMenu extends JPopupMenu {

    public ListPopMenu(JList list) {
        add(new JMenuItem(new AddFriendAction()));
        add(new JMenuItem(new RemoveFriendAction(list)));
        add(new JMenuItem(new BlockFriendAction(list)));
        add(new JMenuItem(new DeblockFriendAction(list)));
    }
}