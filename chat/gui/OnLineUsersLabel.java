/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.gui;

import hawkge.chat.model.ChatModel;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Sels
 */
public class OnLineUsersLabel extends JLabel implements ChangeListener {
    /*
     * Label die het aantal gebruikers in de chat telt.
     */
    private ChatModel model;

    public OnLineUsersLabel(ChatModel model) {
        super("Online users: ");
        this.model = model;
        model.getUserModel().addChangeListener(this);
        setAmountOfUsers();
    }

    private void setAmountOfUsers() {
        this.setText("Online users: " + model.getUsersInChat().size());
    }
    
    public void stateChanged(ChangeEvent e) {
       setAmountOfUsers();
    }
}
