package hawkge.storage.events;

import hawkge.storage.userstats.events.PasswordChangedEvent;
import hawkge.storage.gameloading.events.TestGameEvent;
import hawkge.storage.gameloading.events.GameListEvent;
import hawkge.storage.gameloading.events.GameAddedEvent;
import hawkge.storage.gameloading.events.EnsureGameEvent;
import hawkge.storage.gameloading.events.AddGameEvent;
import hawkge.storage.gameloading.events.RequestGameEvent;
import hawkge.storage.gameloading.events.GameRequestedEvent;
import hawkge.storage.usermanager.befriending.RemoveFriendEvent;
import hawkge.storage.usermanager.befriending.FriendsListEvent;
import hawkge.storage.usermanager.befriending.FriendRemovedEvent;
import hawkge.storage.usermanager.befriending.FriendAddedEvent;
import hawkge.storage.usermanager.befriending.AddUserAsFriendEvent;
import hawkge.storage.usermanager.befriending.AddFriendEvent;
import hawkge.storage.usermanager.blocking.DeblockedEvent;
import hawkge.storage.usermanager.blocking.DeblockEvent;
import hawkge.storage.usermanager.blocking.BlocksListEvent;
import hawkge.storage.usermanager.blocking.BlockedEvent;
import hawkge.storage.usermanager.blocking.BlockEvent;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.event.QueueClosedEvent;
import hawkge.game.events.GameFinishedEvent;
import hawkge.game.events.GameInterruptedEvent;
import hawkge.network.events.UpdateUserEvent;
import hawkge.storage.gameloading.GameReceiver;
import hawkge.storage.gameloading.GameSender;
import hawkge.storage.LocalManager;
import hawkge.storage.StoreException;
import hawkge.storage.User;
import hawkge.storage.gameloading.events.RemoveGameEvent;
import hawkge.storage.userstats.GameStat;
import hawkge.storage.userstats.events.DescriptionEvent;
import hawkge.storage.userstats.events.LocalUserStatEvent;
import hawkge.storage.userstats.events.ShowGameStatEvent;
import hawkge.storage.userstats.events.ShowUserStatEvent;
import hawkge.storage.userstats.events.UserStatRequestEvent;
import hawkge.storage.userstats.gui.UserStatsFrame;
import javax.swing.SwingUtilities;

public class StorageEventListener implements EventListener {

    private LocalManager localManager;
    private String pass;
    private boolean accountRemoved;

    /**
     * Creates a new StorageEventListener.
     * @param queue The EventQueue to listen to.
     * @param name  The name of the logged in user.
     * @param pass  The password of the logged in user.
     */
    public StorageEventListener(User user, String pass) {
        this.pass = pass;
        this.accountRemoved = false;
        LocalManager newlm = new LocalManager(user);
        this.localManager = LocalManager.load(newlm.getLocation(), pass);
        if(this.localManager == null) this.localManager = newlm;
        EventQueue.getQueue().addEventListener(this);
    }

