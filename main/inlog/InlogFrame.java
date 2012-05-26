package hawkge.main.inlog;

import javax.swing.JFrame;

/**
 * @create on Apr 26, 2012
 *
 * @author jorisvi
 */
public class InlogFrame extends JFrame {

    public InlogFrame() {
        super();
        InlogModel model = new InlogModel(this);
        setContentPane(new InlogPanel(model, this));
        setResizable(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}