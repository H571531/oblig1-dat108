package no.hvl.dat108.oppgave2;

import java.util.Random;

/**
 * A producer that produces items and add items to a shared buffer. The item
 * type is int.
 *
 * @author Atle Geitung
 * @version 15.05.2018 C++ version
 * @version 30.08.2018 Java version
 */
public class Producer extends Thread {

    private Buffer buffer;
    Random rand = new Random();

    /**
     * Constructs a new producer.
     *
     * @param buffer The shared buffer
     */
    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            Integer item = rand.nextInt(100);
            buffer.add(item);
            System.out.println("Produced: " + item);
            try {
                sleep(50);
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }
}
