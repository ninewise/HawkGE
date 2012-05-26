package hawkge.game.actions;

import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.game.events.GameInterruptedEvent;
import hawkge.game.events.GameLeftEvent;
import hawkge.game.models.GameModel;
import hawkge.network.events.OnlineUserListEvent;
import hawkge.storage.User;
import hawkge.storage.events.UserEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collection;

/**
 *
 * @author michaelkint
 */
public class GameSpaceWindowListener implements WindowListener {

    private User user;
    private GameModel model;
    private Collection<User> onlinefriends;

    public GameSpaceWindowListener(GameModel model) {
        super();
        this.model = model;
        getCurrentUser();
        getOnlineFriends();
    }
    
    private void getCurrentUser() {
        EventQueue.queue(new UserEvent(new Callable<User>() {

            public void call(User param) {
                setCurrentUser(param);
            }
        }));
    }

    private void setCurrentUser(User user) {
        this.user = user;
    }
    
    
    private void getOnlineFriends() {
        EventQueue.queue(new OnlineUserListEvent(new Callable<Collection<User>>() {

            public void call(Collection<User> users) {
                initList(users);
            }
        }));
    }

    /** Wijzig de lijst met online friends voor een user. 
    @param users De lijst met friends die ingesteld dient te worden. **/
    private void initList(Collection<User> users) {
        this.onlinefriends = users;
    }

    public void windowOpened(WindowEvent we) {
    }

    public void windowClosing(WindowEvent we) {
        if (model.isActive()) {
           EventQueue.queue(new GameInterruptedEvent(user, model.getOtherPlayers(), model.getID(),model.getDuration()));          
           model.setActive(false);
        } else {
            if (!model.isFinished()) {
                for (User usr : onlinefriends) {
                    EventQueue.queue(new GameLeftEvent(user, usr, model.getID()));
                }
            }

        }
    }

    public void windowClosed(WindowEvent we) {
    }

    public void windowIconified(WindowEvent we) {
    }

    public void windowDeiconified(WindowEvent we) {
    }

    public void windowActivated(WindowEvent we) {
    }

    public void windowDeactivated(WindowEvent we) {
    }
}
