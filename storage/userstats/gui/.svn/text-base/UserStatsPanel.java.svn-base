/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.storage.userstats.gui;

import hawkge.storage.User;
import hawkge.storage.userstats.events.UserStatEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author felix
 */
public class UserStatsPanel extends JPanel {

    private Group horizontal;
    private Group vertical;
    private GroupLayout games;
    private JPanel panel;

    public UserStatsPanel(User user) {
        super();

        panel = new JPanel();
        games = new GroupLayout(panel);
        panel.setLayout(games);
        JLabel lbl = new JLabel("loading games...");
        games.setHorizontalGroup(games.createSequentialGroup().addComponent(lbl));
        games.setVerticalGroup(games.createSequentialGroup().addComponent(lbl));

        initLayout(user);
    }

    public void update(UserStatEvent event) {
        panel.removeAll();
        JComboBox box = new JComboBox(event.getGames().keySet().toArray());
        JButton btn = new JButton(new ViewAction(box, event));
        games.setHorizontalGroup(games.createSequentialGroup()
                .addComponent(box)
                .addComponent(btn)
        );
        games.setVerticalGroup(games.createParallelGroup()
                .addComponent(box)
                .addComponent(btn)
        );
        revalidate();
    }

    private void initLayout(User u) {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        horizontal = layout.createParallelGroup();
        vertical = layout.createSequentialGroup();

        layout.setHorizontalGroup(horizontal);
        layout.setVerticalGroup(vertical);

        JLabel namelbl = new JLabel("Name: ");
        JLabel iplbl = new JLabel("IP: ");
        JLabel gamelbl = new JLabel("Games: ");
        JLabel desclbl = new JLabel("Description: ");
        JLabel name = new JLabel(u.getName());
        JLabel ip = new JLabel(u.getIP().toString());
        JTextArea desc = new JTextArea(u.getDescription(), 5, 50);

        desc.setEditable(false);

        horizontal.addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(namelbl)
                    .addComponent(iplbl)
                    .addComponent(gamelbl)
                )
                .addGroup(layout.createParallelGroup()
                    .addComponent(name)
                    .addComponent(ip)
                    .addComponent(panel)
                )
        );
        horizontal.addComponent(desclbl);
        horizontal.addComponent(desc);

        vertical.addGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(namelbl)
                        .addComponent(iplbl)
                        .addComponent(gamelbl)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(name)
                        .addComponent(ip)
                        .addComponent(panel)
                )
        );
        vertical.addComponent(desclbl);
        vertical.addComponent(desc);
    }

    private class ViewAction extends AbstractAction {

        private JComboBox box;
        private UserStatEvent event;

        public ViewAction(JComboBox box, UserStatEvent event) {
            super("View Stats");
            this.box = box;
            this.event = event;
        }

        public void actionPerformed(ActionEvent e) {
            String name = (String) box.getSelectedItem();
            event.getGames().get(name).showStats();
        }

    }

}
