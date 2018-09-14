package no.hvl.dat108.oppgave4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import no.hvl.dat108.oppgave3.Ansatt;

public class oppg4 {

	public static void main(String[] args) {
		
		List <Ansatt> ansatte =Arrays.asList(
				new Ansatt("Ole","Olesen","Mann","salg",300000),
				new Ansatt("Emma","Olesen","Kvinne","salg",300000),
				new Ansatt("Siri","Olesen","Kvinne","support",250000),
				new Ansatt("Olaf","Olesen","Mann","sjef",600000),
				new Ansatt("Jonas","Olesen","Mann","sekretaer",250000),
				new Ansatt("Geir","Olesen","Mann","support",250000),
				new Ansatt("Rolf","Olesen","Mann","produktansvarlig",340000),
				new Ansatt("Hege","Olesen","Kvinne","sjef",600000),
				new Ansatt("Julia","Olesen","Kvinne","Grand Moff",600203),
				new Ansatt("Marte","Olesen","Kvinne","produktansvarlig",340000));
		
		//a) Lag en ny liste som kun inneholder etternavnet til ansatte
			List <String> Etternavn= ansatte.stream()
						.map(a->a.getEtternavn())
						.collect(Collectors.toList());
						
		//b) Finn ut antall kvinner
			long kvinner=ansatte.stream()
						.count();
		
		//c) Regn ut gjennomsnittslønn til kvinnene
			double gjlonnKvinner=ansatte.stream()
							.filter(a->a.getKjonn().equals("Kvinne"))
							.map(a->a.getAarlonn())
							.reduce((a, acc)->acc=(acc+a)/ansatte.stream()
																.filter(a->a.getKjonn().equals("Kvinne"))
																.count())
							);
		
		//d)Gi alle sjefer stilling="sjef" lønnsøkning på 7%
			ansatte.stream()
					.filter(a->a.getStilling().equals("sjef"))
					.forEach(a->a.endreLonn());
		
		//e)Finn ut om det er noen med lønn over 80 000
			boolean lonnover=ansatte.stream()
							.anyMatch(a->a.getAarlonn()>800000);
			
		//f)Skriv ut alle de ansatte med sysoutprintln uten løkke
			ansatte.stream()
					.forEach(System.out::println);
		//g)Skriv ut de med lavest lønn
			ansatte.stream()
				.filter(a->a.getAarlonn()==ansatte.stream()
											.mapToInt( b->b.getAarlonn() )
											.min()
											.getAsInt())
				.forEach(System.out::println));
		//h)Finn summen av alle heltall mellom 1,1000 som er delig med 3 eller 5
			int sum=IntStream.range(1, 10)
						.filter(x->x%4==0)
						.
	System.out.println();
	
		
	}

}
