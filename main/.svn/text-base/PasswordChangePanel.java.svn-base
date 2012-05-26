package hawkge.main;

import hawkge.main.inlog.*;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * @create on Apr 26, 2012
 * @author jorisvi
 */
public class PasswordChangePanel extends JPanel {

    public PasswordChangePanel(InlogModel model) {
        super();
        JTextField name = new NameField(model);
        JPasswordField password = new PasswordField(model);
        JPasswordField verify = new PasswordField(model);
        JLabel nameLabel = new HawkLabel("old password");
        JLabel passwordLabel = new HawkLabel("new password");
        JLabel verifyLabel = new HawkLabel("verify password");
        verifyLabel.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "INLOG");
        verifyLabel.getActionMap().put("INLOG", new InlogAction(name, password, model));
        JButton login = new JButton();
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(nameLabel)
                        .addComponent(passwordLabel)
                        .addComponent(verifyLabel)
                        .addComponent(login)                        
                    )
                    .addGroup(layout.createParallelGroup()
                        .addComponent(name)
                        .addComponent(password)
                        .addComponent(verify)
                    )
                );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(nameLabel)
                        .addComponent(name)
                        
                    )
                    .addGroup(layout.createParallelGroup()
                        .addComponent(passwordLabel)
                        .addComponent(password)
                    )
                    .addGroup(layout.createParallelGroup()
                        .addComponent(verifyLabel)
                        .addComponent(verify)
                    )
                    .addGroup(layout.createParallelGroup()
                        .addComponent(login)
                    )
                );
    }
}