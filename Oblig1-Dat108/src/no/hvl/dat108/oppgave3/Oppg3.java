package no.hvl.dat108.oppgave3;

import java.util.function.Function;

/**
 * DAT108 Oblig1, Oppgave 3
 * Opprett tre ansatte. Lønnen deres skal endres på tre forskjellige måter:
 * Et fast kronetillegg
 * Et fast prosenttillegg
 * Et fast kronetillegg hvis du har lav lønn
 * 
 * Lønnen skal endres med en funksjonsparameter til ansattObjekt.endreLonn(Function<Integer, Integer> funksjon)
 * Minst ett lambda-uttrykk skal lagres i en variabel før bruk, 
 * og minst ett lambda-uttrykk skal være returverdi fra en public static-metode
 * 
 * 
 * @author Adrian Mortensen, Kristoffer Nome, Anders Simonsen
 *
 */

public class Oppg3 {
	
	/**
	 * Endrer en ansatts lønn når gitt som parameter til ansattObjekt.endreLonn.
	 * 
	 * @param p Prosent lønnen skal endres med
	 * @return Lambda-uttrykk for endring av lønn
	 */
	public static Function<Integer,Integer> endreprosent(int p) {
		return a->a+(a*p)/100;
	}		
	
	
	/**
	 * Endrer en ansatts lønn når gitt som parameter til ansattObjekt.endreLonn.
	 * Hvis den ansattes lønn er under 300 000, økes lønnen med 50 000,
	 * ellers gjøres ingen endringer.
	 * 
	 * @return Lambda-uttrykk
	 */
	public static Function<Integer, Integer> fastKroneHvisLav(){
		return lonn -> {
			if(lonn < 300000) {
				return lonn + 50000;
			} else {
				return lonn;
			}
		};
	}


	public static void main(String[] args) {
		
		
		Ansatt a1 = new Ansatt("Ole", "Olesen", "Mann", "Sjef", 600000);
		Ansatt a2 = new Ansatt("Oline", "Olesen", "Kvinne", "Salg", 300000);
		Ansatt a3 = new Ansatt("Olaf", "Olesen", "Mann", "Support", 250000);
		Ansatt a4 = new Ansatt("Pia", "Persen", "Kvinne", "Medarbeider", 400000);
		Ansatt a5 = new Ansatt("Kari", "Karlsen", "Kvinne", "Sekretær", 250000);
		
		int kronetillegg = 1000;
		int fastprosent = 10;
		
		//Oppgave: minst ett lambda-uttrykk skal lagres i en variabel før bruk
		Function<Integer, Integer> fastKroneTilleggVariabel = f -> f+kronetillegg;
		
		System.out.println("Ansatte før endring: ");
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		System.out.println(a4);
		System.out.println(a5);
		
		System.out.println("\n////////////////////////\n");
		
		//Oppgave: Øk den ansattes lønn med et fast kronetillegg
		a1.endreLonn(a->a+kronetillegg);
		
		//Oppgave: Øk den ansattes lønn med et fast prosenttillegg.
		//Oppgave: Minst ett lambda-uttrykk skal være returverdi fra en public static-metode
		a2.endreLonn(endreprosent(fastprosent));
		
		//Oppgave: Minst ett lambda-uttrykk skal lagres i en variabel før bruk
		a3.endreLonn(fastKroneTilleggVariabel);
		
		//Oppgave:Øk den ansattes lønn med et fast kronetillegg hvis du har lav lønn
		//Forsøker å endre lønn på en ansatt med lønn < 300 000, og en med lønn > 300 000
		a4.endreLonn(fastKroneHvisLav());
		a5.endreLonn(fastKroneHvisLav());
		
		
		
		System.out.println("Ansatte etter endring: ");
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		System.out.println(a4);
		System.out.println(a5);
		
	}

}
