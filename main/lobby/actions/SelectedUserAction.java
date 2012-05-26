/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.main.lobby.actions;

import hawkge.main.lobby.list.UserListComponent;
import hawkge.main.lobby.list.UserState;
import hawkge.storage.User;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author felix
 */
public abstract class SelectedUserAction extends AbstractAction implements ListSelectionListener {

    private JList list;
    private static final Set<SelectedUserAction> all = new HashSet<SelectedUserAction>();

    public SelectedUserAction(String value, JList list) {
        super(value);
        setEnabled(false);
        this.list = list;
        all.add(this);
        list.addListSelectionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        UserListComponent listComponent = (UserListComponent) list.getSelectedValue();
        if (listComponent != null) {
            UserState s = actionPerformed(listComponent.getUser());
            // Watch out, this is not the actual UserState, just one that
            // responds correctly in valueChanged. I know, that's bad.
            for(SelectedUserAction a : all) a.userSelected(s);
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        UserListComponent listComponent = (UserListComponent) list.getSelectedValue();
        if (listComponent != null) {
            userSelected(listComponent.getUserState());
        } else {
            userSelected(null);
        }
    }

    public abstract UserState actionPerformed(User u);
    public abstract void userSelected(UserState s);

}
