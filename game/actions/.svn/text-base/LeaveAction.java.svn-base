package hawkge.game.actions;

import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.game.events.GameInterruptedEvent;
import hawkge.game.events.GameLeftEvent;
import hawkge.game.gui.GameEnvironment;
import hawkge.game.models.GameModel;
import hawkge.network.events.OnlineUserListEvent;
import hawkge.storage.User;
import hawkge.storage.events.UserEvent;
import java.awt.event.ActionEvent;
import java.util.Collection;
import javax.swing.AbstractAction;

/**
 * Extensie van abstractaction die het mogelijk maakt het GameSpace paneel te verlaten en
 * terug te gaan naar het GameSettings paneel. 
 * @author michaelkint
 */
public class LeaveAction extends AbstractAction {

    private GameEnvironment environment; // Het GameEnvironment venster waarvan de inhoud dient te veranderen
    private User user;
    private GameModel model;
    
    private Collection<User> onlinefriends;

    public LeaveAction(GameEnvironment environment, GameModel model) {
        super("Leave");
        this.environment = environment;
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (model.isActive()) {
                EventQueue.queue(new GameInterruptedEvent(user, model.getOtherPlayers() , model.getID(), model.getDuration()));            
                model.setActive(false);
        } else {
            if (!model.isFinished()) {
                for (User usr : onlinefriends) {
                    EventQueue.queue(new GameLeftEvent(user, usr, model.getID()));
                }
            }

        }
        environment.close();
    }
}
