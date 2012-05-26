package hawkge.game.gui;

import hawkge.chat.model.ChatModel;
import hawkge.chat.chatsession.GameChatSession;
import hawkge.chat.model.FontSelectorModel;
import hawkge.chat.model.OnLineUserModel;
import hawkge.chat.model.TextColorModel;
import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.game.Game;
import hawkge.game.GameSessionInfo;
import hawkge.game.actions.GameSpaceWindowListener;
import hawkge.game.actions.InviteFriendAction;
import hawkge.game.models.GameModel;
import hawkge.game.actions.LeaveAction;
import hawkge.game.actions.ShowRulesAction;
import hawkge.game.events.PublicGameEvent;
import hawkge.network.events.OnlineUserListEvent;
import hawkge.storage.User;
import hawkge.storage.events.UserEvent;
import java.awt.BorderLayout;
import java.util.Collection;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Paneel waarin links het spelbord wordt getoond en rechts een gamespecifieke chat
 * (enkel met de spelers van de game).
 * @author michaelkint
 */
public class GameSpace extends JPanel implements ChangeListener {

    private Game g; // De geselecteerde Game
    private GameEnvironment environment; // Het Venster waarin dit paneel getoond wordt
    private User user; // De huidige gebruiker
    private GameView gameview; // Het paneel waarop het spelbord wordt getoond
    private GameChatSession gamechat; // Het paneel waarop gechat kan worden
    private int numberofplayers; // Het aantal spelers nodig om het huidige spel te starten
    private OnLineUserModel onlineusermodel; // Het model waarin de deelnemers van het huidige spel worden bijgehouden
    private JLabel leftlabel, activelabel;
    private boolean publicgame, newgame, waitCondition;
    private GameModel gamemodel; // Het gamemodel voor deze gamespace
    private final Object wait;

    /** Constructor voor een gamespace voor een nieuwe game
    @param environment Het GameEnvironment venster waarin dit paneel wordt ingeladen. 
    @param g De gekozen Game waarvoor dit paneel wordt ingeladen. 
    @param queue De EventQueue om over een netwerk te kunnen werken. 
    @param numberofplayers Het gekozen aantal spelers voor de nieuwe spelsessie
    @param publicgame Het feit of de gekozen game publiek of privaat is. **/
    public GameSpace(GameEnvironment environment, Game g, int numberofplayers, boolean publicgame) {
        super();
        wait = new Object();
        this.g = g;
        this.environment = environment;
        this.numberofplayers = numberofplayers;
        this.publicgame = publicgame;
        initLayout(null);
        setCondition(true);
        EventQueue.queue(new UserEvent(new Callable<User>() {
            public void call(User param) {
                setCurrentUser(param);
                synchronized (wait) {
                    setCondition(false);
                    wait.notifyAll();
                }
            }
        }));
        synchronized (wait) {
            try {
                while (waitCondition) {
                    wait.wait();
                }
            } catch (InterruptedException ex) {
                System.out.println("Waiting interrupt: " + ex);
            }
        }
    }

    /** Constructor voor een gamespace voor een reeds aangemaakte (en doorgestuurde game) 
    @param environment Het GameEnvironment venster waarin dit paneel wordt ingeladen. 
    @param queue De EventQueue om over een netwerk te kunnen werken.
    @param info Info voor de opgestarte gamesessie.  **/
    public GameSpace(GameEnvironment environment, GameSessionInfo info, Game g) {
        super();
        wait = new Object();
        this.g = g;
        this.environment = environment;
        this.numberofplayers = info.getNumberofusers();
        setCondition(true);
        EventQueue.queue(new UserEvent(new Callable<User>() {
            public void call(User param) {
                setCurrentUser(param);
                synchronized (wait) {
                    setCondition(false);
                    wait.notifyAll();
                }
            }
        }));
        synchronized (wait) {
            try {
                while (waitCondition) {
                    wait.wait();
                }
            } catch (InterruptedException ex) {
                System.out.println("Waiting interrupt: " + ex);
            }
        }
        initLayout(info);
    }

