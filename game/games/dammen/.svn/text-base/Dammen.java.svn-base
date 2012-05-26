import hawkge.chat.model.OnLineUserModel;
import hawkge.game.Game;
import hawkge.game.GameSessionInfo;
import hawkge.game.models.GameModel;
import hawkge.game.gui.GameView;

/**
 *
 * @author michaelkint
 */

public class Dammen extends Game {
    
    public Dammen(){
        super("Dammen",2,2);
     }
    
    @Override
    public GameView getGameView(GameModel model){
        return new DammenGameView(model);
    }

    @Override
    public int getScore(int place) {
        if(place == 1) return 10;
        return 0;
    }

    @Override
    public String getRules() {
        String rules;
        rules = " Dammen (or checkers), is a Game that is played by 2 people. Each player"
                + "\n gets a color of 'dams', white or black. The GameBoard consists of 20" 
                + "\n 'dams' of each color. The 'dams' are placed on opposite sides of the board. "
                + "\n \n The objective of the game is to either 'hit' all of the opponent's dams "
                + "\n (hitting them means jump over them when you are in a square next to it)," 
                + "\n or to play the game in such a way that your opponent has no moves left and "
                + "\n has to surrender."
                + "\n \n Players can only move one square at once, unless they can 'hit' the"
                + "\n opponent, and after doing so have the possibility to hit another one of the "
                + "\n opponent's dams. A player can only move forward (unless they reach the other "
                + "\n side, like specified in the next paragraph. )"
                + "\n \n When a player reaches the other side with one of his 'dams', the dam will"
                + "\n become a 'king'. This means that the dam can move either forward and afterward. "
                + "\n \n The last rule is the 'piece touché' rule. This rule says that, when you "
                + "\n touch one of your dams (one that can da a legit move), you can't change to"
                + "\n another dam anymore, but you have to move the chosen dam. When you touch a dam"
                + "\n that can't make a move, you have to possibility to choose another dam (since "
                + "\n you still have dams left that you can move; if this was not the case you would"
                + "\n have lost the move before.)";
        return rules;
    }

    @Override
    public GameModel getGameModel(OnLineUserModel onlineusermodel, int numberofplayers) {
        return new DammenGameModel(onlineusermodel,numberofplayers,this);
    }

   @Override
    public GameModel getGameModel(OnLineUserModel onlineusermodel, GameSessionInfo info) {
        return new DammenGameModel(onlineusermodel,this, info);
    }
}
