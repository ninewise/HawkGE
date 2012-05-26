/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.area;

import hawkge.chat.chatsession.ChatMessage;
import hawkge.chat.model.ChatModel;
import hawkge.chat.model.FontSelectorModel;
import hawkge.chat.model.TextColorModel;
import hawkge.storage.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author Sels
 */
public class ChatFieldArea extends JTextArea implements KeyListener {

    /*
     * Klasse voor de te verzenden tekst
     */
    private ChatModel model;
    private TextColorModel colorModel;
    private FontSelectorModel selectorModel;

    public ChatFieldArea(ChatModel model, TextColorModel colorModel,FontSelectorModel selectorModel) {
        addKeyListener(this);
        this.colorModel = colorModel;
        this.selectorModel = selectorModel;
        this.model = model;
    }

    public void keyTyped(KeyEvent ke) {
    }

    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER && !ke.isShiftDown()) {
            clearText();
        }
    }

    /*
     * nog aanpassen voor kleur en font
     */
    public void keyPressed(KeyEvent ke) {
        if (ke.isShiftDown() && ke.getKeyCode() == KeyEvent.VK_ENTER) {
            newLine();
        } else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            String text = getText();
            User user = model.getOwnUser();
            ArrayList<User> receivers = model.getUsersInChat();
            Color color = colorModel.getResultColor();
            Font font = selectorModel.getResultFont();
            ChatMessage message = new ChatMessage(text, user, receivers, font, color,false);
            model.sendMessage(message);
        }
    }

    private void clearText() {
        setText("");
    }

    private void newLine() {
        append("\n");
    }
}
