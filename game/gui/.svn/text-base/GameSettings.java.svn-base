package hawkge.game.gui;

import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.game.Game;
import hawkge.game.actions.CancelButtonAction;
import hawkge.game.models.GameComboBoxModel;
import hawkge.game.actions.OKButtonAction;
import hawkge.storage.gameloading.events.GameListEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

/**
 * Paneel waarop je instellingen voor een spel kan wijzigen.
 * @author michaelkint
 */
public class GameSettings extends JPanel implements ChangeListener {

    private JSpinner numberofplayers; // Spinner waarin de user het aantal spelers kan kiezen
    private JRadioButton privatebox, publicbox; // Radiobuttons waarbij de speler kan kiezen tussen een privaat of publiek spel
    private JComboBox gamebox; // ComboBox met games waaruit de speler kan kiezen
    private SpinnerNumberModel numberofplayersmodel; // Model die min en max bevat afhankelijk van de geselecteerde game
    private final Object wait;
    private boolean waitcondition;
    private List<Game> games;

    /** Constructor voor het paneel waarin de settings voor een Game kunnen worden 
    gekozen door de gebruiker
    @param environment Het GameEnvironment venster waarin dit paneel wordt getoond.
    @param user De huidige gebruiker. **/
    public GameSettings(GameEnvironment environment) {
        super();
        wait = new Object();
        initLayout(environment);
    }

    /* Initialiseer componenenten en orden ze in een grouplayout.
    @param environment Het Venster waarop dit paneel wordt geplaatst. */
    private void initLayout(GameEnvironment environment) {
        JLabel gamelabel = new JLabel("Game");
        JLabel numberofplayerslabel = new JLabel("Number of players");
        publicbox = new JRadioButton("Public");
        privatebox = new JRadioButton("Private");
        gamebox = new JComboBox();
        numberofplayers = new JSpinner();

        /* Combobox die de namen van alle games bevat */
        initGameBox();

        /* Spinner voor min en max aantal spelers te kiezen (afhankelijk van spel tot spel) */
        numberofplayersmodel = new SpinnerNumberModel();
        numberofplayers.setModel(numberofplayersmodel);
        updateSpinner();

        JButton okbutton = new JButton(new OKButtonAction(environment, gamebox, numberofplayers, publicbox));    
        JButton cancelbutton = new JButton(new CancelButtonAction(environment));

        /* Buttongroup om tussen publiek en wachtwoord te kiezen*/
        ButtonGroup group = new ButtonGroup();
        group.add(publicbox);
        group.add(privatebox);

        publicbox.setSelected(true);

        /* De grouplayout van de componenten */
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(privatebox).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(gamelabel).addComponent(numberofplayerslabel)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(24, 24, 24).addComponent(numberofplayers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))).addGroup(layout.createSequentialGroup().addGap(8, 8, 8).addComponent(gamebox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))).addComponent(publicbox).addGroup(layout.createSequentialGroup().addComponent(okbutton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE).addComponent(cancelbutton))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(gamelabel).addComponent(gamebox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(numberofplayerslabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(publicbox)).addComponent(numberofplayers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(14, 14, 14).addComponent(privatebox).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(okbutton).addComponent(cancelbutton)).addContainerGap()));
    }

    private void initGameBox() {
        setCondition(true);
        getGames();
        synchronized (wait) {
            try {
                while (waitcondition) {
                    wait.wait();
                }
            } catch (InterruptedException ex) {
                System.out.println("Waiting interrupt: " + ex);
            }
        }
        GameComboBoxModel gmodel = new GameComboBoxModel(games);
        gmodel.addChangeListener(this);
        gamebox.setModel(gmodel);
    }

    private synchronized void setCondition(Boolean condition) {
        this.waitcondition = condition;
    }

    /** Geef de games terug. Nodig voor het comboboxmodel in te vullen. **/
    private synchronized void getGames() {
        EventQueue.queue(new GameListEvent(new Callable<Collection<Game>>() {

            public void call(Collection<Game> games) {
                setGames(games);
                synchronized (wait) {
                    setCondition(false);
                    wait.notifyAll();
                }
            }
        }));
    }

    private void setGames(Collection<Game> games) {
        this.games = new ArrayList<Game>(games);
    }

    /** Geef het minimum aantal players voor de geselecteerde game terug.
    @return Het minimum aantal players voor de geselecteerde game. **/
    private int getMinNumberOfPlayers() {
        Game selected = (Game) gamebox.getSelectedItem();
        if (selected != null) {
            return selected.getMinPlayers();
        }
        return 0;
    }

    /** Geef het maximum aantal players voor de geselecteerde game terug.
    @return Het maximum aantal players voor de geselecteerde game. **/
    private int getMaxNumberOfPlayers() {
        Game selected = (Game) gamebox.getSelectedItem();
        if (selected != null) {
            return selected.getMaxPlayers();
        }
        return 0;
    }

    /** Update de spinner zodat het minimum en maximum aantal worden aangepast,
    afhankelijk van de huidige geselecteerde game. **/
    private void updateSpinner() {
        numberofplayersmodel.setMinimum(getMinNumberOfPlayers());
        numberofplayersmodel.setMaximum(getMaxNumberOfPlayers());
        numberofplayers.setValue(getMinNumberOfPlayers());
    }

    /** Dit paneel luistert naar het GameComboboxModel, zodat de
    spinner wordt aangepast wanneer een andere game wordt geselecteerd.
    @param ce De ChangeEvent **/
    @Override
    public void stateChanged(ChangeEvent ce) {
        updateSpinner();
    }
}
