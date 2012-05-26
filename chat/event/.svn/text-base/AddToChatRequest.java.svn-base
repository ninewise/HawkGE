/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.event;

import hawkge.event.NetworkEvent;
import hawkge.storage.User;
import java.util.ArrayList;

/**
 *
 * @author Sels
 */
public class AddToChatRequest extends NetworkEvent {

    private User sender;
    private User receiver;
    private ArrayList<User> usersInChat;
    private ArrayList<User> allUsersInChat;
    private String modelID;

    /*
     * Dit event zal een event sturen naar de ontvanger met daarin een aanvraag
     * om een chat met de verstuurder te openen.
     */
    public AddToChatRequest(User sender, User receiver, ArrayList<User> usersInChat,String modelID) {
        super(receiver);
        this.sender = sender;
        this.receiver = receiver;
        this.usersInChat = usersInChat;
        this.modelID = modelID;
        allUsersInChat = new ArrayList<User>();

    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public ArrayList<User> getFullUserlist() {
        for (User user : usersInChat) {
            allUsersInChat.add(user);
        }
        allUsersInChat.add(sender);
        return allUsersInChat;
    }
    public String getModelID(){
        return modelID;
    }
}
