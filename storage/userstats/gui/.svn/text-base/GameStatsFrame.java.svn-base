/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.userstats.gui;

import hawkge.storage.userstats.GameStat;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author felix
 */
public class GameStatsFrame extends JFrame {

    public GameStatsFrame(GameStat g) {
        super("Statistics");

        ShowStatsPanel panel = new ShowStatsPanel(g);

        setResizable(false);
        setContentPane(panel);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private class ShowStatsPanel extends JPanel {

        public ShowStatsPanel(GameStat g) {
            super();

            JLabel[] lbls = new JLabel[] {
                new JLabel("Games won:"),
                new JLabel("Games lost:"),
                new JLabel("Games left:"),
                new JLabel("Last played:"),
                new JLabel("Hours played:"),
                new JLabel("Highest score:"),
                new JLabel("Total score:")
            };
            JLabel[] values = new JLabel[] {
                new JLabel(""+g.getGamesWon()),
                new JLabel(""+g.getGamesLost()),
                new JLabel(""+g.getGamesLeft()),
                new JLabel(g.getLastPlayed()),
                new JLabel(""+g.getHoursPlayed()),
                new JLabel(""+g.getHighscore()),
                new JLabel(""+g.getTotalScore())
            };

            GroupLayout layout = new GroupLayout(this);
            setLayout(layout);
            layout.setAutoCreateContainerGaps(true);
            layout.setAutoCreateGaps(true);

            Group horizontal = layout.createSequentialGroup();
            Group lblsgroup = layout.createParallelGroup();
            Group valuesgroup = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
            for(int i = 0; i < lbls.length; i++) {
                lblsgroup.addComponent(lbls[i]);
                valuesgroup.addComponent(values[i]);
            }
            horizontal.addGroup(lblsgroup);
            horizontal.addGap(20);
            horizontal.addGroup(valuesgroup);

            Group vertical = layout.createSequentialGroup();
            for(int i = 0; i < lbls.length; i++) {
                vertical.addGroup(layout.createParallelGroup()
                        .addComponent(lbls[i])
                        .addComponent(values[i])
                );
            }

            layout.setHorizontalGroup(horizontal);
            layout.setVerticalGroup(vertical);

        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameStatsFrame(new GameStat());
            }
        });
    }

}
