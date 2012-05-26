package hawkge.game.actions;

import hawkge.event.Callable;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.event.NetworkEvent;
import hawkge.game.Game;
import hawkge.game.GameID;
import hawkge.game.GameSessionInfo;
import hawkge.game.PublicGameModel;
import hawkge.game.events.JoinGameEvent;
import hawkge.game.events.JoinRefusedEvent;
import hawkge.storage.User;
import hawkge.storage.events.UserEvent;
import hawkge.storage.gameloading.events.GameAddedEvent;
import hawkge.storage.gameloading.events.GameListEvent;
import hawkge.storage.gameloading.events.RequestGameEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author michaelkint
 */
public class JoinGameAction extends AbstractAction implements ListDataListener, ListSelectionListener, EventListener {

    private PublicGameModel model;
    private JList list;
    private User user;
    private List<Game> games;
    private final Object wait;
    private boolean waitCondition;

    public JoinGameAction(PublicGameModel model, JList list) {
        super("Join selected game");
        wait = new Object();
        this.list = list;
        this.model = model;
        setEnabled(false);
        model.addListDataListener(this);
        list.addListSelectionListener(this);
        getCurrentUser();
    }

    public void actionPerformed(ActionEvent ae) {
        if (gameSelected()) {
            setCondition(true);
            getGames();
            synchronized (wait) {
                try {
                    while (waitCondition) {
                        wait.wait();
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Waiting interrupt: " + ex);
                }
            }
            // Taking snapshot of variables for use in onEvent.
            final String name = getSelectedInfo().getGame();
            final User host = getSelectedInfo().getHost();
            final GameID id = getSelectedInfo().getID();
            final User local = user;
            if (containsGame(getSelectedInfo().getGame())) {
                EventQueue.queue(new JoinGameEvent(host, local, id));             
            } else {
                EventQueue.getQueue().addEventListener(new EventListener() {
                    public void onEvent(Event event) {
                        if(event instanceof GameAddedEvent) {
                            GameAddedEvent gameAddedEvent = (GameAddedEvent) event;
                            if(name.equals(gameAddedEvent.getName())) {
                                EventQueue.queue(new JoinGameEvent(host, local, id));
                                // We got what we wanted, no more listening.
                                EventQueue.getQueue().removeEventListener(this);
                            }
                        }
                    }
                });
                EventQueue.queue(new RequestGameEvent(getSelectedInfo().getGame(), user, getSelectedInfo().getHost()));
            }
        }
    }
    
    private boolean containsGame(String game){
        for(Game g : games){
            if(g.toString().equals(game)) return true;
        }
        return false;
    }

    private GameSessionInfo getSelectedInfo() {
        return (GameSessionInfo) model.getElementAt(list.getSelectedIndex());
    }

    private void getCurrentUser() {
        EventQueue.queue(new UserEvent(new Callable<User>() {

            public void call(User param) {
                setCurrentUser(param);
            }
        }));
    }

    public void setCurrentUser(User user) {
        this.user = user;
    }

    public void intervalAdded(ListDataEvent lde) {
        setEnabled(gameSelected());
    }

    public void intervalRemoved(ListDataEvent lde) {
        setEnabled(gameSelected());
    }

    private boolean gameSelected() {
        return model.size() > 0 && list.getSelectedIndex() != -1;
    }
    
    public void contentsChanged(ListDataEvent lde) {
    }

    public void valueChanged(ListSelectionEvent lse) {
        setEnabled(gameSelected());
    }

    private synchronized void setCondition(Boolean condition) {
        this.waitCondition = condition;
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

    public void onEvent(Event event) {
        if(event instanceof NetworkEvent && !((NetworkEvent) event).isLocal()){
            if(event instanceof JoinRefusedEvent){
                JoinRefusedEvent evt = (JoinRefusedEvent) event;
                JOptionPane.showMessageDialog(null, "No more places available for " + evt.getGame() + " with host " + evt.getHost(), "Join game error", 0);             
            }
        }
    }

}
