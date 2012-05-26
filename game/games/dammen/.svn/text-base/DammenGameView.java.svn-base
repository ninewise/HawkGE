import hawkge.game.models.GameModel;
import hawkge.game.gui.GameView;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author michaelkint
 */
public class DammenGameView extends GameView {

    private DammenGameModel model;

    public DammenGameView(GameModel model) {
        super(model);
        this.model = (DammenGameModel) model;

        addMouseListener(new DammenMouseListener((DammenGameModel) model));
    }

    @Override
    public int getHeight() {
        return model.getHeight() * 40 + 10;
    }

    @Override
    public int getWidth() {
        return model.getWidth() * 40 + 10;
    }

    public void stateChanged(ChangeEvent ce) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        for (int i = 0; i < model.getWidth(); i++) {
            for (int j = 0; j < model.getHeight(); j++) {

                /** Teken het spelbord **/
                if (i % 2 == 0) {
                    if (j % 2 != 0) {
                        grphcs.setColor(new Color(139, 87, 66));
                    } else {
                        grphcs.setColor(new Color(255, 215, 0));
                    }
                    grphcs.fillRect(i * 40 + 10, j * 40 + 10, 40, 40);
                } else {
                    if (j % 2 == 0) {
                        grphcs.setColor(new Color(139, 87, 66));
                    } else {
                        grphcs.setColor(new Color(255, 215, 0));
                    }
                    grphcs.fillRect(i * 40 + 10, j * 40 + 10, 40, 40);
                }

                /** Teken een gekleurde bol op de aangeklikte plaats **/
                if (model.getPos(i, j) != 0) {
                    if (model.getPos(i, j) == 2) {
                        drawOval(grphcs, i, j);
                    } else if (model.getPos(i, j) == 1) {
                        drawOval(grphcs, i, j);
                    } else if(model.getPos(i,j) == 3){
                        drawCrownedOval(grphcs,Color.BLACK, i,j);
                    } else {
                        drawCrownedOval(grphcs,Color.WHITE, i,j);
                    }
                }

                /** Teken lijnen **/
                grphcs.setColor(Color.BLACK);
                for (int k = 0; k < getHeight(); k++) {
                    grphcs.drawLine(10 + 40 * k, 10, 10 + 40 * k, 10 + 40 * getHeight());
                    grphcs.drawLine(10, 10 + 40 * k, 10 + 40 * getWidth(), 10 + 40 * k);
                }

                if (model.getSelected() != null) {
                    Point p = model.getSelected();
                    int x = (int) p.getX();
                    int y = (int) p.getY();

                    grphcs.setColor(Color.BLUE);
                    grphcs.fillOval(x * 40 + 15, y * 40 + 15, 30, 30);

                    if (model.getPos(x, y) > 2) {
                        grphcs.setColor(new Color(139, 87, 66));
                        grphcs.fillRect(x * 40 + 15, y * 40 + 15, 30, 9);
                        
                        grphcs.setColor(Color.BLUE);
                        int[] ints = {x * 40 + 15, x * 40 + 15, x * 40 + 23, x * 40 + 30, x * 40 + 37, x * 40 + 45, x * 40 + 45};
                        int[] ints1 = {y * 40 + 26, y * 40 + 15, y * 40 + 19, y * 40 + 15, y * 40 + 19, y * 40 + 15, y * 40 + 26};
                        grphcs.fillPolygon(ints, ints1, 7);
                    }
                }
            }
        }
    }
    
    private void drawOval(Graphics grphcs, int i, int j){
        grphcs.setColor(model.getPlayerColor(model.getPos(i, j)));
        grphcs.fillOval(i * 40 + 15, j * 40 + 15, 30, 30);
    }

    private void drawCrownedOval(Graphics grphcs, Color color, int i, int j) {
        grphcs.setColor(model.getPlayerColor(model.getPos(i, j)));
        grphcs.fillOval(i * 40 + 15, j * 40 + 15, 30, 30);

        grphcs.setColor(new Color(139, 87, 66));
        grphcs.fillRect(i * 40 + 15, j * 40 + 15, 30, 9);

        int[] ints = {i * 40 + 15, i * 40 + 15, i * 40 + 23, i * 40 + 30, i * 40 + 37, i * 40 + 45, i * 40 + 45};
        int[] ints1 = {j * 40 + 26, j * 40 + 15, j * 40 + 19, j * 40 + 15, j * 40 + 19, j * 40 + 15, j * 40 + 26};

        grphcs.setColor(color);
        grphcs.fillPolygon(ints, ints1, 7);
    }
}
