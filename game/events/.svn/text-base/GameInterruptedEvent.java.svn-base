package hawkge.game.events;

import hawkge.game.GameID;
import hawkge.storage.User;
import java.util.List;

/**
 * Extensie van NetworkEvent die aan te geven dat een speler het spel verlaten heeft. 
 * De host verstuurd dit event om aan zijn medespelers te laten weten dat deze persoon 
 * uit het model met spelers moet verwijderd worden.
 * @author michaelkint
 */
public class GameInterruptedEvent extends GameNetworkEvent {

    private User quitter;
    private long duration;

    /** @param quitter De persoon die het spel verlaten heeft. 
     @param destination De persoon die zijn model moet aanpassen en de verlater verwijderen. **/
    public GameInterruptedEvent(User quitter, List<User> destinations, GameID id, long duration) {
        super(destinations,id);
        this.quitter = quitter;
    }

    /** @return De gebruiker die het spel verlaten heeft. **/
    public User getQuitter() {
        return quitter;
    }
    
    public long getDuration(){
        return duration;
    }
}
