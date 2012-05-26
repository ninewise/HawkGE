/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.event;

import hawkge.event.NetworkEvent;
import hawkge.storage.User;

/**
 *
 * @author Sels
 */
public class UserAddedToChat extends NetworkEvent {
    
    private User sender;
    private String modelID;
    
    
    /*
     * Dit event wordt gebruikt wanneer een gebruiker werd toegevoegd aan de chat 
     * en maakt aan alle huidige leden duidelijk dat er een nieuwe user moet
     * toegevoegd worden aan hun lijst.
     */
    
    public UserAddedToChat(User sender, User receiver, String modelID){
        super(receiver);
        this.modelID = modelID;
        this.sender = sender;        
    }
    
    public User getUser(){
        return sender;
    }
    
    public String getModelID(){
        return modelID;
    }
    
}
