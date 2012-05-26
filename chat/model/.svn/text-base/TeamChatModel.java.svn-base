package hawkge.chat.model;

import hawkge.chat.chatsession.ChatMessage;
import hawkge.chat.event.*;
import hawkge.chat.gui.InviteToChatRequest;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.network.events.ConnectFriendEvent;
import hawkge.storage.User;
import java.util.ArrayList;

/**
 * @create on May 11, 2012
 *
 * @author jorisvi
 */
public class TeamChatModel extends ChatModel implements EventListener {

    private final Object wait;
    private OnLineUserModel teamModelBlue;
    private OnLineUserModel teamModelRed;

    public TeamChatModel() {
        wait = new Object();
        teamModelBlue = new OnLineUserModel();
        teamModelRed = new OnLineUserModel();
    }

    public void addOwnUserToChat(boolean blue) {
        if (blue) {
            addUserToTeamBlue(ownUser);
        } else {
            addUserToTeamRed(ownUser);
        }
    }

    public TeamChatModel(String modelID) {
        super(modelID);
        wait = new Object();

        teamModelBlue = new OnLineUserModel();
        teamModelBlue.changeModelID(modelID);

        teamModelRed = new OnLineUserModel();
        teamModelRed.changeModelID(modelID);
    }

    @Override
    public ArrayList<User> getUsersInChat() {

        ArrayList<User> allUsers = new ArrayList<User>();

        for (User user : teamModelBlue.getUsers()) {
            allUsers.add(user);
        }

        for (User user : teamModelRed.getUsers()) {
            allUsers.add(user);
        }

        return allUsers;
    }

    public boolean containsUser(User user) {
        ArrayList<User> users = getUsersInChat();
        return users.contains(user);
    }

    public void removeUser(User user) {
        if (teamModelBlue.getUsers().contains(user)) {
            teamModelBlue.removeUser(user);
        } else if (teamModelRed.getUsers().contains(user)) {
            teamModelRed.removeUser(user);
        }
    }

    public void addUserToTeamBlue(User user) {
        teamModelBlue.addUser(user);
    }

    public void addUserToTeamRed(User user) {
        teamModelRed.addUser(user);
    }

    public OnLineUserModel getBlueUserModel() {
        return teamModelBlue;
    }

    public OnLineUserModel getRedUserModel() {
        return teamModelRed;
    }

    public void setBlueOnlineUserModel(OnLineUserModel teamModelBlue) {
        this.teamModelBlue = teamModelBlue;
    }

    public void setRedOnlineUserModel(OnLineUserModel teamModelRed) {
        this.teamModelRed = teamModelRed;
    }

    public void sendTeamMessage(ChatMessage message) {
        if (isUserInBlueTeam()) {
            EventQueue.queue(new TeamChatEvent(message, getTeamList(), ownUser, modelID, true, true));
        } else {
            EventQueue.queue(new TeamChatEvent(message, getTeamList(), ownUser, modelID, true, false));
        }
        receiveMessage(message);
    }

    public ArrayList<User> getTeamList() {
        if (isUserInBlueTeam()) {
            return teamModelBlue.getUsers();
        } else {
            return teamModelRed.getUsers();
        }
    }

    public ArrayList<User> getEnemyTeamList() {
        if (isUserInBlueTeam()) {
            return teamModelRed.getUsers();
        } else {
            return teamModelBlue.getUsers();
        }
    }

    public boolean isUserInBlueTeam() {
        return teamModelBlue.getUsers().contains(ownUser);
    }

    public boolean isUserInBlueTeam(User user) {
        return teamModelBlue.getUsers().contains(user);
    }

    public boolean isUserInRedTeam() {
        return teamModelRed.getUsers().contains(ownUser);
    }

    public boolean isUserInRedTeam(User user) {
        return teamModelRed.getUsers().contains(ownUser);
    }

    public boolean isUserInSameTeam(User user) {
        if ((isUserInBlueTeam(user) && isUserInBlueTeam()) || isUserInRedTeam() && isUserInRedTeam(user)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof ChatEvent) {
            ChatEvent chat = (ChatEvent) event;
            if (!chat.isTeamChat()) {
                if (chat.getModelID().equals(modelID)) {
                    if (!chat.isLocal()) {
                        receiveMessage(chat.getBericht());
                    }
                }
            } else {
                TeamChatEvent teamChat = (TeamChatEvent) event;
                if (teamChat.getModelID().equals(modelID)) {
                    if (!teamChat.isLocal()) {
                        receiveMessage(teamChat.getBericht());
                    }
                }
            }

        } else if (event instanceof AddToTeamChatRequest) {
            AddToTeamChatRequest request = (AddToTeamChatRequest) event;
            if (request.getModelID().equals(modelID)) {
                if (!request.isLocal()) {
                    InviteToChatRequest inviteRequest = new InviteToChatRequest(request.getSender());
                    if (inviteRequest.getAnswer()) {
                        AddToTeamChatAccepted accepted = new AddToTeamChatAccepted(ownUser, request.getFullUserlist(), modelID, request.shouldBeAddedToBlueTeam());
                        EventQueue.queue(accepted);
                    }
                }
            }
        } else if (event instanceof AddToTeamChatAccepted) {
            AddToTeamChatAccepted acceptedEvent = (AddToTeamChatAccepted) event;
            if (acceptedEvent.getModelID().equals(modelID)) {
                if (!acceptedEvent.isLocal()) {
                    if (acceptedEvent.shouldBeInblueTeam()) {
                        addUserToTeamBlue(acceptedEvent.getUser());
                    } else {
                        addUserToTeamRed(acceptedEvent.getUser());
                    }
                    if (isUserInBlueTeam()) {
                        EventQueue.queue(new UserAddedToTeamChat(ownUser, acceptedEvent.getUser(), modelID, true));
                    } else {
                        EventQueue.queue(new UserAddedToTeamChat(ownUser, acceptedEvent.getUser(), modelID, false));
                    }
                }
            }
        } else if (event instanceof UserAddedToTeamChat) {
            UserAddedToTeamChat userAddedEvent = (UserAddedToTeamChat) event;
            if (userAddedEvent.getModelID().equals(modelID)) {
                if (!userAddedEvent.isLocal()) {
                    if (userAddedEvent.isInBlueTeam()) {
                        addUserToTeamBlue(userAddedEvent.getUser());
                    } else {
                        addUserToTeamRed(userAddedEvent.getUser());
                    }
                }
            }
        } else if (event instanceof ConnectFriendEvent) {
            ConnectFriendEvent connectEvent = (ConnectFriendEvent) event;
            if (!connectEvent.isOnline()) {
                if (containsUser(connectEvent.getUser())) {
                    removeUser(connectEvent.getUser());
                }
            }
        } else if (event instanceof UserLeavingChatEvent) {
            UserLeavingChatEvent leaving = (UserLeavingChatEvent) event;
            if (leaving.getModelID().equals(modelID)) {
                if (!leaving.isLocal()) {
                    if (containsUser(leaving.getUserToRemove())) {
                        removeUser(leaving.getUserToRemove());
                    }
                } else {
                    EventQueue.getQueue().removeEventListener(this);
                }
            }
        }
    }
}
