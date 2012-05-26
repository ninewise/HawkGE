package hawkge.main;

import hawkge.main.inlog.InlogModel;
import javax.swing.JFrame;

/**
 * @create on Apr 26, 2012
 *
 * @author jorisvi
 */
public class Frame extends JFrame {

    public Frame() {
        super();
        setContentPane(new PasswordChangePanel(new InlogModel(this)));
        setResizable(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Frame();
    }
}