
package hawkge.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// TODO no longer needs closing boolean. Back to set?
// waiting, because we might need them for resetting.
// Felix


public class EventQueue {

    public static final int POOLSIZE = 10;

    // Singleton handling.
    private static EventQueue queue = new EventQueue();
    public static EventQueue getQueue() {
        return queue;
    }
    public static void queue(Event e) {
        queue.queueEvent(e);
    }

    private final Map<EventListener, Boolean> listeners;
    private final ExecutorService executor;
    private boolean closed;

    /**
     * Creates a new eventqueue without listeners.
     * Must be closed with the close() method!
     */
    private EventQueue() {
        this.listeners = new HashMap<EventListener, Boolean>();
        this.executor = Executors.newFixedThreadPool(POOLSIZE);
        this.closed = false;
    }

    /**
     * Safely closes this eventqueue.
     */
    public void close() {
        if(!closed) {
            queueEvent(new QueueClosedEvent());
            synchronized(this) {
                executor.shutdown();
                closed = true;
            }
            try {
                executor.awaitTermination(1L, TimeUnit.MINUTES);
            } catch (InterruptedException ex) {
                executor.shutdownNow();
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    /**
     * Ensure the EventListener is listening to this queue.
     * @param eventListener The EventListener that wishes to subscribe.
     */
    public synchronized void addEventListener(EventListener eventListener) {
        if(!closed) this.listeners.put(eventListener, false);
    }

    /**
     * Ensure the EventListener is not listening to this queue.
     * @param eventListener The EventListener the unsubscribes.
     */
    public synchronized void removeEventListener(EventListener eventListener) {
        if(!closed) {
            this.listeners.remove(eventListener);
        }
    }

    /**
     * Returns whether the given EventListener is listening to this EventQueue.
     * @param eventListener The listener to be verified.
     * @return Whether it's listening or not.
     */
    public synchronized boolean isEventListener(EventListener eventListener) {
        return listeners.containsKey(eventListener);
    }

    /**
     * Line a new Event on this queue.
     * @param event The event to line.
     */
    public synchronized void queueEvent(final Event event) {
        if(event instanceof ResetQueueEvent) {
            queue = new EventQueue();
        } else if(!closed) {
            Set<EventListener> set = listeners.keySet();
            for(final EventListener listener : set) {
                executor.execute(new Runnable() {
                    public void run() {
                        listener.onEvent(event);
                    }
                });
            }
        }
    }

}
