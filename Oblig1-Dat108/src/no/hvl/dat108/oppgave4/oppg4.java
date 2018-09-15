package no.hvl.dat108.oppgave4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import no.hvl.dat108.oppgave3.Ansatt;
import no.hvl.dat108.oppgave3.Oppg3;

/**
 * 
 * DAT108 Oblig1, Oppgave 4
 * Oppretter en liste med 10 ansatte.
 * Skriv en klasse med en main()-metode som inneholder løsningen på
 * delspørsmålene. Løsningene skal være funksjonelle,
 * dvs. bruk stream(), map(), filter(), reduce() osv.
 * Alle løsningene/svarene skal lagres i variabler og skrives ut på skjermen
 * @author Adrian Mortensen, Kristoffer Nome, Anders Simonsen
 *
 */
public class oppg4 {

	public static void main(String[] args) {
		
		List <Ansatt> ansatte =Arrays.asList( //Lager en liste av ansatte
				new Ansatt("Ole","Olesen","Mann","salg",300000),
				new Ansatt("Emma","Olesen","Kvinne","salg",300000),
				new Ansatt("Siri","Olesen","Kvinne","support",250000),
				new Ansatt("Olaf","Olesen","Mann","sjef",600000),
				new Ansatt("Jonas","Olesen","Mann","sekretaer",250000),
				new Ansatt("Geir","Olesen","Mann","support",250000),
				new Ansatt("Rolf","Olesen","Mann","produktansvarlig",340000),
				new Ansatt("Hege","Olesen","Kvinne","sjef",600000),
				new Ansatt("Julia","Tarkin","Kvinne","Grand Moff",1000000),
				new Ansatt("Marte","Olesen","Kvinne","produktansvarlig",340000));
		
		//a) Lag en ny liste som kun inneholder etternavnet til ansatte
			List <String> Etternavn= ansatte.stream()
						.map(a->a.getEtternavn())
						.collect(Collectors.toList());
			
			Etternavn.stream() //Printe ut alle i en liste
					.forEach(System.out::println);
			System.out.println();	
			
		//b) Finn ut antall kvinner
			long kvinner=ansatte.stream()
						.count();
			
			System.out.println("Det er "+kvinner+ " kvinner");
			System.out.println();
		
		//c) Regn ut gjennomsnittsl�nn til kvinnene
			double gjSnittKLonn = ansatte.stream()
					.filter(a -> a.getKjonn().equals("Kvinne"))
					.mapToDouble(a -> (double)a.getAarlonn())
					.average()
					.getAsDouble();
			
			System.out.println("Gjennomsnittsl�nnen for kvinner er: " + gjSnittKLonn);
			System.out.println();
			
			
			
		//d)Gi alle sjefer med stilling="sjef" l�nns�kning p� 7%
			System.out.println("Sjefer med l�nn f�r endring: ");
			
				ansatte.stream()//stream for � printe sjefer
					.filter(a->a.getStilling().equals("sjef"))
					.forEach(System.out::println);
			System.out.println();
			
			
			ansatte.stream()
					.filter(a->a.getStilling().equals("sjef"))
					.forEach(a->a.endreLonn(Oppg3.endreprosent(7)));
			
			System.out.println("Etter endring: ");
			ansatte.stream() //stream for � printe sjefer
					.filter(a->a.getStilling().equals("sjef"))
					.forEach(System.out::println);
			System.out.println();
			
			
		//e)Finn ut om det er noen med l�nn over 80 000
			boolean lonnover=ansatte.stream()
							.anyMatch(a->a.getAarlonn()>800000);
			System.out.println("Det er noen med l�nn over 800 000: "+lonnover);
			System.out.println();
			
		//f)Skriv ut alle de ansatte med sysoutprintln uten l�kke
			System.out.println("Alle ansatte: ");
			ansatte.stream()
					.forEach(System.out::println);
			System.out.println();
			
		//g)Skriv ut de med lavest l�nn
			System.out.println("De med lavest l�nn: ");
			ansatte.stream()
				.filter(a->a.getAarlonn()==ansatte.stream() //Bruker en stream her for � finne den laveste l�nnen slik at man kan teste den opp for � finne flere med samme l�nn
											.mapToInt(b->b.getAarlonn())
											.min()
											.getAsInt())
				.forEach(System.out::println);
			System.out.println();
			
		//h)Finn summen av alle heltall mellom 1,1000 som er delig med 3 eller 5
			int sum=IntStream.range(1, 1000)
						.filter(x -> x % 3 == 0 || x % 5 == 0)
						.sum();
			System.out.println("Summen av alle heltall mellom 1-1000 som er delelig med 3 eller 5 er: "+sum);	
			
		//Oppgavetekst nevner eksplisitt bruk av reduce. Alternativ måte å regne sum
		//Med reduce
			int sumRed = IntStream.range(1, 1000)
					.filter(i -> i % 3 == 0 || i % 5 == 0)
					.reduce((deleligSum,i) -> deleligSum+i).getAsInt();
			System.out.println("Med reduce: " + sumRed);
	}
}