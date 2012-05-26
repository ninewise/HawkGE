/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.userstats.events;

import hawkge.event.NetworkEvent;
import hawkge.storage.User;
import hawkge.storage.userstats.GameStat;
import java.util.Map;

/**
 * Contains the stuff for showing about's userstats.
 * @author felix
 */
public class UserStatEvent extends NetworkEvent {

    private final User about;
    private final Map<String, GameStat> games;

    public UserStatEvent(User requestor, User about, Map<String, GameStat> games) {
        super(requestor);
        this.about = about;
        this.games = games;
    }

    public User getAbout() {
        return about;
    }

    public Map<String, GameStat> getGames() {
        return games;
    }

}
