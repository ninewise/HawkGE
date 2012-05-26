/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.gameloading;

import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.storage.User;
import hawkge.storage.gameloading.events.GamePartEvent;
import hawkge.storage.gameloading.events.GameRequestedEvent;
import hawkge.storage.gameloading.events.RequestGameEvent;
import hawkge.storage.gameloading.events.WaitingForGameEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author felix
 */
public class GameSender implements EventListener {

    private static final int PART_SIZE = 512;

    private final File game;
    private final User destination;
    private final String name;

    public GameSender(RequestGameEvent event, GameManager gm) {
        this.destination = event.getRequestor();
        this.name = event.getName();

        game = gm.getGameLocation(name);
        int parts = (int) ((game.length() + PART_SIZE - 1) / PART_SIZE);

        EventQueue.getQueue().addEventListener(this);
        EventQueue.queue(new GameRequestedEvent(name, destination, parts, event.getOwner()));
    }

    public void onEvent(Event event) {
        if(event instanceof WaitingForGameEvent) {
            WaitingForGameEvent e = (WaitingForGameEvent) event;
            // Return if other game or we're waiting.
            if(e.isLocal() || !e.getName().equals(name)) return;
            // That's what we were waiting for. No more listening.
            EventQueue.getQueue().removeEventListener(this);
            InputStream istream = null;
            try {
                istream = new FileInputStream(game);
                byte[] b = new byte[PART_SIZE];
                int i = 0;
                long bytesLeft = game.length();
                while(bytesLeft >= PART_SIZE && istream.read(b) == PART_SIZE) {
                    EventQueue.queue(new GamePartEvent(destination, name, i, b));
                    b = new byte[PART_SIZE];
                    i++;
                    bytesLeft -= PART_SIZE;
                }
                if(bytesLeft > 0) {
                    b = new byte[(int) bytesLeft];
                    istream.read(b);
                    EventQueue.queue(new GamePartEvent(destination, name, i, b));
                }
            } catch(IOException ex) {
                // TODO warn the other user the file is corrupt.
                throw new RuntimeException(ex);
            }
        }
    }

}
