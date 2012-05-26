/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.textpane;

import hawkge.chat.chatsession.ChatMessage;
import hawkge.chat.event.*;
import hawkge.chat.model.TeamChatModel;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.text.Style;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Sels
 */

/*
 * Probleem met users die iets zeggen in team en daarna in all Naam moet getoond
 * worden
 */
public class TeamChatWindowTextPane extends JTextPane implements ChangeListener, EventListener {

    private TeamChatModel teamModel;
    private String lastuser = "";
    private StyledDocument doc = getStyledDocument();
    private Style style;
    private Style saidStyle;
    private Style addStyle;
    private boolean isTeam = false;
    private boolean notRecentlyAdded = false;

    public TeamChatWindowTextPane(TeamChatModel teamModel) {

        this.teamModel = teamModel;
        EventQueue.getQueue().addEventListener(this);
        teamModel.addChangeListener(this);
        this.setEditable(false);
        style = addStyle("Chat style", null);
        addStyle = addStyle("added style", null);
        saidStyle = addStyle("User said style", null);
        StyleConstants.setForeground(saidStyle, Color.BLACK);

    }

    public void addText() {

        ChatMessage message = teamModel.getLastMessage();

        String text = message.getText();
        String info = getMessageInfo(message);

        Color color = message.getColor();
        Font font = message.getFont();

        ArrayList<String> textArray = buildText(text);
        StyleConstants.setForeground(style, color);
        StyleConstants.setFontFamily(style, font.getFamily());

        StyleConstants.setFontSize(style, font.getSize());
        StyleConstants.setItalic(style, font.isItalic());
        StyleConstants.setBold(style, font.isBold());

        if (lastuser.equals(info) && message.isTeamMessage() == isTeam && !notRecentlyAdded) {
            for (String line : textArray) {
                try {
                    doc.insertString(doc.getLength(), line, style);
                } catch (BadLocationException e) {
                    System.out.println("Probleem met windowpane");
                }
            }
        } else {
            lastuser = info;
            notRecentlyAdded = false;
            isTeam = message.isTeamMessage();
            try {
                if (message.isTeamMessage()) {
                    doc.insertString(doc.getLength(), "[TEAM] " + info + " said:\n", saidStyle);
                } else {
                    doc.insertString(doc.getLength(), "[ALL] " + info + " said:\n", saidStyle);

                }
            } catch (BadLocationException e) {
            }
            for (String line : textArray) {
                try {
                    doc.insertString(doc.getLength(), line, style);
                } catch (BadLocationException e) {
                    System.out.println("Probleem met windowpane");
                }
            }
        }

    }

    public void stateChanged(ChangeEvent e) {
        addText();
        setCaretPosition(doc.getLength());
    }

    private String getMessageInfo(ChatMessage message) {
        if (message.getSender() != null) {
            String sender = message.getSender().toString();
            return sender;
            //TODO verwijderen
        }
        return null;
    }

    //Spam filter
    private ArrayList<String> buildText(String text) {
        String[] strings = text.split("\n");
        ArrayList<String> resultaat = new ArrayList<String>();
        for (int arrayCount = 0; arrayCount < strings.length; arrayCount++) {
            if (!strings[arrayCount].equals("")) {
                resultaat.add(strings[arrayCount] + "\n");
                resultaat.add("   ");
            }
        }
        if (resultaat.size() > 0) {
            String begin = "   " + resultaat.get(0);
            resultaat.remove(0);
            resultaat.add(0, begin);
            resultaat.remove(resultaat.size() - 1);
        }

        return resultaat;
    }

    public void onEvent(Event event) {
        if (event instanceof AddToTeamChatAccepted) {
            AddToTeamChatAccepted addToChatAccepted = (AddToTeamChatAccepted) event;
            if (addToChatAccepted.getModelID().equals(teamModel.getId())) {
                if (!addToChatAccepted.isLocal()) {
                    try {
                        StyleConstants.setForeground(addStyle, Color.black);
                        String added = addToChatAccepted.getUser() + " has been added to the chat. \n";
                        notRecentlyAdded = true;
                        doc.insertString(doc.getLength(), added, addStyle);
                    } catch (BadLocationException e) {
                        System.out.println("Probleem met windowpane");
                    }
                } else {
                    try {
                        StyleConstants.setForeground(addStyle, Color.black);
                        String added = "You have entered the chat. \n";
                        doc.insertString(doc.getLength(), added, addStyle);
                    } catch (BadLocationException e) {
                        System.out.println("Probleem met windowpane");
                    }
                }
            }
        } else if (event instanceof UserLeavingChatEvent) {
            UserLeavingChatEvent userLeftEvent = (UserLeavingChatEvent) event;
            System.out.println("user leaving");
            if (teamModel.getId().equals(userLeftEvent.getModelID())) {
                if (!userLeftEvent.isLocal()) {
                    try {
                        StyleConstants.setForeground(style, Color.black);
                        String left = userLeftEvent.getUserToRemove().toString() + " has left the chat. \n";
                        doc.insertString(doc.getLength(), left, style);
                    } catch (BadLocationException e) {
                        System.out.println("Probleem met windowpane");
                    }
                } else {
                    EventQueue.getQueue().removeEventListener(this);
                }
            }
        }
    }
}
