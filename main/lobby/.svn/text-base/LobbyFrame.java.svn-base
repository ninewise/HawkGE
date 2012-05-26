package hawkge.main.lobby;

import hawkge.event.EventQueue;
import hawkge.main.inlog.InlogModel;
import hawkge.main.lobby.list.LobbyCellRenderer;
import hawkge.main.lobby.list.LobbyListModel;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * @create on Apr 27, 2012
 * @author jorisvi
 */
public class LobbyFrame extends JFrame implements WindowListener {
    
    public LobbyFrame(String title, JFrame loginFrame, InlogModel inlogModel) {
        super(title);
        setResizable(true);
        JList friendslist = initFriendJList();
        setContentPane(new LobbyPanel(friendslist));
        setJMenuBar(new LobbyMenuBar(friendslist, loginFrame, this, inlogModel));
        pack();
        addWindowListener(this);
        setVisible(true);
    }
    
    private JList initFriendJList() {
        LobbyListModel lobbyListModel = new LobbyListModel();
        JList friendslist = new JList(lobbyListModel);
        friendslist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        friendslist.setCellRenderer(new LobbyCellRenderer());
        friendslist.setComponentPopupMenu(new ListPopMenu(friendslist));
        return friendslist;
    }
    
    public void windowOpened(WindowEvent e) {
        
    }

    public void windowClosing(WindowEvent e) {
        EventQueue.getQueue().close();
    }

    public void windowClosed(WindowEvent e) {
        
    }

    public void windowIconified(WindowEvent e) {
        
    }

    public void windowDeiconified(WindowEvent e) {
        
    }

    public void windowActivated(WindowEvent e) {
        
    }

    public void windowDeactivated(WindowEvent e) {
        
    }
}