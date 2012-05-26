package hawkge.main.lobby;

import hawkge.chat.actions.TeamSendAction;
import hawkge.main.inlog.InlogModel;
import hawkge.main.lobby.actions.*;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @create on Apr 28, 2012
 * @author jorisvi
 */
public class LobbyMenuBar extends JMenuBar {
    
    private JList list;

    public LobbyMenuBar(JList friendsList, JFrame loginFrame, JFrame lobbyFrame, InlogModel inlogModel) {
        super();
        this.list = friendsList;
        setMenu(loginFrame, lobbyFrame, inlogModel);
        setChatMenu();
        setGame();
        setManageFriends();
        JMenu view = new JMenu("View");
        add(view);
        add(Box.createGlue());
        JMenu help = new JMenu("Help");
        add(help);
    }
    
    private void setMenu(JFrame loginFrame, JFrame lobbyFrame, InlogModel inlogModel) {
        JMenu menu = new JMenu("Menu");
        add(menu);
        JMenuItem logout = new JMenuItem(new LogoutAction(loginFrame, lobbyFrame, inlogModel));
        menu.add(logout);
        JMenuItem exit = new JMenuItem(new ExitAction());
        menu.add(exit);
    }
    
    private void setChatMenu() {
        JMenu chat = new JMenu("Chat");
        add(chat);
        JMenuItem chatsession = new JMenuItem(new StartChatAction());
        JMenuItem teamsession = new JMenuItem(new StartTeamChatAction());
        chat.add(chatsession);
        chat.add(teamsession);
    }
    
    private void setManageFriends() {
        JMenu manage = new JMenu("Account");
        add(manage);
        JMenuItem addFriend = new JMenuItem(new AddFriendAction());
        manage.add(addFriend);
        JMenuItem editSelf = new JMenuItem(new EditSelfAction());
        manage.add(editSelf);
        JMenuItem delete = new JMenuItem(new RemoveAccountAction());
        manage.add(delete);
    }
    
    private void setGame() {
        JMenu game = new JMenu("Game");
        add(game);
        JMenuItem gameSession = new JMenuItem(new StartGameAction());
        game.add(gameSession);
        JMenuItem addGame = new JMenuItem(new AddGameAction());
        game.add(addGame);
    }
}