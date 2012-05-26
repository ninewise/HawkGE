import hawkge.game.models.GameModel;
import hawkge.game.gui.GameView;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author michaelkint
 */
public class VierOpEenRijGameView extends GameView {
    
    private GameModel model;
    
    public VierOpEenRijGameView(GameModel model){
        super(model);
        this.model = model;
        
        addMouseListener(new VierOpEenRijMouseListener(model));
    }

    @Override
    public int getHeight() {      
        return ((VierOpEenRijGameModel)model).getHeight()*40+10;
    }

    @Override
    public int getWidth() {
        return ((VierOpEenRijGameModel)model).getWidth()*40+10;
    }
    
    public void stateChanged(ChangeEvent ce) { 
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        for(int i=0;i<((VierOpEenRijGameModel)model).getWidth();i++){
            for(int j=0;j<((VierOpEenRijGameModel)model).getHeight();j++){
                
                /** Teken het spelbord **/
                grphcs.setColor(Color.DARK_GRAY);
                if(i%2 == 0){ 
                    if(j%2 != 0) grphcs.fillRect(i*40+10, j*40+10, 40, 40);
                } else {
                    if(j%2 == 0) grphcs.fillRect(i*40+10, j*40+10, 40, 40);
                }
                
                /** Teken een gekleurde bol op de aangeklikte plaats **/
                if(((VierOpEenRijGameModel)model).getPos(i,j)){
                    grphcs.setColor(((VierOpEenRijGameModel)model).getPlayerColor(((VierOpEenRijGameModel)model).getPosNumber(i,j)));
                    grphcs.fillOval(i*40+15,j*40+15,30,30);                 
                }
            }
        }
    }    
}
