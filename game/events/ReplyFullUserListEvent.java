package hawkge.game.events;

import hawkge.game.GameID;
import hawkge.storage.User;
import java.util.List;

/**
 *
 * @author michaelkint
 */
public class ReplyFullUserListEvent extends GameNetworkEvent {
    
    private List<User> fulluserlist;
    
    public ReplyFullUserListEvent(User destination, List<User> fulluserlist, GameID id){
        super(destination,id);
        this.fulluserlist = fulluserlist;
    }
    
    public List<User> getFullUserList(){
        return fulluserlist;
    }
}
