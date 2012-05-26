package hawkge.game.actions;

import hawkge.game.Game;
import hawkge.game.gui.GameRules;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Extensie van AbstractAction die aangeeft wat er gebeurt indien op een knop
 * met deze actie gedrukt wordt; Er wordt een venster getoond waarin de spelregels
 * voor een opgegeven game worden getoond. 
 * @author michaelkint
 */
public class ShowRulesAction extends AbstractAction {

    private Game g;
    private GameRules rules;

    public ShowRulesAction(Game g) {
        super("Show rules");
        this.g = g;
        rules = null;
    }

    /** Wanneer op de knop 'Show rules' wordt gedrukt, wordt het oude GameRules venster
    gesloten en een nieuw venster geopend. Zo kan, indien de spelregels aangepast
    werden, de nieuwe regels in het venster getoond worden. Op deze manier wordt 
    ook voorkomen dat meerdere GameRules vensters tegelijk worden geopend. **/
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (rules != null) rules.dispose(); 
        rules = new GameRules(g);
    }
}
