package hawkge.game.events;

import hawkge.game.GameID;
import hawkge.storage.User;

/**
 *
 * @author michaelkint
 */
public class JoinGameEvent extends GameNetworkEvent {
    
    private User sender;
    
    public JoinGameEvent(User host, User sender, GameID id){
        super(host,id);
        this.sender = sender;
    }
    
    public User getSender(){
        return sender;
    }
}
