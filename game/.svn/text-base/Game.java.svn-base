package hawkge.game;

import hawkge.game.models.GameModel;
import hawkge.chat.model.OnLineUserModel;
import hawkge.game.gui.GameView;
import java.io.Serializable;

/**
 * Abstracte klasse die gegevens van een spel bijhoudt
 * @author michaelkint
 */
public abstract class Game implements Serializable {

    protected String naam;
    protected int minspelers; // Het minimale aantal spelers dat aan deze game moet deelnemen
    protected int maxspelers; // Het maximale aantal spelers dat aan deze game kan deelnemen

    /** Constructor van de klasse die door spelontwikkelaars dient geimplementeerd te worden. 
    Houdt gegevens bij over de Game die ze willen maken, en linkt ook aan de GameView, het
    paneel dat ze moeten implementeren en dat zal weergeven worden, en aan het GameModel,
    het specifieke model voor deze Game.
    @param naam De naam van het spel.
    @param minspelers Het minimale aantal spelers om de game te kunnen spelen.
    @param maxspelers Het maximale aantal spelers om de game te kunnen spelen.**/
    public Game(String naam, int minspelers, int maxspelers) {
        this.naam = naam;
        this.minspelers = minspelers;
        this.maxspelers = maxspelers;
    }

    /** Het minimale aantal spelers die aan een specifiek spel kunnen meedoen. 
    @return Het minimale aantal spelers die aan een specifiek spel kunnen meedoen. **/
    public int getMinPlayers() {
        return minspelers;
    }

    /** Het maximale aantal spelers die aan een specifiek spel kunnen meedoen. 
    @return Het maximale aantal spelers die aan een specifiek spel kunnen meedoen. **/
    public int getMaxPlayers() {
        return maxspelers;
    }

    /** De naam van de Game. 
    @return De naam van de Game. **/
    public String getNaam() {
        return this.naam;
    }

    /**
     * Returns the class name.
     * @return The name of the game class.
     */
    public String getClassName() {
        return getClass().getSimpleName();
    }

    /** Dient door een spelontwikkelaar voorzien te worden, dit is
    het spelbord waarop gespeeld zal worden. 
    @return Het paneel met het spelbord. **/
    public abstract GameView getGameView(GameModel model);

    /** Geef de score voor een speler die op de aangegeven plaats geëindigd is.
    @param place De plaats waarop een speler geëindigd is.
    @return De score van de speler geëindigd op plaats place. **/
    public abstract int getScore(int place);

    /** Geef de spelregels van dit spel als String terug.
    @return De spelregels van het spel. **/
    public abstract String getRules();

    /** Geef het GameModel terug voor een nieuw spel. 
    @param onlineusermodel Het model met de online gebruikers. 
    @param numberofplayers Het aantal spelers voor de geselecteerde spelsessie. 
    @return Het GameModel dat voldoet aan de opgegeven vereisten. **/
    public abstract GameModel getGameModel(OnLineUserModel onlineusermodel, int numberofplayers);

    /** Geef het GameModel terug voor een reeds aangemaakte game. De gegevens voor deze game zitten in 
    het GameSessionInfo object.
    @param onlineusermodel Het model met de online gebruikers. 
    @param info Het GameSessionInfo object dat de gegevens over de aangemaakte spelsessie bevat.
    @return Het GameModel dat voldoet aan de opgegeven vereisten.**/
    public abstract GameModel getGameModel(OnLineUserModel onlineusermodel, GameSessionInfo info);

    /** Geeft als toString de naam van de Game terug. 
    @return De string die deze game voorstelt, voorgesteld door zijn naam. **/
    @Override
    public String toString() {
        return getClassName();
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Game){
            Game g = (Game) o;
            return getNaam().equals(g.getNaam()) && minspelers == g.getMinPlayers() && maxspelers == g.getMaxPlayers();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.getNaam() != null ? this.getNaam().hashCode() : 0);
        hash = 43 * hash + this.minspelers;
        hash = 43 * hash + this.maxspelers;
        return hash;
    }
}
