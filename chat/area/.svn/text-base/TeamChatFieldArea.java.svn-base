/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.area;

import hawkge.chat.chatsession.ChatMessage;
import hawkge.chat.model.FontSelectorModel;
import hawkge.chat.model.TeamChatModel;
import hawkge.chat.model.TextColorModel;
import hawkge.storage.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

/**
 *
 * @author Sels
 */
public class TeamChatFieldArea extends JTextArea implements KeyListener {

    /*
     * Klasse voor de te verzenden tekst
     */
    private TeamChatModel model;
    private TextColorModel colorModel;
    private FontSelectorModel selectorModel;
    private JCheckBox teamSelector;

    public TeamChatFieldArea(TeamChatModel model, TextColorModel colorModel, FontSelectorModel selectorModel, JCheckBox teamSelector) {
        addKeyListener(this);
        this.colorModel = colorModel;
        this.selectorModel = selectorModel;
        this.model = model;
        this.teamSelector = teamSelector;
    }

    public void keyTyped(KeyEvent ke) {
    }

    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER && !ke.isShiftDown()) {
            clearText();
        }
    }

    /*
     * nog aanpassen voor kleur en font
     */
    public void keyPressed(KeyEvent ke) {
        if (ke.isShiftDown() && ke.getKeyCode() == KeyEvent.VK_ENTER) {
            newLine();
        } else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            User user = model.getOwnUser();
            Color color = colorModel.getResultColor();
            Font font = selectorModel.getResultFont();
            ArrayList<User> receivers = new ArrayList<User>();

            if (teamSelector.isSelected()) {
                String text = getText();
                if (model.isUserInBlueTeam()) {
                    receivers = model.getBlueUserModel().getUsers();
                } else if (model.isUserInRedTeam()) {
                    receivers = model.getRedUserModel().getUsers();
                }

                ChatMessage message = new ChatMessage(text, user, receivers, font, color, true);
                model.sendTeamMessage(message);

            } else {
                String text = getText();
                receivers = model.getUsersInChat();
                ChatMessage message = new ChatMessage(text, user, receivers, font, color, false);
                model.sendMessage(message);
            }
        }
    }

    private void clearText() {
        setText("");
    }

    private void newLine() {
        append("\n");
    }
}
