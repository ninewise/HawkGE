package hawkge.game.events;

import hawkge.event.NetworkEvent;
import hawkge.game.GameSessionInfo;
import hawkge.storage.User;

/**
 * Event die naar een bepaalde persoon gestuurd wordt om te vragen aan die 
 * persoon deel te nemen aan het huidige spel, waarvan de gegevens worden
 * bijgehouden in het GameModel dat ook verstuurd wordt via dit event.
 * @author michaelkint
 */
public class GameInvitationRequestEvent extends NetworkEvent {
    
    private GameSessionInfo info; // Het model met gegevens voor deze specifieke spelsessie
    
    /** 
     @param destination De persoon die uitgenodigd wordt voor de spelsessie. 
     @param model Het GameModel waarin de gegevens over de huidige spelsessie bevat zijn **/
    public GameInvitationRequestEvent(User destinations, GameSessionInfo info){
        super(destinations);    
        this.info = info;
    }

    /** @return Het GameModel waarin de gegevens voor de huidige spelsessie bevat zijn. **/
    public GameSessionInfo getGameSessionInfo() {
        return info;
    }
}
