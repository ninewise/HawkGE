package hawkge.main.lobby.list;

import hawkge.chat.event.AddToChatAccepted;
import hawkge.chat.event.AddToChatRequest;
import hawkge.chat.event.AddToTeamChatAccepted;
import hawkge.chat.event.AddToTeamChatRequest;
import hawkge.chat.gui.ChatFrame;
import hawkge.chat.gui.InviteToChatRequest;
import hawkge.chat.gui.TeamChatFrame;
import hawkge.chat.model.ChatModel;
import hawkge.chat.model.TeamChatModel;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.network.events.ConnectFriendEvent;
import hawkge.storage.usermanager.blocking.BlockedEvent;
import hawkge.storage.usermanager.befriending.FriendAddedEvent;
import hawkge.storage.usermanager.befriending.FriendRemovedEvent;
import hawkge.storage.usermanager.blocking.DeblockedEvent;

/**
 * Lobby EventsListener lists to the events on the queue and let it know to the
 * lobbys @create on May 7, 2012
 *
 * @author jorisvi
 */
public class LobbyEventListener implements EventListener {

    private LobbyListModel model;

    public LobbyEventListener(LobbyListModel model) {       
        this.model = model;
        EventQueue.getQueue().addEventListener(this);
    }

    /**
     * Called when an event is on the queue
     *
     * @param event
     */
    public void onEvent(Event event) {
        if (event instanceof ConnectFriendEvent) {
            ConnectFriendEvent friend = (ConnectFriendEvent) event;
            if (friend.isOnline()) {
                model.setUserState(UserState.ONLINE, friend.getUser());
            } else {
                model.setUserState(UserState.OFFLINE, friend.getUser());
            }
        } else if (event instanceof FriendAddedEvent) {
            model.addUser(UserState.OFFLINE ,((FriendAddedEvent) event).getFriend());
        } else if (event instanceof FriendRemovedEvent) {
            model.removeUser(((FriendRemovedEvent) event).getFriend());
        } else if (event instanceof BlockedEvent) {
            model.setUserState(UserState.BLOCKED, ((BlockedEvent) event).getBlock());
        } else if (event instanceof DeblockedEvent) {
            model.setUserState(UserState.OFFLINE, ((DeblockedEvent)event).getUser());
        } else if (event instanceof AddToChatRequest) {
            AddToChatRequest request = (AddToChatRequest) event;
            if (!request.isLocal()) {
                InviteToChatRequest inviteRequest = new InviteToChatRequest(request.getSender());
                if (inviteRequest.getAnswer()) {
                    ChatModel chatModel = new ChatModel(request.getModelID());
                    ChatFrame frame = new ChatFrame(chatModel);
                    AddToChatAccepted accept = new AddToChatAccepted(chatModel.getOwnUser(), request.getFullUserlist(), request.getModelID());
                    EventQueue.queue(accept);
                }

            }
        }
        else if (event instanceof AddToTeamChatRequest) {
            AddToTeamChatRequest request = (AddToTeamChatRequest) event;
            if (!request.isLocal()) {
                InviteToChatRequest inviteRequest = new InviteToChatRequest(request.getSender());
                if (inviteRequest.getAnswer()) {
                    TeamChatModel chatModel = new TeamChatModel(request.getModelID());
                    TeamChatFrame frame = new TeamChatFrame(chatModel);
                    if ( request.shouldBeAddedToBlueTeam()){
                        chatModel.addOwnUserToChat(true);
                    }
                    else{
                        chatModel.addOwnUserToChat(false);
                    }
                    AddToTeamChatAccepted accept = new AddToTeamChatAccepted(chatModel.getOwnUser(), request.getFullUserlist(), request.getModelID(),request.shouldBeAddedToBlueTeam());
                    EventQueue.queue(accept);
                }

            }
        }
    }
}