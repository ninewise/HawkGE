package hawkge.game;

/**
 * Geeft het resultaat terug voor de verschillende manieren waarop een spel kan aflopen. 
 * Elk van deze manieren heeft een andere manier van quoteren. 
 * @author michaelkint
 */
public enum GameResult {
    WON {
        public int getResult(){
            return 1;
        }
    }   
    ,LOST {
        public int getResult(){
            return -1;
        }
    }
    ,QUIT {
        public int getResult(){
            return -1;
        }
    }
    ,DRAW {
        public int getResult(){
            return 0;
        }
    };
    
    /** Geef het resultaat terug voor een bepaalde manier van speleinde. 
        @retun Het resultaat voor een bepaalde manier van speleinde. **/
    public abstract int getResult();
}
