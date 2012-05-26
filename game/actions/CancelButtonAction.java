package hawkge.game.actions;

import hawkge.game.gui.GameEnvironment;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author michaelkint
 */
public class CancelButtonAction extends AbstractAction {
 
    private GameEnvironment environment;
    
    public CancelButtonAction(GameEnvironment environment){
        super("Cancel");
        this.environment = environment;
    }

    public void actionPerformed(ActionEvent ae) {
        environment.dispose();
    }
}
