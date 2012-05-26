/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.model;

import hawkge.storage.User;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Sels
 */
public class FriendListModel extends DefaultListModel  {

    
    private ArrayList<User> users = new ArrayList<User>(); 
    
    /*
     * Model die gebruikt wordt voor de vrienden weer te geven in de JList.
     */
    
    public FriendListModel(ArrayList<User> users){
        this.users = users;
        initializeList();        
    }
    
    @Override
    public int getSize(){
        return users.size();
    }
    
    @Override
    public Object getElementAt(int index){
        return users.get(index);
    }
    
    private void initializeList(){        
        for ( User user: users){
            addElement(user);
        }
        fireContentsChanged(this, 0, users.size());   
    }    
}
