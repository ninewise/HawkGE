package hawkge.game;

import hawkge.event.Callable;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.event.NetworkEvent;
import hawkge.game.events.GameInfoEvent;
import hawkge.game.events.GameInvitationAcceptedEvent;
import hawkge.game.events.GameInvitationDeclinedEvent;
import hawkge.game.events.GameInvitationRequestEvent;
import hawkge.game.events.GameLeftEvent;
import hawkge.game.events.GameStartedEvent;
import hawkge.game.events.PublicGameEvent;
import hawkge.game.gui.GameEnvironment;
import hawkge.game.gui.GameSpace;
import hawkge.network.events.ConnectFriendEvent;
import hawkge.storage.User;
import hawkge.storage.events.UserEvent;
import hawkge.storage.gameloading.events.GameAddedEvent;
import hawkge.storage.gameloading.events.GameListEvent;
import hawkge.storage.gameloading.events.RequestGameEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * 
 * @author michaelkint
 */
public class PublicGameListener implements EventListener {

    private User user;
    private final Object wait;
    private boolean waitCondition;
    private Collection<Game> gamecollection;
    private List<GameSessionInfo> games;
    private PublicGameModel model;

    public PublicGameListener(List<GameSessionInfo> games, PublicGameModel model) {
        wait = new Object();
        this.games = games;
        this.model = model;
        EventQueue.getQueue().addEventListener(this);
        getCurrentUser();
    }

    public void onEvent(Event event) {
        if (event instanceof NetworkEvent && !((NetworkEvent) event).isLocal()) {
            if (event instanceof GameInvitationRequestEvent) {
                GameInvitationRequestEvent evt = (GameInvitationRequestEvent) event;
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
                int answer = JOptionPane.showConfirmDialog(
                        null,
                        evt.getGameSessionInfo().getHost() + " has invited you for a game of " + evt.getGameSessionInfo().getGame() + "\nDo you accept?",
                        "Game Invitation",
                        JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    if(!collectionContainsGame(evt.getGameSessionInfo().getGame())) {
                        final String name = evt.getGameSessionInfo().getGame();
                        final Collection<User> senders = evt.getGameSessionInfo().getFullUserList();
                        final GameID id = evt.getGameSessionInfo().getID();
                        EventQueue.getQueue().addEventListener(new EventListener() { public void onEvent(Event event) {
                            if(event instanceof GameAddedEvent) {
                                GameAddedEvent gameAddedEvent = (GameAddedEvent) event;
                                if(!gameAddedEvent.getName().equals(name)) return;
                                EventQueue.queue(new GameInvitationAcceptedEvent(user, senders, id));
                                EventQueue.getQueue().removeEventListener(this);
                            }
                        }});
                        EventQueue.queue(new RequestGameEvent(name, user, evt.getGameSessionInfo().getHost()));
                    } else {
                        EventQueue.queue(new GameInvitationAcceptedEvent(user, evt.getGameSessionInfo().getFullUserList(), evt.getGameSessionInfo().getID()));
                    }
                } else {
                    EventQueue.queue(new GameInvitationDeclinedEvent(user, evt.getGameSessionInfo().getFullUserList(), evt.getGameSessionInfo().getID()));
                }
            } else if (event instanceof GameInfoEvent) {
                GameInfoEvent evt = (GameInfoEvent) event;
                GameEnvironment ge = new GameEnvironment(false);
                GameSpace gs = new GameSpace(ge, evt.getGameSessionInfo(), evt.getGame());
                ge.changeToGameSpacePanel(evt.getGame(), gs);
            } else if (event instanceof PublicGameEvent) {
                PublicGameEvent evt = (PublicGameEvent) event;
                GameSessionInfo info = evt.getGameSessionInfo();
                games.add(info);
                model.fireIntervalAdded(games.size() - 1, games.size());
            } else if (event instanceof GameStartedEvent) {
                GameStartedEvent evt = (GameStartedEvent) event;
                for (GameSessionInfo info : games) {
                    if (info.getID().equals(evt.getId())) {
                        games.remove(info);
                        model.fireIntervalRemoved(games.size() + 1, games.size());
                        break;
                    }
                }
            } else if (event instanceof GameLeftEvent) {
                GameLeftEvent evt = (GameLeftEvent) event;
                for (GameSessionInfo info : games) {
                    if (info.getID().equals(evt.getId()) && evt.getSender().equals(info.getHost())) {
                        games.remove(info);
                        model.fireIntervalRemoved(games.size() + 1, games.size());
                        break;
                    }
                }
            }
        } else if (event instanceof ConnectFriendEvent) {
            ConnectFriendEvent evt = (ConnectFriendEvent) event;
            if (!evt.isOnline()) {
                removeAllFromListWithoutConcurrentModificationError(evt.getUser());
            }
        }   
    }

    private void removeAllFromListWithoutConcurrentModificationError(User sender) {
        List<GameSessionInfo> temp = new ArrayList<GameSessionInfo>();
        for (GameSessionInfo info : games) {
            if (info.getHost().equals(sender)) {
                temp.add(info);
            }
        }
        games.removeAll(temp);
        model.fireIntervalRemoved(games.size() + temp.size(), games.size());
    }

    private void getCurrentUser() {
        EventQueue.queue(new UserEvent(new Callable<User>() {

            public void call(User param) {
                setCurrentUser(param);
            }
        }));
    }

    private void setCondition(boolean condition) {
        this.waitCondition = condition;
    }

    private void setCurrentUser(User user) {
        setCondition(true);
        this.user = user;
        synchronized (wait) {
            setCondition(false);
            wait.notifyAll();
        }
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
        this.gamecollection = new ArrayList<Game>(games);
    }

    private boolean collectionContainsGame(String game) {
        for (Game g : gamecollection) {
            if (g.toString().equals(game)) {
                return true;
            }
        }
        return false;
    }
}
