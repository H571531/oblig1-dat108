package no.hvl.dat108.oppgave2;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * A shared buffer for consumers and producers. The item type is int. The buffer
 * is implemented by using a double ended queue.
 *
 * @author Atle Geitung
 * @version 17.05.2018 C++ version
 * @version 30.08.2018 Java version
 */
public class Buffer {

    private final static int SIZE = 10;
    private LinkedList<Integer> buffer = new LinkedList<Integer>();
    
   
   /*
    * For å støtte flere producers og consumers opprettes tre semaforer. 
    * En som fungerer som en mutex for ekslusiv tilgang til selve bufferen,
    * en som skal holde rede på hvor mange plasser i listen som er brukt,
    * en som skal holde rede på hvor mange plasser i listen som er tom 
    * 
    * 
    */
    Semaphore mutex = new Semaphore(1);
    Semaphore antBruktePlasser = new Semaphore(0);
    Semaphore antTommePlasser = new Semaphore(SIZE);
    
    
    

    /**
     * Add a new item to the buffer. If the buffer is full, wait.
     *
     * @param item the new item
     */
    public void add(Integer item) {
    	
    	/*
    	 * Fjerner while(true)-loop som var gitt i ferdiglaget kode - både Producer og Consumer
    	 * går i egne while(true)-loops i sine egne run()-metoder.
    	 * 
    	 * I stedet for at producere og consumere forsøker igjen og igjen å sjekke om det er ledige plasser eller noe å hente ut,
    	 * brukes semaforer til at de venter til det er ledige plasser eller det finnes noe å hente ut.
    	 */
//        while (true) {
//            // TODO
//            buffer.add(item);
//            // TODO
//            return;
//        }
    	
    		try {
    			/* 
    			 * Senker antall tomme plasser med .acquire() FØR det faktisk legges noe til i bufferen. Når dette holdes rede på i en semafor, unngås situasjoner hvor
    			 * en producer sjekker buffer.size() og finner at det er ledig plass, men tråden settes på vent før den kommer inn i en kritisk seksjon hvor det legges til i bufferen
    			 * en annen producer sjekker buffer.size() og finner at det er ledig plass, siden producer1 enda ikke har lagt til i bufferen
    			 * producer2 legger til i bufferen og går ut av kritisk seksjon
    			 * producer1 blir aktiv igjen og går inn i kritisk seksjon. Bufferen kan nå potensielt være full, men så langt producer1 vet, er det ledig plass og vil forsøke å legge til.
    			 * 
    			 * Hvis det ikke er noen ledige plasser, vil produceren vente til antTommePlasser økes i remove()
    			 */
    			antTommePlasser.acquire();
    			
    			/*
    			 * Henter mutex med .acquire(), som sikrer ekslusiv tilgang.
    			 * I situasjoner med flere producers kan det oppstå situasjoner hvor 
    			 * producer1 finner ut at det er en ledig plass,
    			 * og producer2 etterpå finner ut at det er en ledig plass. 
    			 * 
    			 * Uten både en semafor som holder rede på antall tomme plasser og en mutex som sikrer ekslusiv tilgang
    			 * kan det oppstå situasjoner hvor begge producere finner ut at det er ledig plass i bufferen,
    			 * producer1 finner en ledig plass
    			 * før producer1 får lagt til, finner producer2 den samme ledige plassen
    			 * begge forsøker å skrive til samme ledige plass
    			 */
				mutex.acquire();
				
				//Legger til i bufferen
				buffer.add(item);
				System.out.println("Legger til: " + item + " - antall i buffer: " + buffer.size());
				
				/* 
				 * Gir slipp på ekslusiv tilgang med mutex.release(), og øker antall brukte plasser med .release()
				 * Hvis det er en consumer som venter på noe å ta ut, vil denne nå få tilgang til semaforen antBruktePlasser.
				 */
				mutex.release();
				antBruktePlasser.release();
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
			}
    	
    }

    /**
     * Remove next available item from the buffer. If the buffer is empty, wait.
     *
     * @return next item
     */
    public Integer remove() {
//        while (true) {
//            // TODO
//            Integer back = buffer.removeFirst();
//            // TODO
//            return back;
//        }
    	
    	
    	Integer back = null;
    	try {
    		/* 
			 * Senker antall tomme plasser med .acquire() FØR det faktisk tas noe ut av bufferen. Når dette holdes rede på i en semafor, unngås situasjoner hvor
			 * det finnes ett item i bufferen
			 * en consumer sjekker buffer.size() og finner at det finnes noe å ta ut, men tråden settes på vent før den kommer inn i en kritisk seksjon hvor det faktisk tas ut fra bufferen
			 * en annen consumer sjekker buffer.size() og finner at den ikke er tom, siden consumer1 enda ikke har tatt ut fra bufferen
			 * consumer2 tar ut fra bufferen og går ut av kritisk seksjon
			 * consumer1 blir aktiv igjen og går inn i kritisk seksjon. Bufferen kan nå potensielt være tom, men så langt consumer1 vet, er ikke bufferen tom, og vil forsøke å ta ut.
			 * 
			 * Hvis det ikke finnes noe i bufferen, vil consumeren vente til antBruktePlasser økes i add().
			 */
    		antBruktePlasser.acquire();
    		
    		
    		/*
			 * Henter mutex med .acquire(), som sikrer ekslusiv tilgang.
			 * I situasjoner med flere consumers kan det oppstå situasjoner hvor 
			 * consumer1 finner ut at finnes noe å ta ut i bufferen
			 * og consumer2 etterpå finner at det finnes noe å ta ut. 
			 * 
			 * Uten både en semafor som holder rede på antall tomme plasser og en mutex som sikrer ekslusiv tilgang
			 * kan det oppstå situasjoner hvor begge consumere finner ut at bufferen ikke er tom
			 * consumer1 finner plass med item som skal tas ut
			 * før consumer1 får lagt til, finner consumer2 den samme opptatte plassen i bufferen
			 * begge forsøker å hente ut samme item
			 */
    		mutex.acquire();
    		
    		
    		//Tar ut fra bufferen
    		back = buffer.removeFirst();
    		System.out.println("Tar ut: " + back + " - antall i buffer: " + buffer.size());
    		
    		
    		
    		/* 
			 * Gir slipp på ekslusiv tilgang med mutex.release(), og øker antall brukte plasser med .release()
			 * Hvis det er en producer som venter på at en ledig plass skal åpnes, vil denne nå få tilgang til semaforen antTommePlasser.
			 */
    		mutex.release();
    		antTommePlasser.release();
    		
    	} catch (InterruptedException e) {
    		
    	} finally {
    		
    	}
    	
    	return back;
    }

}
