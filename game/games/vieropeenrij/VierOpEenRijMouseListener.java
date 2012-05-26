import hawkge.game.models.GameModel;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author michaelkint
 */
public class VierOpEenRijMouseListener implements MouseListener {
    
    private GameModel model;
    
    public VierOpEenRijMouseListener(GameModel model){
        this.model = model;
    }

    public void mouseClicked(MouseEvent me) {
        /** Enkel de actieve user mag een zet doen **/
        if(model.isActive() && model.getOwnUser().equals(model.getActiveUser())){
            Point point = new Point(((me.getX()+30)/40 - 1),((me.getY()+30)/40 - 1));
            if(!((VierOpEenRijGameModel)model).getPos(point)){
                model.send(point);         
                ((VierOpEenRijGameModel)model).fill(point);
            }
        }
    }

    public void mousePressed(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {}
    public void mouseExited(MouseEvent me) {}
}
