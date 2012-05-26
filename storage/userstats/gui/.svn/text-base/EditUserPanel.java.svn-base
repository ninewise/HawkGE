/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.userstats.gui;

import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.storage.User;
import hawkge.storage.events.VerifyPassEvent;
import hawkge.storage.gameloading.events.RemoveGameEvent;
import hawkge.storage.registration.Registrator;
import hawkge.storage.registration.events.CloseRegistratorEvent;
import hawkge.storage.userstats.events.DescriptionEvent;
import hawkge.storage.userstats.events.LocalUserStatEvent;
import hawkge.storage.userstats.events.PasswordChangedEvent;
import hawkge.storage.userstats.events.ShowGameStatEvent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

/**
 *
 * @author felix
 */
public class EditUserPanel extends JPanel {

    public EditUserPanel(final User user) {
        super();

        JLabel namelbl = new JLabel("Name:");
        JLabel iplbl = new JLabel("IP:");
        JLabel passlbl = new JLabel("Password:");
        JLabel oldlbl = new JLabel("(old password)");
        JLabel newlbl = new JLabel("(new password)");
        JLabel desclbl = new JLabel("Description:");
        JLabel gameslbl = new JLabel("Games:");

        JLabel name = new JLabel(user.getName());
        JLabel ip = new JLabel(user.getIP().toString());
        final JPasswordField oldPass = new JPasswordField(15);
        final JPasswordField newPass = new JPasswordField(15);
        final JTextArea desc = new JTextArea(user.getDescription(), 2, 40);
        final JComboBox games = new JComboBox();


        EventQueue.queue(new LocalUserStatEvent(new Callable<String[]>() {
            public void call(String[] param) {
                for(int i = 0; i < param.length; i++) {
                    games.addItem(param[i]);
                }
            }
        }));

        // snapshot of variables.
        final String username = user.getName();

        JButton changePass = new JButton(new AbstractAction("Change") {
            public void actionPerformed(ActionEvent e) {
                if(oldPass.getPassword() == null) JOptionPane.showMessageDialog(null, "Fill in your old password.", "Field empty", JOptionPane.ERROR_MESSAGE);
                if(newPass.getPassword() == null) JOptionPane.showMessageDialog(null, "Fill in your new password.", "Field empty", JOptionPane.ERROR_MESSAGE);
                EventQueue.queue(new VerifyPassEvent(new String(oldPass.getPassword()), new Callable<Boolean>() {
                    public void call(Boolean correct) {
                        if(!correct) JOptionPane.showMessageDialog(null, "Incorrect old password.", "Incorrect Password", JOptionPane.ERROR_MESSAGE);
                        new Registrator();
                        EventQueue.queue(new PasswordChangedEvent(username, new String(newPass.getPassword()), new Callable<Void>() {
                            public void call(Void param) {
                                EventQueue.queue(new CloseRegistratorEvent());
                            }
                        }));
                        oldPass.setText(null);
                        newPass.setText(null);
                    }
                }));
            }
        });

        JButton saveDesc = new JButton(new AbstractAction("Save") {
            public void actionPerformed(ActionEvent e) {
                user.setDescription(desc.getText());
                EventQueue.queue(new DescriptionEvent(desc.getText()));
            }
        });

        JButton removeGame = new JButton(new AbstractAction("Remove") {
            public void actionPerformed(ActionEvent e) {
                EventQueue.queue(new RemoveGameEvent((String) games.getSelectedItem()));
                games.removeItemAt(games.getSelectedIndex());
            }
        });

        JButton viewGame = new JButton(new AbstractAction("View Stats") {
            public void actionPerformed(ActionEvent e) {
                EventQueue.queue(new ShowGameStatEvent((String) games.getSelectedItem()));
            }
        });

        GroupLayout l = new GroupLayout(this);
        setLayout(l);

        l.setAutoCreateContainerGaps(true);
        l.setAutoCreateGaps(true);

        l.setHorizontalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(namelbl)
                .addComponent(iplbl)
                .addComponent(gameslbl)
                .addComponent(passlbl)
                .addComponent(desclbl)
            )
            .addGroup(l.createParallelGroup()
                .addComponent(name)
                .addComponent(ip)
                .addGroup(l.createSequentialGroup()
                    .addComponent(games)
                    .addComponent(removeGame)
                    .addComponent(viewGame)
                )
                .addGroup(l.createSequentialGroup()
                    .addGroup(l.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(oldlbl)
                        .addComponent(oldPass)
                    )
                    .addGroup(l.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(newlbl)
                        .addComponent(newPass)
                    )
                    .addComponent(changePass)
                )
                .addGroup(l.createSequentialGroup()
                    .addComponent(desc)
                    .addComponent(saveDesc)
                )
            )
        );
        l.setVerticalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(namelbl)
                .addComponent(name)
            )
            .addGroup(l.createParallelGroup()
                .addComponent(iplbl)
                .addComponent(ip)
            )
            .addGroup(l.createParallelGroup()
                .addComponent(gameslbl)
                .addComponent(games)
                .addComponent(removeGame)
                .addComponent(viewGame)
            )
            .addGroup(l.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(passlbl)
                .addGroup(l.createSequentialGroup()
                    .addComponent(oldlbl)
                    .addComponent(oldPass)
                )
                .addGroup(l.createSequentialGroup()
                    .addComponent(newlbl)
                    .addComponent(newPass)
                )
                .addComponent(changePass, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            )
            .addGroup(l.createParallelGroup()
                .addComponent(desclbl)
                .addComponent(desc)
                .addComponent(saveDesc, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            )
        );

    }

}
