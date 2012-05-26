/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.event;

import hawkge.event.NetworkEvent;
import hawkge.storage.User;
import java.util.ArrayList;

/**
 *
 * @author Sels
 */
public class UserLeavingChatEvent extends NetworkEvent {
    
    private User remove;
    private String modelID;
    
    public UserLeavingChatEvent(User remove, ArrayList<User> receivers,String modelID){
        super(receivers);
        this.remove = remove;
        this.modelID = modelID;
    }
    
    public User getUserToRemove(){
        return remove;
    }
    
    public String getModelID(){
        return modelID;
    }
}
