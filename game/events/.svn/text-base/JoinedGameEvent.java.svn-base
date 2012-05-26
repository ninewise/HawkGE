/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.game.events;

import hawkge.game.GameID;
import hawkge.storage.User;
import java.util.Collection;

/**
 *
 * @author michaelkint
 */
public class JoinedGameEvent extends GameNetworkEvent {
    
    private User joiner;
    
    public JoinedGameEvent(Collection<User> destinations, GameID id, User joiner){
        super(destinations, id);
        this.joiner = joiner;
    }
    
    public User getJoiner(){
        return joiner;
    }
    
}
