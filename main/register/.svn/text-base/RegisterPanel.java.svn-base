package hawkge.main.register;

import hawkge.main.inlog.HawkLabel;
import hawkge.main.inlog.NameField;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @create on Apr 27, 2012
 * @author jorisvi
 */
public class RegisterPanel extends JPanel {

    public RegisterPanel(RegisterModel model) {
        super();
        JTextField name = new NameField(model);
        JPasswordField password = new PasswordField(model);
        JPasswordField confirmPassword = new ConfirmPasswordField(model);
        IPRegisterField ip = new IPRegisterField(model);
        JTextArea description = new DescriptionArea(model);        
        JScrollPane descriptionScroll = new JScrollPane(description);
        descriptionScroll.setMinimumSize(new Dimension(210, 65));
        descriptionScroll.setPreferredSize(new Dimension(210, 65));
        descriptionScroll.setPreferredSize(new Dimension(210, 65));
        JLabel nameLabel = new HawkLabel("name");
        JLabel passwordLabel = new HawkLabel("password");
        JLabel verifyLabel = new HawkLabel("confirm password");
        JLabel ipLabel = new HawkLabel("own ipadress");
        JLabel descriptionLabel = new HawkLabel("description");
        JButton register = new RegisterButton(name, password, confirmPassword,
                ip, description, model);
        JButton clear = new ClearButton(model);
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
                        .addComponent(ipLabel)
                        .addComponent(descriptionLabel)
                        .addComponent(register)
                    )
                    .addGroup(layout.createParallelGroup()
                        .addComponent(name)
                        .addComponent(password)
                        .addComponent(confirmPassword)
                        .addComponent(ip)
                        .addComponent(descriptionScroll)
                        .addComponent(clear)
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
                        .addComponent(confirmPassword)
                    )
                    .addGroup(layout.createParallelGroup()
                        .addComponent(ipLabel)
                        .addComponent(ip)
                    )
                    .addGroup(layout.createParallelGroup()
                        .addComponent(descriptionLabel)
                        .addComponent(descriptionScroll)
                    )
                    .addGroup(layout.createParallelGroup()
                        .addComponent(register)
                        .addComponent(clear)
                    )
                );
    }
}