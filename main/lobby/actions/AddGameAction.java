package hawkge.main.lobby.actions;

import hawkge.event.Callable;
import hawkge.event.EventQueue;
import hawkge.storage.gameloading.GameManager;
import hawkge.storage.gameloading.events.AddGameEvent;
import hawkge.storage.gameloading.events.TestGameEvent;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @create on May 14, 2012
 * @author jorisvi
 */
public class AddGameAction extends AbstractAction {
    
    public AddGameAction() {
        super("Add game...");
    }

    /**
     * Opens a filechooser to select a jar
     * @param e an ActionEvent object
     */
    public void actionPerformed(ActionEvent e) {
        openFile();
    }

    /**
     * When a jar file is selected send an event on the queue to add the game.
     */
    private void openFile() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("jar Files", new String[]{"jar"}));
        if (fileChooser.showOpenDialog(null) != 0) {
            return;
        }
        final File game = fileChooser.getSelectedFile();
        EventQueue.queue(new TestGameEvent(game, new Callable<String>() {
            public void call(String param) {
                parseMessage(fileChooser, param, game);
            }
        }));
    }

    /**
     * Parses the output of the gameTest.
     */
    private void parseMessage(JFileChooser fileChooser, String msg, File game) {
        if(msg == null) { // It's an actual game!
            EventQueue.queue(new AddGameEvent(game));
        } else {
            JOptionPane.showMessageDialog(fileChooser, msg, "Ongeldig bestand", JOptionPane.ERROR_MESSAGE);
            openFile();
        }
    }

}