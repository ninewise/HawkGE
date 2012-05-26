/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.gui;

import hawkge.chat.model.TeamChatModel;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Sels
 */
public class OnLineUsersTeamLabel extends JLabel implements ChangeListener {
    /*
     * Label die het aantal gebruikers in de chat telt.
     */
    private TeamChatModel model;

    public OnLineUsersTeamLabel(TeamChatModel model) {
        super("Online users: ");
        this.model = model;
        model.getRedUserModel().addChangeListener(this);
        model.getBlueUserModel().addChangeListener(this);
        setAmountOfUsers();
    }

    private void setAmountOfUsers() {
        this.setText("Online users: " + model.getUsersInChat().size());
    }
    
    public void stateChanged(ChangeEvent e) {
       setAmountOfUsers();
    }
}
