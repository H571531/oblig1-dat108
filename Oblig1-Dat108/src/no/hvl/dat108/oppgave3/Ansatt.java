package no.hvl.dat108.oppgave3;

import java.util.function.Function;
import java.util.function.Supplier;

interface endre { 
    int operation(int a, int b); 
} 

public class Ansatt {
	private String fornavn;
	private String etternavn;
	private String kjonn;
	private String stilling;
	private Integer aarlonn;
	
	
	public Ansatt(String fornavn, String etternavn, String kjonn, String stilling, int aarlonn) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.kjonn=kjonn;
		this.stilling = stilling;
		this.aarlonn = aarlonn;
	
	}
	
	public void endreLonn(Function <Integer, Integer> funksjon) {
		setAarlonn(funksjon.apply(aarlonn));	
	}
	
	

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	
	public String getKjonn() {
		return kjonn;
	}

	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public int getAarlonn() {
		return aarlonn;
	}

	private void setAarlonn(int aarlonn) {
		this.aarlonn = aarlonn;
	}

	@Override
	public String toString() {
		return "Ansatt [fornavn=" + fornavn + ", etternavn=" + etternavn + ", stilling=" + stilling + ", aarlonn="
				+ aarlonn + "]";
	}
}