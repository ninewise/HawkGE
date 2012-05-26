package hawkge.game.events;

import hawkge.game.GameID;
import hawkge.storage.User;

/**
 *
 * @author michaelkint
 */
public class RequestFullUserListEvent extends GameNetworkEvent{
    
    private User sender;
    
    public RequestFullUserListEvent(User destination, GameID id, User sender){
        super(destination,id);
        this.sender = sender;
    }
    
    public User getSender(){
        return sender;
    }
}
