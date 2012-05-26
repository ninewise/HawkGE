/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.userstats.gui;

import hawkge.event.Callable;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.event.NetworkEvent;
import hawkge.network.IPAddress;
import hawkge.storage.User;
import hawkge.storage.events.UserEvent;
import hawkge.storage.userstats.GameStat;
import hawkge.storage.userstats.events.UserStatEvent;
import hawkge.storage.userstats.events.UserStatRequestEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author felix
 */
public class UserStatsFrame extends JFrame implements EventListener {

    private final UserStatsPanel panel;

    public UserStatsFrame(final User user) {
        super("Statistics " + user.getName());

        panel = new UserStatsPanel(user);

        setResizable(false);
        setContentPane(panel);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        EventQueue.getQueue().addEventListener(this);
        EventQueue.queue(new UserEvent(new Callable<User>() {
            public void call(User param) {
                EventQueue.queue(new UserStatRequestEvent(param, user));
            }
        }));
    }

    public void onEvent(Event event) {
        if(event instanceof UserStatEvent) {
            final UserStatEvent userStatEvent = (UserStatEvent) event;
            if(userStatEvent.isLocal()) return;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    panel.update(userStatEvent);
                }
            });
        }
    }

    public static void main(String[] args) {
        final User user = new User(new IPAddress(1, 1, 2, 3), "Felix", "Zotte kerel dadde.");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserStatsFrame(user);
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {

        }
        Map<String, GameStat> map = new HashMap<String, GameStat>();
        map.put("Dammen", new GameStat());
        NetworkEvent e = new UserStatEvent(user, user, map);
        e.clearLocal();
        EventQueue.queue(e);
        // To close, but right away:
        // EventQueue.getQueue().close();
    }

}
