package hawkge.game.events;

import hawkge.game.GameID;
import hawkge.storage.User;
import java.util.Collection;

/**
 * Event die informatie over de gebruiker bijhoudt, wanneer een gebruiker
 * een invitation accepteert. 
 * Sender is de user die de invitation accepteert, destination is de user
 * die al in het spel zit, en zo te weten komt dat hij de nieuwe user
 * aan zijn model moet toevoegen.
 * @author michaelkint
 */
public class GameInvitationAcceptedEvent extends GameNetworkEvent {

    private User sender;

    /** @param sender De gebruiker die de invitation accepteert. 
    @param destination De gebruiker die reeds in het spel zit
    en de nieuwe gebruiker in zijn model moet toevoegen. **/
    public GameInvitationAcceptedEvent(User sender, Collection<User> destinations, GameID id) {
        super(destinations,id);
        this.sender = sender;
    }
    
    public GameInvitationAcceptedEvent(User sender, User destination, GameID id){
        super(destination,id);
        this.sender = sender;
    }

    /** @return De gebruiker die de invitation accepteert. **/
    public User getSender() {
        return sender;
    }
}
