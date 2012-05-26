package hawkge.game.events;

import hawkge.game.GameID;
import hawkge.storage.User;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author michaelkint
 */
public class GameStartedEvent extends GameNetworkEvent {
    
    private List<User> fulluserlist;
    
    public GameStartedEvent(Collection<User> destinations, GameID id, List<User> fulluserlist){        
        super(destinations,id);
        this.fulluserlist = fulluserlist;
    }
    
    public List<User> getFullUserList(){
        return fulluserlist;
    }
    
}
