
package hawkge.game.events;

import hawkge.game.GameID;
import hawkge.storage.User;

/**
 *
 * @author felix
 */
public class GameFinishedEvent extends GameNetworkEvent {

    private int result;
    private int score;
    private User user;
    private boolean draw;
    private long duration;

    public GameFinishedEvent(User user, boolean draw, int result, int score, GameID id, long duration) {
        super(user,id);
        this.result = result;
        this.score = score;
        this.user = user;
        this.draw = draw;
        this.duration = duration;
    }

    /**
     * Returns the result of the game.
     * @return The result of the game. -1 means you lost or quit, 0 means a draw, and
     *      a 1 means you won.
     */
    public int getResult() {
        return result;
    }

    /**
     * Returns the score you had.
     * @return The score.
     */
    public int getScore() {
        return score;
    }
    
    public User getUser(){
        return user;
    }
    
    public boolean draw(){
        return draw;
    }
    
    public long getDuration(){
        return duration;
    }
}
