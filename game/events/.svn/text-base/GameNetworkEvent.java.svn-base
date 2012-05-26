/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.game.events;

import hawkge.event.NetworkEvent;
import hawkge.game.GameID;
import hawkge.storage.User;
import java.util.Collection;

/**
 *
 * @author michaelkint
 */
public abstract class GameNetworkEvent extends NetworkEvent {
    
    private GameID id;
    
    public GameNetworkEvent(User destination, GameID id){
        super(destination);
        this.id = id;
    }
    
    public GameNetworkEvent(Collection<User> destinations, GameID id){
        super(destinations);
        this.id = id;
    }
    
    public GameID getId(){
        return id;
    }
    
}
