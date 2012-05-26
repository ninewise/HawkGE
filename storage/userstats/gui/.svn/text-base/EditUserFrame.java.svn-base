/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.userstats.gui;

import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.storage.User;
import hawkge.storage.events.UserEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author felix
 */
public class EditUserFrame extends JFrame {

    public EditUserFrame() {
        super("About you");
        EventQueue.queue(new UserEvent(new Callable<User>() {
            public void call(User param) {
                initialize(param);
            }
        }));
    }

    public void initialize(User user) {
        EditUserPanel panel = new EditUserPanel(user);
        setContentPane(panel);

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EditUserFrame();
            }
        });
    }

}
