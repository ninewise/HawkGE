package hawkge;

import javax.swing.event.ChangeListener;

/**
 *
 * @create on May 2, 2012
 * @author jorisvi
 */
public interface ModelInterface {
    public void addChangeListener(ChangeListener l);
    public void removeChangeListener(ChangeListener l);
}
