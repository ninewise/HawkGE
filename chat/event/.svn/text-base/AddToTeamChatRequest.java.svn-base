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
public class AddToTeamChatRequest extends NetworkEvent {
    private User sender;
    private User receiver;
    private ArrayList<User> usersInChat;
    private String modelID;
    private boolean blueTeam;
    
    /*
     * Dit event zal een event sturen naar de ontvanger met daarin een aanvraag
     * om een chat met de verstuurder te openen.
     */
    public AddToTeamChatRequest(User sender, User receiver, ArrayList<User> usersInChat,String modelID, boolean blueTeam) {
        super(receiver);
        this.sender = sender;
        this.receiver = receiver;
        this.usersInChat = usersInChat;
        this.modelID = modelID;
        this.blueTeam = blueTeam;
     }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public ArrayList<User> getFullUserlist() {
        return usersInChat;
    }
    public String getModelID(){
        return modelID;
    }
    
    public boolean shouldBeAddedToBlueTeam(){
        return blueTeam;
    }
}

