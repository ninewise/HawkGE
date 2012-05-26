package hawkge.game.events;

import hawkge.event.NetworkEvent;
import hawkge.game.GameSessionInfo;
import hawkge.storage.User;
import java.util.Collection;

/**
 * Event om aan te geven dat een nieuwe publieke game is aangemaakt. 
 * Dit event bevat het GameModel, zodat info over de game in de lobby
 * kan getoond worden.
 * @author michaelkint
 */
public class PublicGameEvent extends NetworkEvent {

    private GameSessionInfo gamesessioninfo;
    private User user;
    
    /** Constructor voor een event dat naar een user zijn online friends wordt verstuurd, 
     om getoond te worden in de lobby. 
     @param model GameSessionInfo object met info over de aangemaakte spelsessie. 
     @param destinations Lijst met online vrienden van de user die de spelsessie heeft aangemaakt.
     @param user De user die de spelsessie heeft aangemaakt en dus het event verstuurd heeft. **/
    public PublicGameEvent(GameSessionInfo model, Collection<User> destinations, User host) {
        super(destinations);
        this.gamesessioninfo = model;
        this.user = host;
    }
    
    public PublicGameEvent(GameSessionInfo model, User destination, User host){
        super(destination);
        this.gamesessioninfo = model;
        this.user = host;
    }

    /** Geef de zender van het bericht terug, dit is degene die het spel heeft
    opgestart, de host van het spel dus. 
    @return De afzender van het Event, de host van het gespecifieerde spel. **/
    public User getHost() {
        return user;
    }

    /** Het GameModel van de nieuwe Game die is aangemaakt. Door dit GameModel
    op te vragen kan in de lobby informatie over de opgestarte gamesessie
    getoond worden (bijvoorbeeld over welk spel het gaat, hoeveel spelers
    nog nodig zijn om het spel te kunnen starten enzovoort. 
    @return Het GameModel van de opgestarte (publieke) Game. **/
    public GameSessionInfo getGameSessionInfo() {
        return gamesessioninfo;
    }
}
