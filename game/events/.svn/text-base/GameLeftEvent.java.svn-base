package hawkge.game.events;

import hawkge.game.GameID;
import hawkge.storage.User;

/**
 * Verlaat een game wanneer hij is afgelopen
 * @author michaelkint
 */
public class GameLeftEvent extends GameNetworkEvent {
    
    private User sender;
    
    public GameLeftEvent(User sender, User destination, GameID id){
        super(destination, id);
        this.sender = sender;
    }
    
    public User getSender(){
        return sender;
    }
}
