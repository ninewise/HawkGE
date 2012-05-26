package hawkge.game.models;

import hawkge.Model;
import hawkge.chat.model.OnLineUserModel;
import hawkge.event.Callable;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.event.NetworkEvent;
import hawkge.game.Game;
import hawkge.game.GameID;
import hawkge.game.GameResult;
import hawkge.game.GameSessionInfo;
import hawkge.game.events.GameEvent;
import hawkge.game.events.GameFinishedEvent;
import hawkge.game.events.GameInfoEvent;
import hawkge.game.events.GameInterruptedEvent;
import hawkge.game.events.GameInvitationAcceptedEvent;
import hawkge.game.events.GameInvitationDeclinedEvent;
import hawkge.game.events.GameLeftEvent;
import hawkge.game.events.GameNetworkEvent;
import hawkge.game.events.GameStartedEvent;
import hawkge.game.events.JoinGameEvent;
import hawkge.game.events.JoinRefusedEvent;
import hawkge.game.events.JoinedGameEvent;
import hawkge.game.events.ReplyFullUserListEvent;
import hawkge.game.events.RequestFullUserListEvent;
import hawkge.network.events.ConnectFriendEvent;
import hawkge.network.events.OnlineUserListEvent;
import hawkge.storage.User;
import hawkge.storage.events.UserEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Abstracte klasse waarin gegevens over de huidige spelbeurt worden
 * bijgehouden.
 *
 * @author michaelkint
 */
public abstract class GameModel extends Model implements EventListener, ChangeListener {

    protected OnLineUserModel model; // Het model waarin de online spelers worden bijgehouden
    private int numberofusers, lastusernumber, numberofpendinginvitations;
    private User host, activeuser;
    protected User you; // De eigen speler
    private boolean isgameactive, finished, waitCondition, interrupted;
    private Game g; // De game waarvoor dit gamemodel staat
    private GameID id;
    private List<User> fulluserlist;
    private Collection<User> onlinefriends;
    private List<User> invited;
    private final Object wait;
    private long start, stop;

    /**
     * Constructor voor een nieuwe game
     *
     * @param model Het OnLineUserModel dat de spelers bijhoudt voor deze
     * spelsessie.
     * @param g De game waarvoor dit Model wordt aangemaakt.
     * @param numberofusers Het ingestelde aantal gebruikers waarmee gespeeld
     * dient te worden.
     */
    public GameModel(OnLineUserModel model, Game g, int numberofusers) {
        super();
        wait = new Object();
        this.model = model;
        this.numberofusers = numberofusers;
        this.g = g;
        lastusernumber = 0;
        invited = new ArrayList<User>();
        addListeners(model);
        id = new GameID(g, new Random().nextInt());
        getCurrentUserOnNewGame();
    }

