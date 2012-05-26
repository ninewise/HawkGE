/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.event;

/**
 *
 * @author felix
 */
public class Terminator {

    private int count;

    public Terminator(int count) {
        this.count = count;
    }

    public synchronized void decrement() {
        if(--count == 0) System.exit(0);
    }

    public synchronized void decrementSafe() {
        count--;
    }

    public synchronized void increment() {
        count++;
    }

}
