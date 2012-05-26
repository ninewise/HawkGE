/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.chatsession;

import hawkge.chat.model.*;
import javax.swing.JPanel;

/**
 *
 * @author Sels
 */
public abstract class ChatSession extends JPanel {

    protected ChatModel chatmodel;
    protected TextColorModel colorModel;
    protected FontSelectorModel selectorModel;
    protected TeamChatModel teamModel;

    /*
     * Abstracte klasse voor de twee subklassen GameChat en NormalChat
     */
    public ChatSession(ChatModel chatmodel, TextColorModel colorModel, FontSelectorModel selectorModel) {
        this.chatmodel = chatmodel;
        this.colorModel = colorModel;
        this.selectorModel = selectorModel;
    }

    public ChatSession(TeamChatModel teamModel, TextColorModel colorModel, FontSelectorModel selectorModel) {
        this.teamModel = teamModel;
        this.colorModel = colorModel;
        this.selectorModel = selectorModel;
    }

    public abstract void initializeLayout();

    public ChatModel getChatModel() {
        return chatmodel;
    }

    public OnLineUserModel getOnLineuserModel() {
        return chatmodel.getUserModel();
    }

    public TextColorModel getTextColorModel() {
        return colorModel;
    }
}