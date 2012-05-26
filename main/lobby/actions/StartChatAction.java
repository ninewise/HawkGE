package hawkge.main.lobby.actions;

import hawkge.chat.gui.ChatFrame;
import hawkge.chat.model.ChatModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @create on May 7, 2012
 *
 * @author jorisvi
 */
public class StartChatAction extends AbstractAction {

    public StartChatAction() {
        super("Start chat...");
    }

    /**
     * Start a new chatsession.
     * @param e an ActionEvent object
     */
    public void actionPerformed(ActionEvent e) {
        ChatModel chatModel = new ChatModel();
        ChatFrame frame = new ChatFrame(chatModel);
    }
}