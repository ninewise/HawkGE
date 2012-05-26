package hawkge.game;

import java.io.Serializable;

/**
 *
 * @author michaelkint
 */
public class GameID implements Serializable {
    
    private Game g;
    private int randomint;
        
    
    public GameID(Game g, int randomint){
        this.g = g;
        this.randomint = randomint;
    }
    
    public Game getG(){
        return g;
    }
    
    public int getRandomInt(){
        return randomint;
    }
    
    @Override
    public String toString(){
        return "" + g + randomint;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof GameID){
            GameID gid = (GameID) o;
            return g.equals(gid.getG()) && randomint == gid.getRandomInt();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.g != null ? this.g.hashCode() : 0);
        hash = 67 * hash + this.randomint;
        return hash;
    }
    
}
