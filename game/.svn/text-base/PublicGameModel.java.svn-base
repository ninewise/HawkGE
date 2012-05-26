package hawkge.game;

import hawkge.event.EventQueue;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 * Model voor een JList die online spelers toont
 * @author michaelkint
 */
public class PublicGameModel extends DefaultListModel {

    private ArrayList<GameSessionInfo> games;

    /** Constructoor voor een extensie van DefaultListModel, bedoeld voor Users. */
    public PublicGameModel() {
        games = new ArrayList<GameSessionInfo>();
        EventQueue.getQueue().addEventListener(new PublicGameListener(games, this));
    }

    /** @return Het aantal users in de lijst. **/
    @Override
    public int getSize() {
        if (games == null) {
            return 0;
        }
        return games.size();
    }

    @Override
    public int size() {
        return getSize();
    }

    /** @return Geef de User op index i terug. 
    @param i De index waarop de User dient afgehaald te worden. **/
    @Override
    public Object getElementAt(int i) {
        if (games == null) {
            return null;
        }
        return games.get(i);
    }
    
    public void fireIntervalAdded(int i1, int i2){
        fireIntervalAdded(this, i1, i2);
    }
    
    public void fireIntervalRemoved(int i1, int i2){
        fireIntervalRemoved(this, i1, i2);
    }
    
    public void fireContentsChanged(int i1, int i2){
        fireContentsChanged(this, i1, i2);
    }
}
