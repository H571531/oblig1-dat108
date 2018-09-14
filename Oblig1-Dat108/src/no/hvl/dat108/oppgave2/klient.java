package no.hvl.dat108.oppgave2;

/**
 * @author Adrian Mortensen
 *
 */
public class klient {
	/**
	 * @param args Ikke brukt
	 * 
	 */
	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		Thread producer = new Producer(buffer);
		Thread consumer =new Consumer(buffer);
		
		producer.start();
		consumer.start();

	}
}