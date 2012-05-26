/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.addfriendframe;

import hawkge.event.EventQueue;
import hawkge.storage.usermanager.UserManager;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author felix
 */
public class AddFriendFrame extends JFrame {

    public AddFriendFrame(UserManager userManager) {
        super();

        AddFriendModel model = new AddFriendModel(this, userManager);
        AddFriendPanel panel = new AddFriendPanel(model);

        setResizable(false);
        setContentPane(panel);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void close() {
        setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddFriendFrame(null);
            }
        });
    }

}
