import hawkge.chat.model.OnLineUserModel;
import hawkge.game.Game;
import hawkge.game.GameSessionInfo;
import hawkge.game.models.GameModel;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michaelkint
 */
public class DammenGameModel extends GameModel {

    private int[][] spelbord;
    private Point selected;
    
    private int black; // aantal zwarte schijven
    private int white; // aantal witte schijven
    
    private boolean slaggedaan;

    public DammenGameModel(OnLineUserModel model,int minusers, Game g) {
        super(model, g , minusers);
        init();
    }

    public DammenGameModel(OnLineUserModel model, Game g, GameSessionInfo info) {
        super(info,g,model);
        init();
    }
    
    private void init(){
        spelbord = new int[getWidth()][getHeight()];
        selected = null;    
        black = (getWidth() / 2) * 4;
        white = (getWidth() / 2) * 4;
        initBoard();
    }
    
    private int getBlack(){
        return black;
    }
    
    private int getWhite(){
        return white;
    }
    
    private void decreaseBlack(){
        black--;
    }
    
    private void decreaseWhite(){
        white--;
    }

    public Point getSelected() {
        return selected;
    }

    public void setSelected(Point p) {
        this.selected = p;
        fireStateChanged();
    }

    public boolean isSelected(Point p) {
        return (selected != null && (p.getX() == selected.getX() && p.getY() == selected.getY()));
    }

    public boolean zetMogelijk(Point p) {
        return mogelijkeZetten(p).size() > 0;
    }

    public boolean aanRand(int x, int y) {
        return x == 0 || x == getWidth() - 1 || y == 0 || y == getHeight() - 1;
    }

    public boolean inRange(int x, int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }

    public boolean isEmpty(int x, int y) {
        if (inRange(x, y)) {
            return spelbord[x][y] == 0;
        } else {
            return false;
        }
    }
    
    public void move(Point origin, Point destination) {
        int index = spelbord[(int) origin.getX()][(int) origin.getY()];
        spelbord[(int) origin.getX()][(int) origin.getY()] = 0;
        
        if ((getActivePlayerIndex() == 1 && destination.getY() == getHeight() - 1)
                || getActivePlayerIndex() == 2 && destination.getY() == 0) {
            /** Heeft einde bereikt, kan ook achterwaards bewegen**/
            spelbord[(int) destination.getX()][(int) destination.getY()] = getActivePlayerIndex() + 2;
        } else {
            spelbord[(int) destination.getX()][(int) destination.getY()] = index;
        }

        checkTussenLiggend(origin, destination);
        selected = null;
        
        fireStateChanged();
        
        if (!checkIfFinished()) {
            if (!slaggedaan || (slaggedaan &&  !slagMogelijk(destination))) {
                nextUsersTurn();
            } 
            slaggedaan = false;
        } else {
            stopGame();
        }
        fireStateChanged();
    }
    
    private void slag(){
      if(getActivePlayerIndex() == 1) decreaseBlack(); else decreaseWhite();
      slaggedaan = true;
    }

    private void checkTussenLiggend(Point origin, Point destination) {
        if (origin.getX() - destination.getX() == 2) {
            if (origin.getY() - destination.getY() == 2) {
                if (othersItem((int) origin.getX() - 1, (int) origin.getY() - 1)) {
                    spelbord[(int) origin.getX() - 1][(int) origin.getY() - 1] = 0;
                    slag();
                }
            } else if (origin.getY() - destination.getY() == -2) {
                if (othersItem((int) origin.getX() - 1, (int) origin.getY() + 1)) {
                    spelbord[(int) origin.getX() - 1][(int) origin.getY() + 1] = 0;
                    slag();
                }
            }
        } else if (origin.getX() - destination.getX() == -2) {
            if (origin.getY() - destination.getY() == 2) {
                if (othersItem((int) origin.getX() + 1, (int) origin.getY() - 1)) {
                    spelbord[(int) origin.getX() + 1][(int) origin.getY() - 1] = 0;
                    slag();
                }
            } else if (origin.getY() - destination.getY() == -2) {
                if (othersItem((int) origin.getX() + 1, (int) origin.getY() + 1)) {
                    spelbord[(int) origin.getX() + 1][(int) origin.getY() + 1] = 0;
                    slag();
                }
            }
        }
    }

