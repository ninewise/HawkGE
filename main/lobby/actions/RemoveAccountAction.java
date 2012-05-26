/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.main.lobby.actions;

import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.main.lobby.list.LogoutEvent;
import hawkge.storage.events.RemoveAccountEvent;
import hawkge.storage.registration.Registrator;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author felix
 */
public class RemoveAccountAction extends AbstractAction {

    private final Counter i;

    public RemoveAccountAction() {
        super("Remove account");
        i = new Counter(2);
    }

    public void actionPerformed(ActionEvent e) {
        int input = JOptionPane.showConfirmDialog(null, "Doing this will delete your account permanently.", "Please confirm", JOptionPane.OK_CANCEL_OPTION);
        if(input == JOptionPane.CANCEL_OPTION || input == JOptionPane.CLOSED_OPTION) return;
        new Registrator();
        EventQueue.queue(new RemoveAccountEvent(new Callable<Void>() {
            public void call(Void param) {
                i.down();
            }
        }));
    }

    private class Counter {
        private int i;
        public Counter(int i) { this.i = i; }
        public synchronized void down() {
            i--;
            if(i == 0) EventQueue.queue(new LogoutEvent());
            System.out.println("logging out?");
        }
    }

}
