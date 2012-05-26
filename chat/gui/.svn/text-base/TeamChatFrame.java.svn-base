/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.gui;

import hawkge.chat.actions.AddFriendAction;
import hawkge.chat.actions.AddPersonToTeamChatAction;
import hawkge.chat.actions.SelectColorAction;
import hawkge.chat.actions.SelectFontAction;
import hawkge.chat.chatsession.GameChatSession;
import hawkge.chat.chatsession.TeamChatSession;
import hawkge.chat.listener.ChatWindowListener;
import hawkge.chat.model.FontSelectorModel;
import hawkge.chat.model.TeamChatModel;
import hawkge.chat.model.TextColorModel;
import hawkge.event.EventQueue;
import hawkge.network.IPAddress;
import hawkge.storage.User;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author Sels
 */
public class TeamChatFrame extends JFrame {

    /*
     * Maakt de frame aan van een chatsessie
     */
    public TeamChatFrame(TeamChatModel chatModel) {

        super("ChatFrame");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        TextColorModel colorModel = new TextColorModel();
        FontSelectorModel selectorModel = new FontSelectorModel();

        JToolBar bar = new JToolBar();
        bar.add(new JButton(new SelectColorAction(colorModel, chatModel)));
        bar.add(new JButton(new SelectFontAction(selectorModel, chatModel)));
        bar.add(new JButton(new AddPersonToTeamChatAction(chatModel)));
        bar.setFloatable(false);
        
        panel.add(bar, BorderLayout.NORTH);
        panel.add(new TeamChatSession(chatModel, colorModel, selectorModel));
        
        setContentPane(panel);
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new ChatWindowListener(chatModel, this));

    } 
}
