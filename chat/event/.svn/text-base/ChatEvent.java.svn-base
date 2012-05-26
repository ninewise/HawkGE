/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.event;

import hawkge.chat.chatsession.ChatMessage;
import hawkge.event.NetworkEvent;
import hawkge.network.IPAddress;
import hawkge.storage.User;
import java.util.ArrayList;

/**
 *
 * @author Sels
 */
public class ChatEvent extends NetworkEvent {
    
    private ChatMessage bericht;
    private User from;
    private String modelID;
    private boolean isTeamChat;
    
    /*
     * Deze klasse wordt gebruik om een event over het netwerk te sturen als
     * de gebruiker een chatbericht wil verzenden.
     */
    
    public ChatEvent(ChatMessage bericht, ArrayList<User> users, User from,String modelID,boolean isTeamChat) {
        super(users);
        this.bericht = bericht;
        this.from = from;
        this.modelID = modelID;
        this.isTeamChat = isTeamChat;
    }
    
    public ChatMessage getBericht() {
        return bericht;
    }
    
    public User getFrom() {
        return from;
    }
    
    public String getModelID(){
        return modelID;
    }
    public boolean isTeamChat(){
     return isTeamChat;   
    }
}