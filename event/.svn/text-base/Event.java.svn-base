
package hawkge.event;

import java.io.Serializable;

/**
 * An event. Can be thrown on the EventQueue.
 */
public class Event<T> implements Serializable {

    private Callable<T> callback;

    public Event() {
        callback = null;
    }

    public Event(Callable<T> callback) {
        this.callback = callback;
    }

    public void callback(T param) {
        if(callback != null) callback.call(param);
    }
    
}
