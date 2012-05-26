/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.model;

import hawkge.Model;
import java.awt.Font;

/**
 *
 * @author Sels
 */
public class FontSelectorModel extends Model {

    private String selectedFont = "Arial";
    private String font = "Arial";
    private int selectedSize = 14;
    private int size = 14;
    private int selectedFontIndex = 6;
    private int selectedSizeIndex = 4;
    private int fontIndex = 6;
    private int sizeIndex = 4;
    private boolean selectedBold = false;
    private boolean bold = false;
    private boolean selectedItalic = false;
    private boolean italic = false;

    public FontSelectorModel() {
    }

    public void setSelectedFont(String selectedFont) {
        this.selectedFont = selectedFont;
        fireStateChanged();
    }

    public void setSelectedSize(int selectedSize) {
        this.selectedSize = selectedSize;
        fireStateChanged();
    }

    public String getSelectedFont() {
        return selectedFont;
    }

    public int getSelectedSize() {
        return selectedSize;
    }

    public String getFont() {
        return font;
    }

    public int getSize() {
        return size;
    }

    public void acceptChanged() {
        size = selectedSize;
        font = selectedFont;
        fontIndex = selectedFontIndex;
        sizeIndex = selectedSizeIndex;
        italic = selectedItalic;
        bold = selectedBold;
    }

    public void cancelChanged() {
        selectedSize = size;
        selectedFont = font;
        selectedFontIndex = fontIndex;
        selectedSizeIndex = sizeIndex;
        selectedItalic = italic;
        selectedBold = bold;
    }

    public int getFontIndex() {
        return fontIndex;
    }

    public int getSizeIndex() {
        return sizeIndex;
    }

    public int getSelectedFontIndex() {
        return selectedFontIndex;
    }

    public int getSelectedSizeIndex() {
        return selectedSizeIndex;
    }

    public void setSelectedSizeIndex(int selectedSizeIndex) {
        this.selectedSizeIndex = selectedSizeIndex;
    }

    public void setSelectedFontIndex(int selectedFontIndex) {
        this.selectedFontIndex = selectedFontIndex;
    }

    public void setItalicSelected() {
        selectedItalic = !selectedItalic;
        fireStateChanged();
    }

    public void setBoldSelected() {
        selectedBold = !selectedBold;
        fireStateChanged();
    }

    public int getItalicSelected() {
        if (selectedItalic) {
            return Font.ITALIC;
        } else {
            return 0;
        }
    }

    public int getBoldSelected() {
        if (selectedBold) {
            return Font.BOLD;
        } else {
            return 0;
        }
    }

    public int getItalic() {
        if (italic) {
            return Font.ITALIC;
        } else {
            return 0;
        }
    }

    public int getBold() {
        if (bold) {
            return Font.BOLD;
        } else {
            return 0;
        }
    }

    public boolean boldSelected() {
        return bold;
    }

    public boolean italicSelected() {
        return italic;
    }

    public Font getResultFont() {
        return new Font(font, getItalic() + getBold(), size);
    }

    public void setFontIndex(int fontIndex) {
        this.fontIndex = fontIndex;
    }
}
