package hawkge.game;

import hawkge.storage.User;
import java.io.Serializable;
import java.util.List;

/**
 * Klasse die gegevens over een spelbeurt bijhoudt, en over een netwerk verstuurd kan worden
 * zodat verschillende gebruikers van een game dezelfde instellingen kunnen initialiseren.
 * @author michaelkint
 */
public class GameSessionInfo implements Serializable {

    private int numberofusers; // het aantal gebruikers dat het spel nodig heeft om te starten.
    private User host; // De host van het spel, persoon die het spel heeft aangemaakt.
    private String game; // Game waarvoor de speler is uitgenodigd.
    private GameID id;
    private List<User> fulluserlist;
    
    /** Constructor voor GameSessionInfo, klasse die gegevens bijhoudt over een spelsessie.
    @param users De huidige gebruikers in het spel. 
    @param numberofusers Het aantal users dat het spel nodig heeft om te starten. 
    @param lastusernumber Om bij te houden of een speler verwijderd of toegevoegd wordt.
    @param g Game waarvoor deze spelsessie dient. 
    @param numberofpendinginvitations Door de host verstuurde maar nog niet geaccepteerde/afgewezen invitaties. 
    @param host De persoon die het spel heeft aangemaakt. **/
    public GameSessionInfo(List<User> fulluserlist, int numberofusers, String game, User host, GameID id) {
        this.numberofusers = numberofusers;
        this.game = game;
        this.host = host;
        this.id = id;
        this.fulluserlist = fulluserlist;
    }

    public List<User> getFullUserList(){
        return fulluserlist;
    }
    
    /**@return Game waarvoor deze spelsessie dient. **/
    public String getGame() {
        return game;
    }

    /**@return Aantal users nodig om het spel te starten. **/
    public int getNumberofusers() {
        return numberofusers;
    }

    /** @return De host van deze spelsessie. **/
    public User getHost() {
        return host;
    }
    
    public GameID getID(){
        return id;
    }
    
    @Override
    public String toString(){
        return "Game : " + game + " Host : " + host + " Number of Players : " + numberofusers;
    }
}
