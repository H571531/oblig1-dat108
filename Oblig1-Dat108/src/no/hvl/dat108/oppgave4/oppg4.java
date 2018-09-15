package no.hvl.dat108.oppgave4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import no.hvl.dat108.oppgave3.Ansatt;
import no.hvl.dat108.oppgave3.Oppg3;

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
		
		//c) Regn ut gjennomsnittslønn til kvinnene
			int sumLonnKvinner=ansatte.stream()
							.filter(a->a.getKjonn().equals("Kvinne"))
							.map(a ->a.getAarlonn() )
							.reduce((a,acc)->acc+a).orElse(null);//Om det ikke er noen kvinner vil den returnere null og gjennomsnittet vil også bli null
			
			//Gjennomsnitt=sum/antall	
			double gjSnittKvinner=sumLonnKvinner/ansatte.stream().filter(a->a.getKjonn().equals("Kvinne")).count(); //Bruker en stream her for å finne antall kvinner
			
			System.out.println("Gjennomsnittslønnen for kvinner er: "+gjSnittKvinner);
			System.out.println();
			
			
			
		//d)Gi alle sjefer stilling="sjef" lønnsøkning på 7%
			System.out.println("Sjefer med lønn før endring: ");
			
				ansatte.stream()//stream for å printe sjefer
					.filter(a->a.getStilling().equals("sjef"))
					.forEach(System.out::println);
			System.out.println();
			
			
			ansatte.stream()
					.filter(a->a.getStilling().equals("sjef"))
					.forEach(a->a.endreLonn(Oppg3.endreprosent(7)));
			
			System.out.println("Etter endring: ");
			ansatte.stream() //stream for å printe sjefer
					.filter(a->a.getStilling().equals("sjef"))
					.forEach(System.out::println);
			System.out.println();
			
			
		//e)Finn ut om det er noen med lønn over 80 000
			boolean lonnover=ansatte.stream()
							.anyMatch(a->a.getAarlonn()>800000);
			System.out.println("Det er noen med lønn over 800 000 er "+lonnover);
			System.out.println();
			
		//f)Skriv ut alle de ansatte med sysoutprintln uten løkke
			System.out.println("Alle ansatte: ");
			ansatte.stream()
					.forEach(System.out::println);
			System.out.println();
			
		//g)Skriv ut de med lavest lønn
			System.out.println("De med lavest lønn: ");
			ansatte.stream()
				.filter(a->a.getAarlonn()==ansatte.stream() //Bruker en stream her for å finne den laveste lønnen slik at man kan teste den opp for å finne flere med samme lønn
											.mapToInt(b->b.getAarlonn())
											.min()
											.getAsInt())
				.forEach(System.out::println);
			System.out.println();
			
		//h)Finn summen av alle heltall mellom 1,1000 som er delig med 3 eller 5
			int sum=IntStream.range(1, 10)
						.filter(x->x%3==0||x%5==0)
						.sum();
			System.out.println("Summen av alle heltall mellom 1-1000 som er delelig med 3 eller 5 er: "+sum);	
	}
}