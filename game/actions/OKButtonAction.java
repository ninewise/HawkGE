package hawkge.game.actions;

import hawkge.game.Game;
import hawkge.game.gui.GameEnvironment;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

/**
 * Laadt een nieuw spel in (Verandert de inhoud van het GameEnvironment venster)
 * @author michaelkint
 */
public class OKButtonAction extends AbstractAction {

    private GameEnvironment environment;
    private JComboBox gamebox;
    private JSpinner spinner;
    private JRadioButton publicbox;

    public OKButtonAction(GameEnvironment environment, JComboBox model, JSpinner spinner, JRadioButton publicbox) {
        super("OK");
        this.environment = environment;
        this.gamebox = model;
        this.spinner = spinner;
        this.publicbox = publicbox;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (gamebox.getSelectedItem() != null) {
            environment.changeToGameSpacePanel((Game) gamebox.getSelectedItem(), (Integer) spinner.getModel().getValue(), publicbox.isSelected());
        } else {
            JOptionPane.showMessageDialog(null, "No Games installed. Please install a Game first.", "Start Game error.",0);
        }
    }
}
