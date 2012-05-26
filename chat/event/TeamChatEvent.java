package hawkge.chat.event;

import hawkge.chat.chatsession.ChatMessage;
import hawkge.storage.User;
import java.util.ArrayList;

/**
 * @create on May 11, 2012
 * @author jorisvi
 */
public class TeamChatEvent extends ChatEvent {
    
    private boolean blueTeam;
    
    public TeamChatEvent(ChatMessage bericht, ArrayList<User> users, User from,String modelID,boolean isTeamChat, boolean blueTeam) {
        super(bericht, users, from, modelID,isTeamChat);
        this.blueTeam = blueTeam;
    }

    public boolean isBlueTeam() {
        return blueTeam;
    }
    
}