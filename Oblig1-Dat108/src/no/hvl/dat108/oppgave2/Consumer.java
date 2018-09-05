package no.hvl.dat108.oppgave2;

/**
 * A consumer that consumes items and remove items from a shared buffer. The
 * item type is int.
 *
 * @author Atle Geitung
 * @version 15.05.2018 C++ version
 * @version 30.08.2018 Java version
 */
public class Consumer extends Thread {

    private Buffer buffer;

    /**
     * Constructs a new consumer.
     *
     * @param buffer The shared buffer
     */
    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            Integer item = buffer.remove();
            System.out.println("Consumed: " + item);
            try {
                sleep(50);
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

}
