package hawkge.game.events;

import hawkge.game.Game;
import hawkge.game.GameSessionInfo;
import hawkge.storage.User;

/**
 *
 * @author michaelkint
 */
public class GameInfoEvent extends GameNetworkEvent {
    
    private GameSessionInfo info;
    private Game game;
    
    public GameInfoEvent(User destination, GameSessionInfo info, Game game){
        super(destination,info.getID());
        this.game = game;
        this.info = info;
    }
    
    public GameSessionInfo getGameSessionInfo(){
        return info;
    }
    public Game getGame(){
        return game;
    }
}
