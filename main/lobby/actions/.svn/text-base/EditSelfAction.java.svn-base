/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.main.lobby.actions;

import hawkge.storage.userstats.gui.EditUserFrame;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

/**
 *
 * @author felix
 */
public class EditSelfAction extends AbstractAction {

    public EditSelfAction() {
        super("Settings...");
    }

    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() { public void run() {
            new EditUserFrame();
        }});
    }

}
