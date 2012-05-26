package hawkge.game.events;

import hawkge.event.NetworkEvent;
import hawkge.storage.User;

/**
 *
 * @author michaelkint
 */
public class JoinRefusedEvent extends NetworkEvent {
    
    private String gamename;
    private User host;
    
    public JoinRefusedEvent(User destination, String gamename, User host){
        super(destination);
        this.gamename = gamename;
        this.host = host;
    }
    
    public User getHost(){
        return host;
    }
    
    public String getGame(){
        return gamename;
    }
}
