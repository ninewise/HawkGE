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
public class AddToTeamChatAccepted extends NetworkEvent {

    private User accepted;
    private String modelID;
    private boolean blueTeam;

    /*
     * De user is de persoon die geaccepteerd heeft om chat te starten De lijst
     * users zijn de personen die vervolgens de gebruiker aan hun chat zullen
     * moeten tevoegen, m.a.w, alle huidige personen in de chat
     */
    //user = de toe te voegen persoon aan het model van de users list
    //user moeten alle personen uit die lijst aan zijn model toevoegen0
    public AddToTeamChatAccepted(User accepted, ArrayList<User> users, String modelID, boolean blueTeam) {
        super(users);
        this.modelID = modelID;
        this.accepted = accepted;
        this.blueTeam = blueTeam;
    }

    public User getUser() {
        return accepted;
    }

    public String getModelID() {
        return modelID;
    }
    public boolean shouldBeInblueTeam(){
        return blueTeam;
    }
}
