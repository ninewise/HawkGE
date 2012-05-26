package hawkge.storage.addfriendframe;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @create on Apr 26, 2012
 * @author jorisvi
 */
public class AddFriendPanel extends JPanel {

    final private JTextField nameField;
    final private IPField ipField;
    final private JButton okButton;

    public AddFriendPanel(final AddFriendModel model) {
        super();

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);
        JLabel ipLabel = new JLabel("IP:");
        ipField = new IPField();

        Action okAction = new AbstractAction("OK") {
            public void actionPerformed(ActionEvent e) {
                model.addFriend(nameField.getText(), ipField.getIP());
            }
        };

        Action nameAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                ipField.requestFocusInWindow();
            }
        };

        okButton = new JButton(okAction);
        nameField.addActionListener(nameAction);
        ipField.setAction(okAction);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameLabel)
                .addComponent(ipLabel)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameField)
                .addComponent(ipField)
            )
            .addComponent(okButton)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(nameLabel)
                    .addComponent(nameField)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(ipLabel)
                    .addComponent(ipField)
                )
            )
            .addComponent(okButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

}