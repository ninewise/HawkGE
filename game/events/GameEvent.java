
package hawkge.game.events;

import hawkge.game.GameID;
import hawkge.storage.User;
import java.io.Serializable;

/**
 * Event specifiek voor Game.
 * Het object dat wordt verstuurd mag de spelontwikkelaar zelf kiezen. 
 * De enige vereiste voor dit object is dat het serializable is, zodat 
 * het over het netwerk kan verstuurd worden. 
 * @author Kint
 */

public class GameEvent extends GameNetworkEvent {
    
    private Serializable object;
    private User from;
    
    /** @param object Het object dat wordt verstuurd
     @param users De users naar wie het GameEvent wordt verstuurd
     @param from De afzender van het Event. **/
    public GameEvent(Serializable object, User destination, User from, GameID id) {
        super(destination, id);  
        this.object = object;
        this.from = from;
       
    }   
    
    /** Geef het door de GameEvent verstuurde object terug. 
        @return Het door de GameEvent verstuurde object. **/
    public Serializable getObject() {
        return object;
    }
    
    /** Geef de afzender van de Event terug. 
        @return De afzender van de Event. */
    public User getFrom() {
        return from;
    }
}