    public int getAantalMogelijkeVoorwaartseZetten(int x, int y, int playerindex) {
        return getMogelijkeVoorwaartseZetten(x, y,playerindex).size();
    }

    public int getAantalMogelijkeZetten(int i, int j, int playerindex) {
        return getMogelijkeZetten(i, j, playerindex).size();
    }

    public boolean othersItem(int x, int y) {
        if (inRange(x, y)) {
            return spelbord[x][y] == otherPlayerIndex() || spelbord[x][y] == otherPlayerIndex() + 2;
        } else {
            return false;
        }
    }
    
    public boolean othersItem(int x, int y, int playerindex) {
        if (inRange(x, y)) {
            if(playerindex == 1)
                return spelbord[x][y] == 2 || spelbord[x][y] == 4;
            else
                return spelbord[x][y] == 1 || spelbord[x][y] == 3;
        } else {
            return false;
        }
    }

    public boolean ownItem(int x, int y) {
        if (inRange(x, y)) {
            return spelbord[x][y] == getActivePlayerIndex() || spelbord[x][y] == getActivePlayerIndex() + 2;
        } else {
            return false;
        }
    }

    public boolean slagMogelijk(Point p) {
        return slagMogelijk((int) p.getX(), (int) p.getY());
    }

    public boolean slagMogelijkNoord(int x, int y) {
        if (y - 1 > 0) {
            if (x - 1 > 0 && othersItem(x - 1, y - 1) && !aanRand(x - 1, y - 1) && isEmpty(x - 2, y - 2)) {
                return true;
            }
            return x + 1 < getWidth() && othersItem(x + 1, y - 1) && !aanRand(x + 1, y - 1) && isEmpty(x + 2, y - 2);
        }
        return false;
    }

    public boolean slagMogelijkZuid(int x, int y) {
        if (y + 1 < getHeight()) {
            if (x - 1 > 0 && othersItem(x - 1, y + 1) && !aanRand(x - 1, y + 1) && isEmpty(x - 2, y + 2)) {
                return true;

            }
            return x + 1 < getWidth() && othersItem(x + 1, y + 1) && !aanRand(x + 1, y + 1) && isEmpty(x + 2, y + 2);
        }

        return false;
    }

    public boolean slagMogelijk(int x, int y) {
        if (getPos(x, y) == getActivePlayerIndex()) {
            /** Enkel voorwaarts checken **/
            if (getPos(x, y) == 1) {
                return slagMogelijkZuid(x, y);
            } else {
                return slagMogelijkNoord(x, y);
            }
        } else {
            /** Zowel voorwaarts als achterwaarts checken ***/
            if (slagMogelijkNoord(x, y)) {
                return true;
            } else {
                return slagMogelijkZuid(x, y);
            }
        }
    }

    public List<Point> mogelijkeZetten(Point p) {
        return mogelijkeZetten((int) p.getX(), (int) p.getY());
    }

    public List<Point> mogelijkeZetten(int x, int y) {
        if (selected != null) {
            if (getPos((int) selected.getX(), (int) selected.getY()) == getActivePlayerIndex()) {
                return getMogelijkeVoorwaartseZetten(x, y,getActivePlayerIndex());
            } else {
                return getMogelijkeZetten(x, y,getActivePlayerIndex());
            }
        } else {
            return null;
        }
    }

    public List<Point> getMogelijkeVoorwaartseZetten(Point p, int playerindex) {
        return getMogelijkeVoorwaartseZetten((int) p.getX(), (int) p.getY(),playerindex);
    }

