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
public class UserAddedToTeamChat extends NetworkEvent {

    private User sender;
    private String modelID;
    private boolean isInBlueTeam;

    /*
     * Dit event wordt gebruikt wanneer een gebruiker werd toegevoegd aan de
     * chat en maakt aan alle huidige leden duidelijk dat er een nieuwe user
     * moet toegevoegd worden aan hun lijst.
     */
    public UserAddedToTeamChat(User sender, User receiver, String modelID, boolean isInBlueTeam) {
        super(receiver);
        this.modelID = modelID;
        this.sender = sender;
        this.isInBlueTeam = isInBlueTeam;
    }

    public User getUser() {
        return sender;
    }

    public String getModelID() {
        return modelID;
    }
    
    public boolean isInBlueTeam(){
        return isInBlueTeam;
    }
}
