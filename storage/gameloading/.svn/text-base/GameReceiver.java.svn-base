/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.gameloading;

import hawkge.storage.gameloading.GameManager;
import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.storage.gameloading.events.GameAddedEvent;
import hawkge.storage.gameloading.events.GamePartEvent;
import hawkge.storage.gameloading.events.GameRequestedEvent;
import hawkge.storage.gameloading.events.WaitingForGameEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author felix
 */
public class GameReceiver implements EventListener {

    private String name;
    private File game;
    private int parts;
    private byte[][] jar;

    /**
     * Creates a new Incomplete game, as a buffer.
     * @param name The name of the new game.
     * @param parts In how much parts this game will be sent.
     * @throws IOException If the new file cannot be created.
     */
    public GameReceiver(GameRequestedEvent event, GameManager gm) {
        this.name = event.getGameName();
        this.parts = event.getParts();
        this.jar = new byte[parts][];

        game = gm.getGameLocation(name);
        try {
            if(!game.createNewFile()) throw new RuntimeException("Game exists yet.");
        } catch(IOException e) { throw new RuntimeException(e); }

        EventQueue.getQueue().addEventListener(this);
        EventQueue.queue(new WaitingForGameEvent(event.getOwner(), name));
    }

    public void complete() {
        OutputStream ostream = null;
        try {
            ostream = new FileOutputStream(game);
            for(int i = 0; i < jar.length; i++) {
                ostream.write(jar[i]);
            }
            ostream.close();
            EventQueue.queue(new GameAddedEvent(this.name));
            EventQueue.getQueue().removeEventListener(this);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onEvent(Event event) {
        if(event instanceof GamePartEvent) {
            GamePartEvent e = (GamePartEvent) event;
            if(e.isLocal() || !e.getGame().equals(this.name)) return;
            jar[e.getIndex()] = e.getPart();
            this.parts--;
            if(this.parts == 0) this.complete();
        }
    }

}