    public List<Point> getMogelijkeVoorwaartseZetten(int x, int y, int playerindex) {
        ArrayList<Point> zetten = new ArrayList<Point>();
        if (playerindex == 1) {
            /** Wit verplaatst zuid **/
            if (y + 1 < getHeight()) {
                if (x - 1 >= 0) {
                    if (isEmpty(x - 1, y + 1)) {
                        zetten.add(new Point(x - 1, y + 1));
                    } else if (y + 1 < getHeight() - 1 && othersItem(x - 1, y + 1, 1) && !aanRand(x - 1, y + 1) && isEmpty(x - 2, y + 2)) {
                        zetten.add(new Point(x - 2, y + 2));
                    }
                }
                if (x + 1 <= getWidth()) {
                    if (isEmpty(x + 1, y + 1)) {
                        zetten.add(new Point(x + 1, y + 1));
                    } else if (y + 1 < getHeight() - 1 && othersItem(x + 1, y + 1 , 1) && !aanRand(x + 1, y + 1) && isEmpty(x + 2, y + 2)) {
                        zetten.add(new Point(x + 2, y + 2));
                    }
                }
            }
        } 
        /** Zwart verplaatst noord **/
        else {
            if (y - 1 >= 0) {
                if (x - 1 >= 0) {
                    if (isEmpty(x - 1, y - 1)) {
                        zetten.add(new Point(x - 1, y - 1));
                    } else if (y - 1 > 0 && othersItem(x - 1, y - 1, 2) && !aanRand(x - 1, y - 1) && isEmpty(x - 2, y - 2)) {
                        zetten.add(new Point(x - 2, y - 2));
                    }
                }
                if (x + 1 <= getWidth()) {
                    if (isEmpty(x + 1, y - 1)) {
                        zetten.add(new Point(x + 1, y - 1));
                    } else if (y - 1 > 0 && othersItem(x + 1, y - 1, 2) && !aanRand(x + 1, y - 1) && isEmpty(x + 2, y - 2)) {
                        zetten.add(new Point(x + 2, y - 2));
                    }
                }

            }
        }
        
        return zetten;
    }

    public List<Point> getMogelijkeZetten(Point p, int playerindex) {
        return getMogelijkeZetten((int) p.getX(), (int) p.getY(),playerindex);
    }

    public List<Point> getMogelijkeZetten(int x, int y, int playerindex) {
        ArrayList<Point> zetten = new ArrayList<Point>();
        if (y + 1 < getHeight()) {
            if (x - 1 >= 0) {
                if (isEmpty(x - 1, y + 1)) {
                    zetten.add(new Point(x - 1, y + 1));
                } else if (y + 1 < getHeight() - 1 && othersItem(x - 1, y + 1, playerindex) && !aanRand(x - 1, y + 1) && isEmpty(x - 2, y + 2)) {
                    zetten.add(new Point(x - 2, y + 2));
                }
            }
            if (x + 1 <= getWidth()) {
                if (isEmpty(x + 1, y + 1)) {
                    zetten.add(new Point(x + 1, y + 1));
                } else if (y + 1 < getHeight() - 1 && othersItem(x + 1, y + 1, playerindex) && !aanRand(x + 1, y + 1) && isEmpty(x + 2, y + 2)) {
                    zetten.add(new Point(x + 2, y + 2));
                }
            }
        }
        if (y - 1 >= 0) {
            if (x - 1 >= 0) {
                if (isEmpty(x - 1, y - 1)) {
                    zetten.add(new Point(x - 1, y - 1));
                } else if (y - 1 > 0 && othersItem(x - 1, y - 1, playerindex) && !aanRand(x - 1, y - 1) && isEmpty(x - 2, y - 2)) {
                    zetten.add(new Point(x - 2, y - 2));
                }
            }
            if (x + 1 <= getWidth()) {
                if (isEmpty(x + 1, y - 1)) {
                    zetten.add(new Point(x + 1, y - 1));
                } else if (y - 1 > 0 && othersItem(x + 1, y - 1, playerindex) && !aanRand(x + 1, y - 1) && isEmpty(x + 2, y - 2)) {
                    zetten.add(new Point(x + 2, y - 2));
                }
            }

        }

        return zetten;
    }

