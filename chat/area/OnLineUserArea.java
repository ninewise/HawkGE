/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.area;

import hawkge.chat.model.OnLineUserModel;
import hawkge.storage.User;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Sels
 */
public class OnLineUserArea extends JTextArea implements ChangeListener {

    /*
     * Toont de lijst met online gebruikers
     */

    private OnLineUserModel model;
    
    public OnLineUserArea(OnLineUserModel model){
        model.addChangeListener(this);
        this.model = model;
        this.setEditable(false);
        refreshOnlineUsers();
    }

    private void createNewLine(){
        this.append("\n");
    }
    
    
    private void refreshOnlineUsers(){        
       clearArea();
        for ( User user: model.getUsers()){
            this.append(user.getName());
            createNewLine();
        }
    }
    
    private void clearArea(){
        this.setText("");
    }
    
    public void stateChanged(ChangeEvent e) {
        refreshOnlineUsers();
    }
}
