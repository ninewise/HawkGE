/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawkge.chat.model;

import hawkge.Model;
import hawkge.chat.chatsession.ChatMessage;
import hawkge.chat.event.*;
import hawkge.chat.gui.InviteToChatRequest;
import hawkge.event.Callable;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.network.IPAddress;
import hawkge.network.events.ConnectFriendEvent;
import hawkge.storage.User;
import hawkge.storage.events.UserEvent;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Sels
 */
public class ChatModel extends Model implements EventListener {

    private ChatMessage received;
    private final Object wait;
    private OnLineUserModel model;
    protected User ownUser;
    protected String modelID;
    private boolean waitCondition;

    public ChatModel() {
        wait = new Object();
        model = new OnLineUserModel();
        EventQueue.getQueue().addEventListener(this);
        setCondition(true);
        askUser();
        synchronized (wait) {
            try {
                while (waitCondition) wait.wait();
            } catch (InterruptedException ex) {
                System.out.println("Waiting interrupt: " + ex);
            }
        }
        createModelID();
    }
    
    private synchronized void setCondition(Boolean condition) {
        this.waitCondition = condition;
    }
    
    public ChatModel(String modelID) {
        wait = new Object();
        model = new OnLineUserModel();
        model.changeModelID(modelID);
        this.modelID = modelID;
        
        EventQueue.getQueue().addEventListener(this);
        askUser();
    }

    public ArrayList<User> getUsersInChat() {
        return model.getUsers();
    }

    public void sendMessage(ChatMessage chatMessage) {
        EventQueue.queue(new ChatEvent(chatMessage, chatMessage.getReceivers(), ownUser, modelID,false));
        receiveMessage(chatMessage);
    }

    public void receiveMessage(ChatMessage chatMessage) {
        received = chatMessage;
        fireStateChanged();
    }

    public void addUserToChat(User user) {
        model.addUser(user);
    }

    public ChatMessage getLastMessage() {
        return received;
    }

    public OnLineUserModel getUserModel() {
        return model;
    }

    public void setOnlineUserModel(OnLineUserModel model) {
        this.model = model;
    }

    public void onEvent(Event event) {
        if (event instanceof ChatEvent) {
            ChatEvent chat = (ChatEvent) event;
            if (chat.getModelID().equals(modelID)) {
                if (!chat.isLocal()) {
                    ChatEvent chatevent = (ChatEvent) event;
                    receiveMessage(chatevent.getBericht());
                }
            }
        } else if (event instanceof AddToChatRequest) {
            AddToChatRequest request = (AddToChatRequest) event;
            if (request.getModelID().equals(modelID)) {
                if (!request.isLocal()) {
                    InviteToChatRequest inviteRequest = new InviteToChatRequest(request.getSender());
                    if (inviteRequest.getAnswer()) {

                        //Veranderderen van de ID

                        AddToChatAccepted accepted = new AddToChatAccepted(ownUser, request.getFullUserlist(), modelID);
                        EventQueue.queue(accepted);
                    }
                }
            }
        } else if (event instanceof AddToChatAccepted) {
            AddToChatAccepted acceptedEvent = (AddToChatAccepted) event;
            if (acceptedEvent.getModelID().equals(modelID)) {
                if (!acceptedEvent.isLocal()) {
                    addUserToChat(acceptedEvent.getUser());
                    EventQueue.queue(new UserAddedToChat(ownUser, acceptedEvent.getUser(), modelID));
                }
            }
        } else if (event instanceof UserAddedToChat) {
            UserAddedToChat userAddedEvent = (UserAddedToChat) event;
            if (userAddedEvent.getModelID().equals(modelID)) {
                if (!userAddedEvent.isLocal()) {
                    addUserToChat(userAddedEvent.getUser());
                }
            }
        } else if (event instanceof ConnectFriendEvent) {
            ConnectFriendEvent connectEvent = (ConnectFriendEvent) event;
            if (!connectEvent.isOnline()) {
                if (model.containsUser(connectEvent.getUser())) {
                    model.removeUser(connectEvent.getUser());
                }
            }
        } else if (event instanceof UserLeavingChatEvent) {
            UserLeavingChatEvent leaving = (UserLeavingChatEvent) event;
            if (leaving.getModelID().equals(modelID)) {
                if (!leaving.isLocal()) {
                    if (model.containsUser(leaving.getUserToRemove())) {
                        model.removeUser(leaving.getUserToRemove());
                    }
                } else {
                    EventQueue.getQueue().removeEventListener(this);
                }
            }
        }
    }

    protected void askUser() {
        EventQueue.queue(new UserEvent(new Callable<User>() {
            public void call(User user) {
                saveOwn(user);
                synchronized (wait) {
                    setCondition(false);
                    wait.notifyAll();
                }
            }
        }));
    }

    private void saveOwn(User ownUser) {
        this.ownUser = ownUser;
    }

    public User getOwnUser() {
            return ownUser;   
    }

    public String getId() {
        return modelID;
    }

    public void createModelID() {
        String name = ownUser.toString();
        Calendar cal = Calendar.getInstance();
        String time = cal.getTime() + "";
        modelID = name + " " + time;
        model.changeModelID(modelID);
     }
}
