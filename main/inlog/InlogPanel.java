package hawkge.main.inlog;

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
public class InlogPanel extends JPanel {

    public InlogPanel(InlogModel model, JFrame loginFrame) {
        super();
        JTextField name = new NameField(model);
        JPasswordField password = new PasswordField(model);
        JLabel nameLabel = new HawkLabel("name");
        JLabel passwordLabel = new HawkLabel("password");
        password.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "INLOG");
        password.getActionMap().put("INLOG", new InlogAction(name, password, model));
        JButton login = new JButton(new InlogAction(name, password, model));
        JButton register = new RegisterButton(loginFrame);
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(nameLabel)
                        .addComponent(passwordLabel)
                        .addComponent(login)
                        
                    )
                    .addGroup(layout.createParallelGroup()
                        .addComponent(name)
                        .addComponent(password)
                        .addComponent(register)
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
                        .addComponent(login)
                        .addComponent(register)
                    )
                );
    }
}