    /**
     * Constructor voor een reeds bestaande game
     *
     * @param gamesession Info van de gamesession, de instellingen die de hos
     * @param g De gekozen game
     * @param Model Het OnLineUserModel dat de spelers bijhoudt voor deze
     * spelsessie.*
     */
    public GameModel(GameSessionInfo gamesession, Game g, OnLineUserModel model) {
        super();
        wait = new Object();
        this.model = model;
        this.g = g;
        this.activeuser = gamesession.getHost();
        this.host = gamesession.getHost();
        this.fulluserlist = gamesession.getFullUserList();
        this.numberofusers = gamesession.getNumberofusers();
        this.id = gamesession.getID();
        invited = new ArrayList<User>();
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
        addListeners(model);
        if (fulluserlist.size() == numberofusers) {
            setActive(true);
            fireStateChanged();
        } else {
            setCondition(true);
            EventQueue.queue(new RequestFullUserListEvent(host, id, you));
            synchronized (wait) {
                try {
                    while (waitCondition) {
                        wait.wait();
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Waiting interrupt: " + ex);
                }
            }
            if (fulluserlist.size() == numberofusers) {
                setActive(true);
                fireStateChanged();
            }
        }
    }

    /**
     * Ontvang data van het netwerk. Dit is de enige methode die
     * spelontwikkelaars dienen te implementeren : ze moeten zelf bepalen wat
     * met data (die ze zelf verstuurd hebben en dus aan de andere kant, bij de
     * tegenspeler terechtkomt ) moet gebeuren.
     *
     * @param data Het object (de data) die wordt ontvangen *
     */
    protected abstract void receive(Serializable data);

    /**
     * Geef een id terug dat de game uniek definieert, zo kunnen meerdere games
     * tegelijk gespeeld worden *
     */
    public GameID getID() {
        return id;
    }

    /**
     * Genereer een GameSessionInfo object waarin de gegevens over deze
     * spelsessie staan. Dit object bevat alle gegevens, en is Serializable. Dit
     * is het object dat over het netwerk wordt verstuurd bij het inviteren van
     * een gebruiker voor dit spel. Zo beschikt de uitgenodigde speler over alle
     * nodige informatie.
     *
     * @return Een Serializable GameSessionInfo object, dat alle gegevens over
     * de huidige spelsessie bevat en over een netwerk kan verstuurd worden. *
     */
    public GameSessionInfo getGameSessionInfo() {
        return new GameSessionInfo(getFullUserList(), getRequiredNumberOfPlayers(), g.getClassName(), host, id);
    }

    /**
     * Bij een nieuw spel worden 3 velden aangepast wanneer de user wordt
     * opgevraagd. Eerst en vooral wordt het veld 'you' aangepast, waarin de
     * eigen gebruiker staat. Daarnaast wordt ook het veld 'host' aangepast; de
     * speler die het spel aanmaakt is ook direct de host van dit spel. Ten
     * slotte wordt ook het veld 'activeuser' aangepast; bij het aanmaken van
     * een nieuw spel is het steeds de host die de eerste zet zal doen bij het
     * beginnen spelen. *
     */
    private void getCurrentUserOnNewGame() {
        EventQueue.queue(new UserEvent(new Callable<User>() {

            public void call(User param) {
                setCondition(true);
                setCurrentUserOnNewGame(param);
                synchronized (wait) {
                    setCondition(false);
                    wait.notifyAll();
                }
            }
        }));
    }

    /**
     * Pas de 3 velden aan bij het starten van een nieuw spel.
     *
     * @param user De huidige gebruiker.
     */
    private void setCurrentUserOnNewGame(User user) {
        this.you = user;
        this.host = user;
        this.activeuser = user;
    }

    /**
     * Pas het enkele veld aan met de eigen gebruiker bij het starten van een
     * game waarvoor de gebruiker is uitgenodigd.
     *
     * @param user De huidige gebruiker*
     */
    private void setCurrentUser(User user) {
        this.you = user;
    }

    /**
     * Haal voor de online user alle online friends op *
     */
    private void getOnlineFriends() {
        EventQueue.queue(new OnlineUserListEvent(new Callable<Collection<User>>() {

            public void call(Collection<User> users) {
                setCondition(true);
                initList(users);
                synchronized (wait) {
                    setCondition(false);
                    wait.notifyAll();
                }
            }
        }));
    }

    /**
     * Wijzig de lijst met online friends voor een user.
     *
     * @param users De lijst met friends die ingesteld dient te worden. *
     */
    private void initList(Collection<User> users) {
        this.onlinefriends = users;
    }

    /**
     * Voeg dit model toe als ChangeListener aan het model met de online users
     * en als EventListener aan de EventQueue.
     *
     * @param model Het OnLineUserModel met de online users.
     * @param queue De EventQueue. *
     */
    private void addListeners(OnLineUserModel model) {
        model.addChangeListener(this);
        EventQueue.getQueue().addEventListener(this);
    }

    /**
     * Geef de eigen gebruiker terug.
     *
     * @return De eigen gebruiker.*
     */
    public User getOwnUser() {
        return you;
    }

    /**
     * Start een nieuwe game. Indien de huidige user de host is, stuur een
     * melding naar de andere medespelers dat de game begonnen is. *
     */
    protected void startGame() {
        setActiveUser(getHost());
        setActive(true);

        setCondition(true);
        if (you != null && you.equals(host)) {
            getOnlineFriends();
            synchronized (wait) {
                try {
                    while (waitCondition) {
                        wait.wait();
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Waiting interrupt: " + ex);
                }
            }
            throwEvent(new GameStartedEvent(onlinefriends, getID(), getFullUserList()));
        }

        fireStateChanged();
    }

    private synchronized void setCondition(Boolean condition) {
        this.waitCondition = condition;
    }

    /**
     * Stop de game doordat iemand gewonnen of gedrawed heeft *
     */
    protected void stopGame() {
        finished = true;
        setActive(false);
        EventQueue.getQueue().removeEventListener(this);
        fireStateChanged();
    }

    /**
     * Stop de spelsessie omdat iemand gewonnen heeft
     */
    protected void stopFinishedGame() {
        stopGame();
        gameFinished();
        fireStateChanged();
    }

    protected void stopDrawedGame() {
        stopGame();
        gameDrawed();
        fireStateChanged();
    }

    /**
     * Werp een Event op de EventQueue.
     *
     * @param e Het NetworkEvent dat dient gegooid te worden. *
     */
    private void throwEvent(Event e) {
        EventQueue.queue(e);
    }

    /**
     * Zend data van de game over het netwerk naar de andere spelers.
     *
     * @param data Het object dat wordt verzonden.
     * @param destinations De tegenspelers aan wie het bedoeld is. *
     */
    public void send(Serializable data, List<User> destinations) {
        for (User usr : destinations) {
            throwEvent(new GameEvent(data, usr, getOwnUser(), id));
        }
        fireStateChanged();
    }

    /**
     * Stuur data naar je tegenspelers *
     */
    public void send(Serializable data) {
        send(data, getOtherPlayers());
        fireStateChanged();
    }

    /**
     * Voeg een user toe aan de lijst met invited personen
     *
     * @param user De invited user *
     */
    public void addInvited(User user) {
        invited.add(user);
    }

    /**
     * Controleer of een gebruiker reeds invited is
     *
     * @param user De te controleren gebruiker
     * @return Is de gespecifieerde gebruiker al dan niet reeds invited *
     */
    public boolean isInvited(User user) {
        return invited.contains(user);
    }

    public long getDuration() {
        return stop - start;
    }

    /**
     * Het huidige spel eindigt met een winnaar. Indien de huidige gebruiker de
     * actieve gebruiker was (de speler aan zet), heeft deze gewonnen. Indien
     * niet heeft deze verloren.*
     */
    protected void gameFinished() {
        if (you.equals(getActiveUser())) {
            throwEvent(new GameFinishedEvent(you, false, GameResult.WON.getResult(), g.getScore(1), id, getDuration()));
            JOptionPane.showMessageDialog(null, "Congratulations, you have won the game!");
        } else {
            throwEvent(new GameFinishedEvent(you, false, GameResult.LOST.getResult(), g.getScore(2), id, getDuration()));
            JOptionPane.showMessageDialog(null, activeuser + " has won the game!");
        }
        fireStateChanged();
    }

    /**
     * Het huidige spel eindigt op gelijkspel. *
     */
    protected void gameDrawed() {
        throwEvent(new GameFinishedEvent(you, true, GameResult.DRAW.getResult(), 0, id, getDuration()));
        JOptionPane.showMessageDialog(null, "Game ended in a draw.");
        fireStateChanged();
    }

    /**
     * @param active Verander het feit of het spel actief is of niet. *
     */
    public void setActive(boolean active) {
        if (active) {
            start = System.currentTimeMillis();
        } else {
            stop = System.currentTimeMillis();
        }
        this.isgameactive = active;
        fireStateChanged();
    }

    /**
     * Stel de actieve gebruiker in (de gebruiker die aan de beurt is).
     *
     * @param user De gebruiker die aan de buurt komt. *
     */
    protected void setActiveUser(User user) {
        activeuser = user;
        fireStateChanged();
    }

    /**
     * Ga naar de volgende speler, wiens beurt het is. Gebeurt nadat de huidige
     * speler een zet heeft gedaan. Dient door de spelontwikkelaar zelf
     * opgeroepen te worden om aan te geven wanneer de volgende gebruiker zijn
     * zet kan doen. *
     */
    protected void nextUsersTurn() {
        setActiveUser(getFullUserList().get((getFullUserList().indexOf(activeuser) + 1) % getFullUserList().size()));
        fireStateChanged();
    }

    /**
     * Wie is de host van dit huidige spel. De host is de persoon met het meeste
     * authoriteit. De host is bijvoorbeeld de enige persoon die mensen kan
     * inviten voor de game.
     *
     * @return De host van dit spel. *
     */
    public User getHost() {
        return host;
    }

    /**
     * Wat gebeurt er wanneer een speler is toegevoegd? Indien het opgegeven
     * aantal spelers is bereikt, wordt de methode startGame opgeroepen, die
     * door de spelontwikkelaar dient geimplementeerd te worden. *
     */
    private void userAdded() {
        if (model.getNumberOfUsers() + 1 == numberofusers) {
            startGame();
        }
        fireStateChanged();
    }

    /**
     * Geeft aan of het huidige spel reeds gestart is.
     *
     * @return Is het huidige spel actief of niet. *
     */
    public boolean isActive() {
        return isgameactive;
    }

    /**
     * Geef de actieve gebruiker terug (de speler die aan zet is)
     *
     * @return De actieve gebruiker. *
     */
    public User getActiveUser() {
        return activeuser;
    }

    /**
     * Geef de index terug van de huidige speler. Kan bijvoorbeeld nodig zijn
     * wanneer met kleuren wordt gewerkt, om op een eenvoudige manier de
     * gebruikers van elkaar te onderscheiden. Deze index is echter niet
     * hetzelfde in de modellen van de verschillende spelers.
     *
     * @return De 'index' van de actieve gebruiker. *
     */
    public int getActiveUserIndex() {
        return getFullUserList().indexOf(activeuser);
    }

    /**
     * Van welke game is dit GameModel het model.
     *
     * @return De Game waarvan dit GameModel het model is. *
     */
    public Game getGame() {
        return g;
    }

    /**
     * Geef het Model met de huidige spelers van het spel terug.
     *
     * @return Het OnLineUserModel met de huidige spelers van dit spel. *
     */
    public OnLineUserModel getUserModel() {
        return model;
    }

    /**
     * Geef het feit terug of een spel reeds afgelopen is of niet.
     *
     * @return Feit of het spel reeds is afgelopen. *
     */
    public boolean isFinished() {
        return finished;
    }

    public boolean isInterrupted() {
        return interrupted;
    }

    /**
     * Geef aan dat er invitation declined of accepted is *
     */
    public void decreaseNumberOfPendingInvitations() {
        numberofpendinginvitations++;
    }

    /**
     * Geef aan dat er een invitation verzonden is. *
     */
    public void increaseNumberOfPendingInvitations() {
        numberofpendinginvitations++;
    }

    /**
     * Geef het huidige aantal spelers in de Game terug.
     *
     * @return Het huidige aantal spelers in de Game. *
     */
    public int getCurrentNumberOfPlayers() {
        return getFullUserList().size();
    }

    /**
     * Geef het aantal spelers terug die nodig zijn om de Game te laten starten.
     *
     * @return Het aantal spelers, nodig om een Game te starten. *
     */
    public int getRequiredNumberOfPlayers() {
        return numberofusers;
    }

    /**
     * Geef een lijst van spelers in de game terug (met uitzondering van de
     * host).
     *
     * @return Een lijst met de huidige spelers van dit spel. *
     */
    public List<User> getOtherPlayers() {
        return model.getUsers();
    }

    /**
     * Geef een lijst terug met de volledige spelerslijst (dus jijzelf
     * inbegrepen) *
     */
    public List<User> getFullUserList() {
        if (you != null && you.equals(getHost())) {
            List<User> list = new ArrayList<User>();
            list.add(getHost());
            for (User usr : model.getUsers()) {
                list.add(usr);
            }
            fulluserlist = list;
        }
        return fulluserlist;
    }

    /**
     * Het GameModel luistert naar het OnLineUserModel, zodat bijvoorbeeld bij
     * het toegevoegd worden van een speler aan dit model kan gecontroleerd
     * worden of het spel dient gestart te worden .*
     */
    public void stateChanged(ChangeEvent ce) {
        if (getCurrentNumberOfPlayers() < lastusernumber) {
            lastusernumber--;
        } else if (getCurrentNumberOfPlayers() > lastusernumber) {
            userAdded();
            lastusernumber++;
        }
        fireStateChanged();
    }

    /**
     * Indien een GameEvent werd gegooid, wat dient er te gebeuren?
     *
     * @param evt De GameEvent die werd gegooid. *
     */
    private void onGameEvent(GameEvent evt) {
        receive(evt.getObject());
        fireStateChanged();
    }

    /**
     * Een geinviteerde vriend heeft geaccepteerd om het spel te joinen.
     *
     * @param evt Het event waarin de gegevens van de geaccepteerde vriend
     * staan. *
     */
    private void onGameInvitationAcceptedEvent(GameInvitationAcceptedEvent evt) {
        if (you.equals(getHost())) {
            decreaseNumberOfPendingInvitations();
            invited.remove(evt.getSender());
            if (getCurrentNumberOfPlayers() > 1) {
                throwEvent(new GameInvitationAcceptedEvent(evt.getSender(), getOtherPlayers(), id));
            }
            model.addUser(evt.getSender());
            throwEvent(new GameInfoEvent(evt.getSender(), getGameSessionInfo(), g));
            fireStateChanged();
        } else {
            model.addUser(evt.getSender());
            fireStateChanged();
        }
    }

    /**
     * Een uitgenodigde speler wenst het spel niet toe te treden.
     *
     * @param event Het event met de gegevens van de persoon die niet wil joinen
     * *
     */
    public void onGameInvitationDeniedEvent(GameInvitationDeclinedEvent evt) {
        decreaseNumberOfPendingInvitations();
        invited.remove(evt.getSender());
    }

    /**
     * Wat gebeurt er als het spel onderbroken wordt doordat iemand het spel
     * verlaat? Er wordt een MessageDialog getoond waarin te lezen staat welke
     * persoon het spel verlaten heeft.
     *
     * @param evt Het GameInterruptedEvent met de gegevens van de persoon die
     * het spel verlaten heeft. *
     */
    private void onGameInterruptedEvent(GameInterruptedEvent evt) {
        interrupted = true;
        model.removeUser(evt.getQuitter());
        JOptionPane.showMessageDialog(null, evt.getQuitter() + " has left the game.");
        stopGame();
        fireStateChanged();
    }

    private void onConnectFriendEvent(ConnectFriendEvent event) {
        if (!event.isOnline()) {
            if (invited.contains(event.getUser())) {
                invited.remove(event.getUser());
                decreaseNumberOfPendingInvitations();
                JOptionPane.showMessageDialog(null, event.getUser() + " has left the game.");
            } else if (model.containsUser(event.getUser())) {
                JOptionPane.showMessageDialog(null, event.getUser() + " has left the game.");
            }
        }
    }

    private void onGameLeftEvent(GameLeftEvent evt) {
        model.removeUser(evt.getSender());
        fireStateChanged();
    }

    private void onGameStartedEvent(GameStartedEvent evt) {
        if (!you.equals(getHost())) {
            fulluserlist = evt.getFullUserList();
            if (!isActive()) {
                setActive(true);
            }
        }
        fireStateChanged();
    }

    private void onJoinGameEvent(JoinGameEvent evt) {
        if (getFullUserList().size() + numberofpendinginvitations < numberofusers) {
            throwEvent(new JoinedGameEvent(getOtherPlayers(), id, evt.getSender()));
            model.addUser(evt.getSender());
            throwEvent(new GameInfoEvent(evt.getSender(), getGameSessionInfo(), g));
            fireStateChanged();
        } else {
            throwEvent(new JoinRefusedEvent(evt.getSender(), g.getNaam(), getHost()));
        }
    }

    private void onJoinedGameEvent(JoinedGameEvent evt) {
        model.addUser(evt.getJoiner());
        fireStateChanged();
    }

    public void onReplyFullUserListEven(ReplyFullUserListEvent event) {
        synchronized (wait) {
            setCondition(false);
            wait.notifyAll();
        }
    }

    public void onRequestFullUserListEvent(RequestFullUserListEvent event) {
        throwEvent(new ReplyFullUserListEvent(event.getSender(), getFullUserList(), id));
    }

    /**
     * Indien een Event op de EventQueue wordt geworpen wordt gecontroleerd of
     * het een Event is die met dit spel te maken heet. Indien dit het geval is,
     * wordt de gepaste methode opgeroepen.
     *
     * @param event De event die opgevangen wordt van de EventQueue. *
     */
    public void onEvent(Event event) {
        if (event instanceof NetworkEvent && !((NetworkEvent) event).isLocal()) {
            if (event instanceof GameNetworkEvent && ((GameNetworkEvent) event).getId().equals(id)) {
                if (event instanceof GameEvent) {
                    onGameEvent((GameEvent) event);
                } else if (event instanceof GameInterruptedEvent) {
                    onGameInterruptedEvent((GameInterruptedEvent) event);
                } else if (event instanceof GameInvitationAcceptedEvent) {
                    onGameInvitationAcceptedEvent((GameInvitationAcceptedEvent) event);
                } else if (event instanceof GameInvitationDeclinedEvent) {
                    onGameInvitationDeniedEvent((GameInvitationDeclinedEvent) event);
                } else if (event instanceof GameStartedEvent) {
                    onGameStartedEvent((GameStartedEvent) event);
                } else if (event instanceof GameLeftEvent) {
                    onGameLeftEvent((GameLeftEvent) event);
                } else if (event instanceof JoinGameEvent) {
                    onJoinGameEvent((JoinGameEvent) event);
                } else if (event instanceof JoinedGameEvent) {
                    onJoinedGameEvent((JoinedGameEvent) event);
                } else if (event instanceof RequestFullUserListEvent) {
                    onRequestFullUserListEvent((RequestFullUserListEvent) event);
                } else if (event instanceof ReplyFullUserListEvent) {
                    onReplyFullUserListEven((ReplyFullUserListEvent) event);
                }
            }
        } else if (event instanceof ConnectFriendEvent) {
            onConnectFriendEvent((ConnectFriendEvent) event);
        }
    }
}
