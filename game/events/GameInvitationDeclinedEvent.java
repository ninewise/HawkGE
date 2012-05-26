package hawkge.game.events;

import hawkge.game.GameID;
import hawkge.storage.User;
import java.util.Collection;

/**
 * Extensie van NetworkEvent om aan tegeven dat een geinviteerde gebruiker
 * de invitation declined en dus niet wenst deel te nemen aan een spel. 
 * @author michaelkint
 */
public class GameInvitationDeclinedEvent extends GameNetworkEvent {
    
    private User sender; // De user die de invitation declined heeft
    
    /** @param sender De gebruiker die de invitation declined. 
     @param destination De gebruiker die de invitation verstuurd had. **/
    public GameInvitationDeclinedEvent(User sender, Collection<User> destinations, GameID id){
        super(destinations,id);
        this.sender = sender;
    }
    
    public GameInvitationDeclinedEvent(User sender, User destination, GameID id){
        super(destination,id);
        this.sender = sender;
    }
    
    /** @return De gebruiker die de invitation declined. **/
    public User getSender(){
        return sender;
    }
}
