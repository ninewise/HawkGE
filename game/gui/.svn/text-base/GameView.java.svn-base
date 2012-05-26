package hawkge.game.gui;

import hawkge.game.models.GameModel;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Spelafhankelijk paneel, dient ge-extend te worden door een spelontwikkelaar
 * @author michaelkint
 */
public abstract class GameView extends JPanel implements ChangeListener {

    public GameView(GameModel model) {
        super();
        model.addChangeListener(this);
    }

    /** Nodig opdat het de container van het GameSpace panel genoeg plek voorziet
    voor dit paneel.
    @return De dimensie van het spelbord. **/
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), getHeight());
    }

    /** Geef de hoogte van het paneel terug. 
    Dit is nodig om een gebruiksvriendelijke layout te presenteren op basis van het 
    door de spelontwikkelaar opgegeven afmetingen van het spelbord. 
    @return De hoogte van het GameView paneel. **/
    @Override
    public abstract int getHeight();

    /** Geef de breedte van het paneel terug. 
    Dit is nodig om een gebruiksvriendelijke layout te presenteren op basis van het 
    door de spelontwikkelaar opgegeven afmetingen van het spelbord. 
    @return De breedte van het GameView paneel. **/
    @Override
    public abstract int getWidth();

    /** Vang een verandering op.
    @param ce De ChangeEvent die de verandering beschrijft. **/
    public abstract void stateChanged(ChangeEvent ce);
}