    private void initBoard() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = getHeight() - 4; j < getHeight(); j++) {
                if (j % 2 == 0) {
                    if (i % 2 == 1) {
                        spelbord[i][j] = 2;
                    }
                } else {
                    if (i % 2 == 0) {
                        spelbord[i][j] = 2;
                    }
                }
            }

            
            for (int j = 0; j < 4; j++) {
                if (j % 2 == 0) {
                    if (i % 2 == 1) {
                        spelbord[i][j] = 1;
                    }
                } else {
                    if (i % 2 == 0) {
                        spelbord[i][j] = 1;
                    }
                }

            }
        }
        fireStateChanged();
    }

    public int getPos(int x, int y) {
        if (inRange(x, y)) {
            return spelbord[x][y];
        } else {
            return -1;
        }
    }

    public int getPos(Point p) {
        return getPos((int) p.getX(), (int) p.getY());
    }

    @Override
    protected void receive(Serializable data) {
        DammenMove mv = (DammenMove) data;
        move(mv.getOrigin(), mv.getDestination());
    }

    private int otherPlayerIndex() {
        if (getActivePlayerIndex() == 1) {
            return 2;
        } else {
            return 1;
        }
    }

    /** Als er geen plaatsen meer bezet zijn door de tegenspeler heeft de actieve speler gewonnen. **/
    private boolean checkIfFinished() {
        if(!checkItemsLeft(otherPlayerIndex())){
            gameFinished();
            return true;
        }
        
        if (!checkZettenLeft(otherPlayerIndex())){
            if(!checkZettenLeft(getActivePlayerIndex())){
                gameDrawed();   
                return true;
            } else {
                gameFinished();
                return true;
            }
        } else {
            if(!checkZettenLeft(getActivePlayerIndex())){
                nextUsersTurn();
                gameFinished();   
                return true;
            }
        } 
              
        return false;
    }

    private boolean checkZettenLeft(int playerindex) {
        int z = 0;
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (spelbord[i][j] == playerindex) {
                    z += getAantalMogelijkeVoorwaartseZetten(i, j,playerindex);
                } else if (spelbord[i][j] == playerindex + 2) {
                    z += getAantalMogelijkeZetten(i, j,playerindex);
                }
            }
        }
        
        return z != 0;
    }

    private boolean checkItemsLeft(int otherplayerindex) {
        if (!playerHasItemsLeft(otherplayerindex)) {
            return false;
        }
        return true;
    }

    private boolean item(int x, int y) {
        return spelbord[x][y] != 0;
    }

    private boolean playerHasItemsLeft(int playerindex) {
        if(playerindex == 1){
            return getWhite() != 0;
        } else {
            return getBlack() != 0;
        }
    }

    public int getActivePlayerIndex() {
        return getActiveUserIndex() + 1;
    }

    /** Geef de breedte van het spelbord qua aantal vakjes terug. 
    @return De breedte van het spelbord (aantal vakjes). **/
    public int getWidth() {
        return 10;
    }

    /** Geef de hoogte van het spelbord qua aantal vakjes terug. 
    @return De hoogte van het spelbord (aantal vakjes). **/
    public int getHeight() {
        return 10;
    }

    /** Geef het kleur terug voor de speler met nummer 'number'. 
    In deze kleur zal het gevraagde vakje op het spelbord ingevuld worden. 
    @param number Het nummer van de persoon waarvan men de kleur wenst te weten. 
    @return De kleur van de speler met nummer 'number'. **/
    public Color getPlayerColor(int number) {
        Color c = null;
        switch (number) {
            case 1:
                c = Color.WHITE;
                break;
            case 2:
                c = Color.BLACK;
                break;
            case 3:
                c= Color.WHITE;
                break;
            case 4:
                c = Color.BLACK;
                break;
        }
        return c;
    }
}
