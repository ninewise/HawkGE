import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author michaelkint
 */
public class DammenMouseListener implements MouseListener {

    private DammenGameModel model;

    public DammenMouseListener(DammenGameModel model) {
        this.model = model;
    }

    public void mouseClicked(MouseEvent me) {
        if (model.getOwnUser().equals(model.getActiveUser()) && model.isActive()) {
            Point point = new Point(((me.getX() + 30) / 40 - 1), ((me.getY() + 30) / 40 - 1));
            if (model.getSelected() == null) {
                /** Er is nog geen punt geselecteerd **/
                if (model.ownItem((int) point.getX(),(int) point.getY())) {
                    if (!model.isSelected(point)) {
                        model.setSelected(point);
                    } else {
                        if (!model.zetMogelijk(point)) {
                            model.setSelected(null);
                        }
                    }
                }
            } else {
                /** Er is al een punt geselecteerd **/
                if (model.getPos(point) == 0) {
                    if ((model.mogelijkeZetten(model.getSelected()).contains(point))) {
                        model.send(new DammenMove(model.getSelected(),point));
                        model.move(model.getSelected(), point);
                    }
                } else if (model.ownItem((int) point.getX(),(int) point.getY()) && point.equals(model.getSelected())) {
                    if (model.mogelijkeZetten(model.getSelected()).isEmpty()) {
                        model.setSelected(null);
                    }
                }
            }
        }
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }
}