    public synchronized void onEvent(Event event) {

        if(event instanceof RemoveAccountEvent) {
            try {
                localManager.remove();
                accountRemoved = true;
                event.callback(null);
            } catch (StoreException ex) {
                ex.printStackTrace();
            }
            return;
        }

        if(event instanceof VerifyPassEvent) {
            VerifyPassEvent verifyPassEvent = (VerifyPassEvent) event;
            event.callback(this.pass.equals(verifyPassEvent.getPass()));
            return;
        }

        if(event instanceof PasswordChangedEvent) {
            PasswordChangedEvent passwordChangedEvent = (PasswordChangedEvent) event;
            this.pass = passwordChangedEvent.getPass();
            return;
        }

        if (event instanceof AddFriendEvent) {
            localManager.getUserManager().showAddFriendDialog();
            return;
        }

        if(event instanceof BlockEvent) {
            BlockEvent blockEvent = (BlockEvent) event;
            if(localManager.getUserManager().addBlock(blockEvent.getToBlock())) {
                EventQueue.queue(new BlockedEvent(blockEvent.getToBlock()));
            }
            return;
        }

        if(event instanceof DeblockEvent) {
            DeblockEvent deblockEvent = (DeblockEvent) event;
            if(localManager.getUserManager().removeBlock(deblockEvent.getBlock())) {
                EventQueue.queue(new DeblockedEvent(deblockEvent.getBlock()));
                localManager.getUserManager().addFriend(deblockEvent.getBlock());
            }
            return;
        }

        if(event instanceof BlocksListEvent) {
            event.callback(localManager.getUserManager().getBlocks());
            return;
        }

        if(event instanceof AddUserAsFriendEvent) {
            AddUserAsFriendEvent addUserAsFriendEvent = (AddUserAsFriendEvent) event;
            boolean r = localManager.getUserManager().addFriend(addUserAsFriendEvent.getFriend());
            if(r) EventQueue.queue(new FriendAddedEvent(addUserAsFriendEvent.getFriend()));
            return;
        }

        if (event instanceof QueueClosedEvent || event instanceof SaveEvent) {
            if(accountRemoved) return;
            System.out.println("QUEUE CLOSED");
            try { localManager.save(pass); }
            catch (StoreException e) {
                try { localManager.save(pass); }
                catch (StoreException e2) {
                    throw new RuntimeException(e2.getMessage());
                }
            }
            event.callback(null);
            System.out.println("QUEUE CLOSED");
            return;
        }

        if(event instanceof UserEvent) {
            event.callback(localManager.getUser());
            return;
        }

        if(event instanceof FriendsListEvent) {
            event.callback(localManager.getUserManager().getFriends());
            return;
        }

        if(event instanceof RemoveFriendEvent) {
            RemoveFriendEvent removeFriendEvent = (RemoveFriendEvent) event;
            boolean removed = localManager.getUserManager().removeFriend(
                    removeFriendEvent.getFriend());
            if(removed) {
                EventQueue.queue(new FriendRemovedEvent(removeFriendEvent.getFriend()));
            }
            return;
        }

        if(event instanceof EnsureGameEvent) {
            EnsureGameEvent ensureGameEvent = (EnsureGameEvent) event;
            if(!localManager.getGameManager().hasGame(ensureGameEvent.getGame())) {
                EventQueue.queue(new RequestGameEvent(
                        ensureGameEvent.getGame(),
                        ensureGameEvent.getRequestor(),
                        ensureGameEvent.getOwner()));
            }
            return;
        }

        if(event instanceof RequestGameEvent) {
            RequestGameEvent e = (RequestGameEvent) event;
            if(!e.isLocal()) new GameSender(e, localManager.getGameManager());
            return;
        }

        if(event instanceof GameRequestedEvent) {
            GameRequestedEvent e = (GameRequestedEvent) event;
            if(!e.isLocal()) new GameReceiver(e, localManager.getGameManager());
            return;
        }

        if(event instanceof GameAddedEvent) {
            GameAddedEvent gameAddedEvent = (GameAddedEvent) event;
            localManager.getGameManager().addGame(
                    gameAddedEvent.getName()
            );
            return;
        }

        if(event instanceof GameListEvent) {
            event.callback(localManager.getGameManager().getGames());
            return;
        }

        if(event instanceof TestGameEvent) {
            TestGameEvent testGameEvent = (TestGameEvent) event;
            testGameEvent.callback(localManager.getGameManager().testGame(
                    testGameEvent.getGame()
            ));
            return;
        }

        if(event instanceof AddGameEvent) {
            AddGameEvent addGameEvent = (AddGameEvent) event;
            localManager.getGameManager().addGame(addGameEvent.getGame());
            return;
        }

        if(event instanceof ShowUserStatEvent) {
            final ShowUserStatEvent showUserStatEvent = (ShowUserStatEvent) event;
            SwingUtilities.invokeLater(new Runnable() { public void run() {
                new UserStatsFrame(showUserStatEvent.getAbout());
            }});
            return;
        }

        if(event instanceof UserStatRequestEvent) {
            UserStatRequestEvent usre = (UserStatRequestEvent) event;
            if(usre.isLocal()) return;
            localManager.getGameManager().sendStats(usre);
            return;
        }

        if(event instanceof LocalUserStatEvent) {
            event.callback(localManager.getGameManager().getNames());
            return;
        }

        if(event instanceof ShowGameStatEvent) {
            ShowGameStatEvent showGameStatEvent = (ShowGameStatEvent) event;
            GameStat gameStat = localManager.getGameManager().getGameStat(
                    showGameStatEvent.getName());
            if (gameStat != null) gameStat.showStats();
            return;
        }

        if(event instanceof RemoveGameEvent) {
            RemoveGameEvent removeGameEvent = (RemoveGameEvent) event;
            localManager.getGameManager().removeGame(removeGameEvent.getName());
            return;
        }

        if(event instanceof GameFinishedEvent) {
            GameFinishedEvent gameFinishedEvent = (GameFinishedEvent) event;
            GameStat gameStat = localManager.getGameManager().getGameStat(gameFinishedEvent.getId().getG().getClassName());
            if(gameFinishedEvent.getResult() == -1) gameStat.incrementGamesLost();
            if(gameFinishedEvent.getResult() == 1) gameStat.incrementGamesWon();
            gameStat.increaseHoursPlayed(gameFinishedEvent.getDuration() / 3600000.0);
            gameStat.updateLastPlayed();
            gameStat.increaseTotalScore(gameFinishedEvent.getScore());
            gameStat.testHighScore(gameFinishedEvent.getScore());
            return;
        }

        if(event instanceof GameInterruptedEvent) {
            GameInterruptedEvent gameInterruptedEvent = (GameInterruptedEvent) event;
            GameStat gameStat = localManager.getGameManager().getGameStat(gameInterruptedEvent.getId().getG().getClassName());
            if(gameInterruptedEvent.getQuitter().getName().equals(localManager.getUser().getName())) {
                gameStat.incrementGamesLeft();
            }
            gameStat.increaseHoursPlayed(gameInterruptedEvent.getDuration() / 3600000.0);
            gameStat.updateLastPlayed();
            return;
        }

        if(event instanceof UpdateUserEvent) {
            User user = ((UpdateUserEvent) event).getUser();
            if(localManager.getUserManager().removeFriend(user)) {
                localManager.getUserManager().addFriend(user);
            }
            return;
        }

        if(event instanceof DescriptionEvent) {
            DescriptionEvent de = (DescriptionEvent) event;
            localManager.getUser().setDescription(de.getDesc());
            return;
        }

    }
}
