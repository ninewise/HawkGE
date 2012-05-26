package hawkge.game.gui;

import hawkge.game.Game;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Venster waarin de regels voor het huidige spel worden weergeven. 
 * @author michaelkint
 */

public class GameRules extends JFrame {

    private Game g; // De game waarvoor de spelregels moeten worden getoond

    /** @param g De game waarvoor de spelregels moeten worden getoond. **/
    public GameRules(Game g) {
        super("Rules for " + g);
        this.g = g;
        initLayout();
    }

    /** Initialiseer de layout voor dit venster. **/
    private void initLayout() {
        JTextArea text = new JTextArea(" Rules for '" + g.getNaam() + "'\n\n" + g.getRules());
        text.setEditable(false);
        add(text);

        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(getWidth(), getHeight()));
    }
}
