/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.addfriendframe;

import hawkge.network.IPAddress;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author felix
 */
public class IPField extends JPanel implements FocusListener {

    private ByteField[] ips;

    public IPField() {
        super();

        setFocusable(true);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        Group verticalGroup = layout.createParallelGroup();
        Group horizontalGroup = layout.createSequentialGroup();

        ips = new ByteField[4];
        ips[0] = new ByteField();
        verticalGroup.addComponent(ips[0]);
        horizontalGroup.addComponent(ips[0]);
        for(int i = 1; i < 4; i++) {
            JLabel lbl = new JLabel(".");
            verticalGroup.addComponent(lbl);
            horizontalGroup.addComponent(lbl);
            ips[i] = new ByteField();
            verticalGroup.addComponent(ips[i]);
            horizontalGroup.addComponent(ips[i]);
        }

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);

        for(int i = 0; i < 3; i++) {
            final int j = i;
            ips[i].setAction(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    ips[j+1].requestFocusInWindow();
                }
            });
        }

    }

    public void clear() {
        for(int i = 0; i < 4; i++) {
            ips[i].setText("");
        }
    }

    public void setAction(Action action) {
        ips[3].setAction(action);
    }

    public IPAddress getIP() {
        int i = 0;
        int[] bytes = new int[4];
        while(i < 4) {
            bytes[i] = ips[i].getByte();
            if(bytes[i] < 0) return null;
            i++;
        }
        return new IPAddress(bytes[0], bytes[1], bytes[2], bytes[3]);
    }

    public void focusLost(FocusEvent e) {}
    public void focusGained(FocusEvent e) {
        this.ips[0].requestFocusInWindow();
    }

    @Override
    public boolean requestFocusInWindow() {
        return this.ips[0].requestFocusInWindow();
    }

    private class ByteField extends JTextField implements FocusListener {
        public ByteField() {
            super();
            setColumns(3);
            addFocusListener(this);
        }
        public int getByte() {
            int b;
            try {
                b = Integer.parseInt(getText());
                if(0 <= b && b < 256) return b;
            } catch(NumberFormatException e) {}
            requestFocusInWindow();
            return -1;
        }
        public void focusGained(FocusEvent e) {
            setCaretPosition(0);
            moveCaretPosition(getText().length());
        }
        public void focusLost(FocusEvent e) {
            setCaretPosition(0);
        }
    }


    /* For testing this component */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("IPField Test");
                JPanel panel = new JPanel();
                IPField field = new IPField();
                JLabel ipLabel = new JLabel("xxx.xxx.xxx.xxx");
                Action action = new GetIPAction(field, ipLabel);
                field.setAction(action);
                panel.add(field);
                panel.add(ipLabel);
                panel.add(new JButton(action));
                frame.setContentPane(panel);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

    private static class GetIPAction extends AbstractAction {
        private IPField field; private JLabel label;
        public GetIPAction(IPField field, JLabel label) {
            super("Submit");
            this.field = field; this.label = label;
        }
        public void actionPerformed(ActionEvent e) {
            if(field.getIP() != null) label.setText(field.getIP().toString());
        }
    }

}
