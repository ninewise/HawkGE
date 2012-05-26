package hawkge.game.models;

import hawkge.Model;
import hawkge.game.Game;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 * ComboBoxModel voor het kiezen van een Game in het GameSettings paneel.
 * @author michaelkint
 */
public class GameComboBoxModel extends Model implements ComboBoxModel {

    private List<Game> games;
    private Object selected;

    /** Constructoor voor een implementatie van een ComboBoxModel, dat wordt
     gebruikt om games te overlopen. 
     @param games De lijst met games waaruit gekozen dient te worden. **/
    public GameComboBoxModel(List<Game> games) {
        this.games = games; 
        if(games.size()> 0) selected = games.get(0);
    }

    /** Stel een nieuw gesecteerde Game (Object) in.
    @param o De nieuwe in te stellen Game (Object). **/
    @Override
    public void setSelectedItem(Object o) {
        if (o != selected) {
            selected = o;
            fireStateChanged();
        }
    }

    /** Welke Game (welk object) is momenteel geselecteerd.
    @return De huidige geselecteerde Game. **/
    @Override
    public Object getSelectedItem() {
        return selected;
    }

    /** Hoeveel Games zijn er aanwezig in de ComboBox. 
    @return Het aantal games waaruit een speler kan kiezen. **/
    @Override
    public int getSize() {
        return games.size();
    }

    /** Geef de Game (het object) op positie i in de combobox terug. 
    @param i De gevraagde spelindex. 
    @return De Game op index i in de combobox. **/
    @Override
    public Object getElementAt(int i) {
        return games.get(i);
    }

    /** Voeg een ListDataListener toe aan het comboboxmodel. 
    @param De ListDataListener die naar dit comboboxmodel moet luisteren. **/
    @Override
    public void addListDataListener(ListDataListener ll) {
    }

    @Override
    /** Verwijder een ListDataListener van het comboboxmodel.
    @param De ListDataListener die verwijderd moet worden. **/
    public void removeListDataListener(ListDataListener ll) {
    }
}
