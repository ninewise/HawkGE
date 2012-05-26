package hawkge.game.gui;

import hawkge.game.Game;
import hawkge.game.GameSessionInfo;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Klasse waarin de instellingen voor een spel gewijzigd kunnen worden.
 * Dit is een venster waarin bij opstarten het GameSettings paneel getoond wordt,
 * dat bij opstarten van een spel gewijzigd wordt naar een GameSpace paneel. 
 * De inhoud van het venster wordt dus gewijzigd
 * @author michaelkint
 */
public class GameEnvironment extends JFrame {

    private GameSettings settings;
    private GameSpace space;

    public GameEnvironment(boolean newgame) {
        super("HawkGE");
        if(newgame) initGameSettingsPanel();
        initLayout();
    }

    public GameEnvironment(GameSpace space, GameSessionInfo info) {
        super("HawkGE");
        initGameSpacePanel(space, info.getGame());
        initLayout();
    }

    /** Initialiseer de layout van het venster. **/
    private void initLayout() {
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);     
    }
    
    public void changeToGameSpacePanel(Game g, int numberofplayers, boolean publicgame){
        initGameSpacePanel(new GameSpace(this, g, numberofplayers, publicgame),g.getNaam());
    }
    
    public void changeToGameSpacePanel(Game g, GameSpace space){
        initGameSpacePanel(space,g.getNaam());
    }

    /** Wissel van GameSettings naar GameSpace paneel. 
    @param selectedgame Verander naar een Spelspecifiek paneel, afhankelijk van de gekozen game. 
    @param numberofplayers Het geselecteerde aantal spelers die aan de game meespelen. 
    @param gspace De GameSpace die moet ingeladen worden. **/
    private void initGameSpacePanel(GameSpace gspace, String selectedgame) {
        space = gspace;

        setContentPane(space);
        setTitle("HawkGE : " + selectedgame);

        setMinimumSize(new Dimension(space.getMinWidth(), space.getMinHeight()));
        setMaximumSize(new Dimension(space.getMinWidth(), space.getMinHeight()));
        setPreferredSize(new Dimension(space.getMinWidth(), space.getMinHeight()));
        setSize(new Dimension(space.getMinWidth(), space.getMinHeight()));

        repaint();
        space.revalidate();
        setResizable(false);         
        setVisible(true);
    }

    /** Wissel van GameSpace naar GameSettings paneel. **/
    public void initGameSettingsPanel() {
        settings = null;
        settings = new GameSettings(this);

        setContentPane(settings);
        setTitle("HawkGE");

        setMaximumSize(new Dimension(270, 200));
        setMinimumSize(new Dimension(270, 200));
        setPreferredSize(new Dimension(270, 200));
        setSize(new Dimension(270, 200));

        repaint();
        settings.revalidate();
        setResizable(false);      
        setVisible(true);
    }

    public void close() {
        dispose();
    }
}
