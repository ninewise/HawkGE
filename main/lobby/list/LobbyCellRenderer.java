package hawkge.main.lobby.list;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Set the color for the JList of the lobby
 * @create on Apr 28, 2012
 * @author jorisvi
 */
public class LobbyCellRenderer extends JLabel implements ListCellRenderer{

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        setOpaque(true);
        if (((UserListComponent) value).getUserState() == UserState.ONLINE) {
            setForeground(new Color(0, 210, 0));
        } else if (((UserListComponent) value).getUserState() == UserState.BLOCKED) {
            setForeground(new Color(0, 0, 0));
        } else {
            setForeground(new Color(210, 0, 0));
        }
        if (isSelected) {
            setBackground(new Color(250, 250, 0));
        } else {
            setBackground(Color.WHITE);
        }
        return this;
    }
}