    /** Initialiseer de layout van het paneel. 
    @param model Het opgegeven gamemodel, al dan niet bestaand. **/
    private void initLayout(GameSessionInfo info) {
        int currentplayers;
        String activeplayer;
        newgame = (info == null);
        onlineusermodel = new OnLineUserModel();

        /** Start een nieuwe game. **/
        ChatModel gamechatmodel;
        if (info == null) {
            activeplayer = "Not started";
            currentplayers = 0;
            gamemodel = g.getGameModel(onlineusermodel, numberofplayers);
            if (newgame && publicgame) {
                warnOnlineFriendsForNewOnlineGame();
                synchronized (wait) {
                    try {
                        while (waitCondition) {
                            wait.wait();
                        }
                    } catch (InterruptedException ex) {
                        System.out.println("Waiting interrupt: " + ex);
                    }
                }
            }

        } /** Laadt een reeds bestaande game in. **/
        else {
            activeplayer = info.getHost().getName();
            gamemodel = g.getGameModel(onlineusermodel, info);
            currentplayers = gamemodel.getCurrentNumberOfPlayers();
            EventQueue.queue(new UserEvent(new Callable<User>() {

                public void call(User param) {
                    setCondition(true);
                    setCurrentUser(param);
                    synchronized (wait) {
                        setCondition(false);
                        wait.notifyAll();
                    }
                }
            }));
            synchronized (wait) {
                try {
                    while (waitCondition) {
                        wait.wait();
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Waiting interrupt: " + ex);
                }
            }
            for (User usr : info.getFullUserList()) {
                if (!user.equals(usr)) {
                    onlineusermodel.addUser(usr);
                }
            }
        }

        activelabel = new JLabel("Active player: " + activeplayer + "   ");
        if (numberofplayers - currentplayers != 0) {
            leftlabel = new JLabel("Waiting for players : " + (numberofplayers - currentplayers));
        } else {
            leftlabel = new JLabel("Game Started");
        }

        gamechatmodel = new ChatModel(gamemodel.getID().toString());
        gamechatmodel.setOnlineUserModel(onlineusermodel);

        /** De 2 panelen die onderdeel uitmaken van het GameSpace paneel. 
        Links bevindt zich het spelbord (gameview)
        Rechts bevindt zich het chatpaneel (gamechat) **/
        gameview = g.getGameView(gamemodel);
        gamechat = new GameChatSession(gamechatmodel, new TextColorModel(), new FontSelectorModel());
        gamemodel.addChangeListener(this);

        setLayout(new BorderLayout());
        environment.addWindowListener(new GameSpaceWindowListener(gamemodel));

        initToolBar(gamechatmodel, gamemodel);
        add(gameview, BorderLayout.WEST);
        add(gamechat, BorderLayout.CENTER);
    }

    /** Toolbar waarin de speler operaties kan uitvoeren zoals een speler inviten, 
    de spelregels lezen of het spel verlaten en terug gaan naar het gamesettingspaneel.
    @param gamechatmodel Het gebruikte ChatModel.
    @param gamemodel Het gebruikte GameModel. **/
    private void initToolBar(ChatModel gamechatmodel, GameModel gamemodel) {
        JToolBar toolbar = new JToolBar();
        toolbar.add(new JButton(new LeaveAction(environment, gamemodel)));
        toolbar.add(new JButton(new ShowRulesAction(g)));
        toolbar.add(new JButton(new InviteFriendAction(gamechatmodel, gamemodel)));
        toolbar.add(Box.createHorizontalGlue());
        toolbar.add(activelabel);
        toolbar.add(leftlabel);
        toolbar.setFloatable(false);

        add(toolbar, BorderLayout.NORTH);
    }

    /** Pas de huidige user aan.
    @param param De user die opgehaald werd via de EventQueue. **/
    private void setCurrentUser(User param) {
        this.user = param;
    }

    /** Haal de online friendlist op voor een user, zodat in het geval van
    een publieke game de gegevens over deze spelsessie in deze vrienden hun lobby
    kan worden getoond en zij dit spel kunnen joinen. **/
    private void warnOnlineFriendsForNewOnlineGame() {
        EventQueue.queue(new OnlineUserListEvent(new Callable<Collection<User>>() {

            public void call(Collection<User> users) {
                setCondition(true);
                warnFriends(users);
                synchronized (wait) {
                    setCondition(false);
                    wait.notifyAll();
                }
            }
        }));
    }

    private synchronized void setCondition(Boolean condition) {
        this.waitCondition = condition;
    }

    /** Wijzig de lijst met online friends voor een user. 
    @param users De lijst met friends die ingesteld dient te worden. **/
    private void warnFriends(Collection<User> users) {
        if (publicgame && newgame) {
            for (User usr : users) {
                EventQueue.queue(new PublicGameEvent(gamemodel.getGameSessionInfo(), usr, user));
            }
        }
    }

    /** Pas het label aan dat de spelers toont die nodig zijn om het huidige spel te starten.**/
    private void updatePlayersLeft() {
        if (leftlabel != null) {
            if (gamemodel.isInterrupted()) {
                leftlabel.setText("Game Interrupted");
            } else if (gamemodel.isFinished()) {
                leftlabel.setText("Game Finished");
            } else {
                if (!gamemodel.isActive()) {
                    int left = ((gamemodel.getRequiredNumberOfPlayers()) - gamemodel.getCurrentNumberOfPlayers());

                    if (left == 0) {
                        leftlabel.setText("Game Started");
                    } else {
                        leftlabel.setText("Waiting for players : " + left);
                    }
                } else {
                    leftlabel.setText("Game Started");
                }
            }
        }
        updateUI();
    }

    /** Pas het label aan die aangeeft welke speler aan zet is. **/
    private void updateActivePlayer() {
        if (activelabel != null) {
            activelabel.setText("Active player: " + gamemodel.getActiveUser() + "   ");
        }
    }

    /** Geef de minimale hoogte terug die dit paneel kan aannnemen. 
    @return De minimale hoogte van het paneel. **/
    public int getMinHeight() {
        return Math.max(gameview.getHeight() + 100, gamechat.getPanelHeight() + 100);
    }

    /** Geef de maximale hoogte terug die dit paneel kan aannemen.
    @return de minimale hoogte van het paneel. **/
    public int getMinWidth() {
        return gameview.getWidth() + gamechat.getPanelWidth() + 200;
    }

    /** Wat gebeurt er wanneer een speler aan het spel wordt toegevoegd/verwijderd; 
    Het label dat het aantal spelers toont die nodig zijn voor het spel te starten
    wordt gepast aangepast.
    @param ce De ChangeEvent die instaat voor de verandering.**/
    public void stateChanged(ChangeEvent ce) {
        if (gamemodel != null) {
            if (!gamemodel.isActive()) {
                updatePlayersLeft();
            } else {
                updateActivePlayer();
            }
        }
    }
}
