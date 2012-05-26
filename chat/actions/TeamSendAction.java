/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.actions;

import hawkge.chat.area.ChatFieldArea;
import hawkge.chat.area.TeamChatFieldArea;
import hawkge.chat.chatsession.ChatMessage;
import hawkge.chat.model.FontSelectorModel;
import hawkge.chat.model.TeamChatModel;
import hawkge.chat.model.TextColorModel;
import hawkge.storage.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JCheckBox;

/**
 *
 * @author Sels
 */
public class TeamSendAction extends AbstractAction {

    private TeamChatFieldArea field;
    private TeamChatModel model;
    private TextColorModel colorModel;
    private FontSelectorModel selectorModel;
    private JCheckBox teamBox;

    /*
     * Deze actie zorgt voor het afhandelen als de gebruiker een nieuwe bericht
     * wil verzenden
     */
    public TeamSendAction(TeamChatFieldArea field, TeamChatModel model, TextColorModel colorModel, FontSelectorModel selectorModel, JCheckBox teamBox) {
        super("Send");
        this.field = field;
        this.model = model;
        this.colorModel = colorModel;
        this.selectorModel = selectorModel;
        this.teamBox = teamBox;
    }

    /*
     * Nog aanpassen voor kleur en font
     */
    public void actionPerformed(ActionEvent e) {

        User user = model.getOwnUser();
        Color color = colorModel.getResultColor();
        Font font = selectorModel.getResultFont();
        ArrayList<User> receivers = new ArrayList<User>();
        
        if (teamBox.isSelected()) {
            String text = field.getText();
            if (model.isUserInBlueTeam()) {
                receivers = model.getBlueUserModel().getUsers();
            } else if (model.isUserInRedTeam()) {
                receivers = model.getRedUserModel().getUsers();
            }
            
            ChatMessage message = new ChatMessage(text, user, receivers, font, color,true);
            model.sendTeamMessage(message);
            
        } else {
            String text = field.getText();
            receivers = model.getUsersInChat();
            ChatMessage message = new ChatMessage(text, user, receivers, font, color,false);
            model.sendMessage(message);
        }
        
        field.setText("");

    }
}
