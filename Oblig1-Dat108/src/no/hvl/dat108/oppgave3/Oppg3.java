package no.hvl.dat108.oppgave3;

import java.util.function.Function;

public class Oppg3 {
	
	
	public static Function<Integer,Integer> endreprosent(int p) {
		return a->a+(a*p)/100;
	}		


	public static void main(String[] args) {
		Ansatt a1 = new Ansatt("Ole","Olesen","Mann","sjef",600000);
		Ansatt a2 = new Ansatt("Oline","Olesen","Kvinne","salg",300000);
		Ansatt a3 = new Ansatt("Olaf","Olesen","Mann","support",250000);
		
		int kronetillegg = 100;
		int fastprosent = 10;
		int fastLavLonn=10;
		
		Function<Integer, Integer> uttrykk =f -> f+fastLavLonn;
		
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		
		a1.endreLonn(a->a+kronetillegg);
		a2.endreLonn(endreprosent(fastprosent));
		a3.endreLonn(uttrykk);
		
		
		
		
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		
	}

}
