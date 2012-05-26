package hawkge.main.lobby.actions;

import hawkge.event.Event;
import hawkge.event.EventListener;
import hawkge.event.EventQueue;
import hawkge.main.inlog.InlogModel;
import hawkge.main.lobby.list.LogoutEvent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;

/**
 * @create on May 14, 2012
 * @author jorisvi
 */
public class LogoutAction extends AbstractAction implements EventListener {
    
    private final JFrame loginFrame;
    private final JFrame lobbyFrame;
    private final InlogModel inlogModel;

    public LogoutAction(JFrame loginFrame, JFrame lobbyFrame, InlogModel inlogModel) {
        super("Logout");
        this.loginFrame = loginFrame;
        this.lobbyFrame = lobbyFrame;
        this.inlogModel = inlogModel;
        EventQueue.getQueue().addEventListener(this);
    }
    
    

    public void actionPerformed(ActionEvent e) {
        inlogModel.logout();
        lobbyFrame.dispose();
        loginFrame.setVisible(true);
    }

    public void onEvent(Event event) {
        if(event instanceof LogoutEvent) {
            actionPerformed(null);
        }
    }

}