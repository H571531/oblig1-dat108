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
    
    //Lager en 'semaphore' med maks 1 tilkoblinger. 
    static Semaphore syncLock = new Semaphore(1);
    /**
     * Add a new item to the buffer. If the buffer is full, wait.
     *
     * @param item the new item
     */
    public void add(Integer item) {
    	boolean erIkkeFull=false;
        while (true) {
        	try {
        		
        			
				while(!erIkkeFull) {
					erIkkeFull=buffer.size()<SIZE;
					//Thread.sleep(100);
					}
        		
				syncLock.acquire();
				
				if(buffer.size()<SIZE) {
				buffer.add(item);
				System.out.println("Det er: "+buffer.size()+" Elementer i listen.");
				syncLock.release();
				}
        		
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				
				
			}
            
            return;
        	}
    }
    

    /**
     * Remove next available item from the buffer. If the buffer is empty, wait.
     *
     * @return next item
     */
    public Integer remove() {
    	boolean erTom=buffer.isEmpty();
    	Integer back=null;
      //  while (true) {
        	
            try {
				
				
				while(erTom) {
					erTom=buffer.isEmpty();
					//Thread.sleep(100);
				}
				
				syncLock.acquire();
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally{
				back = buffer.removeFirst();
				syncLock.release();
			}
            return back;
        //}
    }

}
