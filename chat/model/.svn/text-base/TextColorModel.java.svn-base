/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.model;

import hawkge.Model;
import java.awt.Color;

/**
 *
 * @author Sels
 */
public class TextColorModel extends Model {

    int green = 50;
    int selectedGreen = 50;
    
    int red = 50;
    int selectedRed = 50;
    
    int blue = 50;
    int selectedBlue = 50;

    public void setSelectedGreenValue(int selectedGreen) {
        this.selectedGreen = selectedGreen;
        fireStateChanged();
    }

    public void setSelectedBlueValue(int selectedBlue) {
        this.selectedBlue = selectedBlue;
        fireStateChanged();
    }

    public void setSelectedRedValue(int selectedRed) {
        this.selectedRed = selectedRed;
        fireStateChanged();
    }

    public float getSelectedGreenValue() {
        return selectedGreen / 100f;
    }

    public float getSelectedBlueValue() {
        return selectedBlue / 100f;
    }

    public float getRedSelectedValue() {
        return selectedRed / 100f;
    }

    public int getSelectedGreenIntValue() {
        return selectedGreen;
    }
    
    public int getSelectedRedIntValue() {
        return selectedRed;
    }
    
    public int getSelectedBlueIntValue() {
        return selectedBlue;
    }
    
    public float getRedValue(){
        return red/100f;
    }
    
    public float getGreenValue(){
        return green/100f;
    }
    
    public float getBlueValue(){
        return blue/100f;
    }
    
    public void acceptChanges(){
        red = selectedRed;
        blue = selectedBlue;
        green = selectedGreen;
    }
    
    public void cancelChanges(){
        selectedRed = red;
        selectedGreen = green;
        selectedBlue = blue;
    }
    
    public Color getResultColor(){
        return new Color(getRedValue(),getGreenValue(),getBlueValue());
    }
}
