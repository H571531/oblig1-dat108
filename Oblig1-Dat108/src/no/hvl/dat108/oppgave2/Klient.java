package no.hvl.dat108.oppgave2;

/**
 * 
 * Klient-program for consumers og producers. Starter to threads med producers, og to threads med consumers.
 * 
 * @author Adrian Mortensen, Kristoffer Nome, Anders Simonsen
 *
 */

public class Klient {

	public static void main(String[] args) {
		
		Buffer buffer = new Buffer();
		
		Thread prod = new Producer(buffer);
		Thread prod2 = new Producer(buffer);
		Thread cons = new Consumer(buffer);
		Thread cons2 = new Consumer(buffer);
		
		prod.start();
		prod2.start();
		cons.start();
		cons2.start();

	}

}
