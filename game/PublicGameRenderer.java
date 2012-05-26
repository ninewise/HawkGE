package hawkge.game;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Cellrenderer voor de lijst met public games.
 * Toont de value (een gamesessioninfo object in de lijst)
 * De naam van de game, de host en het aantal users wordt weergeven.
 * @author michaelkint
 */
public class PublicGameRenderer extends JLabel implements ListCellRenderer {

    public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) { 
        GameSessionInfo info = ((GameSessionInfo) value);
        setText("<html>Game : " + info.getGame().toString()
                                        +"<br>Host : " + info.getHost()
                                        +"<br>Number of users : " + info.getNumberofusers() + "</html>");       
        setOpaque(true);
        
        if(!isSelected){
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        } else {
            setBackground(Color.BLUE);
            setForeground(Color.WHITE);
        }
        return this;
    }
}
