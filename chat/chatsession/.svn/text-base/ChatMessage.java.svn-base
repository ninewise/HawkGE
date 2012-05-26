/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.chatsession;

import hawkge.storage.User;
import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Sels
 */
public class ChatMessage implements Serializable {

    private String text;
    private Date date;
    private User sender;
    private ArrayList<User> receivers;
    private Font font;
    private Color color;
    private boolean isTeamMessage;

    /*
     * Deze klasse wordt gebruikt om een chatbericht voor te stellen en over het
     * netwerk te zenden
     */
    
    public ChatMessage(String text, User sender, ArrayList<User> receivers, Font font, Color color,boolean isTeamChat) {
        this.text = text;
        this.sender = sender;
        this.receivers = receivers;
        this.font = font;
        this.color = color;
        this.isTeamMessage = isTeamChat;
        date = new Date();
       
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
    }
    
    public User getSender(){
            return sender;
    }
    
    public ArrayList<User> getReceivers(){
        return receivers;
    }
    
    public Font getFont(){
        return font;
    }
    
    public Color getColor(){
        return color;
    }
    
    public boolean isTeamMessage(){
        return isTeamMessage;
    }
}
