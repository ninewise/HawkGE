/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.event;

/**
 * Thrown on the queue by the queue itself, just before it closes.
 * On this event, all listeners should destruct themself.
 * @author felix
 */
public class QueueClosedEvent extends Event<Void> {

    public QueueClosedEvent() { super(); }

    public QueueClosedEvent(final Terminator terminator) {
        super(new Callable<Void>() {
            public void call(Void param) {
                terminator.decrement();
            }
        });
    }

}
