/** LoadState.java
 * 29/mag/2012 18:52:59
 * Last edit: 29/mag/2012 18:52:59
 * 
 * 
 */

package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


/**
 * Questa è la classe per leggere dal file .ser l'arraylist di locazioni, e ritornarlo.
 */
public class LoadState {
	
	private ObjectInputStream input;
	private ArrayList<object.location.Location> locazioni;
	
	private String path;
	
	public LoadState(String path) {
		this.path = path;
	}
	
	public LoadState() {
		this.path = System.getProperty("user.home")+System.getProperty("file.separator")+"location.ser";
	}

	/** Metodo per aprire il file da cui leggere */
	public void openFile()	throws IOException {
		input = new ObjectInputStream(new FileInputStream(path));
	}
	
	
	/** Metodo per leggere dal file.
	 * A questo punto si presenta un problema, leggendo dal .ser, come assicuro al compilatore il genere di oggetto che sta leggendo?
	 */
	@SuppressWarnings("unchecked")
	public void readFile() throws IOException {
		try {
			/* Questo porta ad un casting non sicuro, come evitarlo? 
			 * Leggendo su internet, sembra sia impossibile. */
			Object read = input.readObject();
			
			if (read instanceof ArrayList<?> && ((ArrayList<?>) read).get(0) instanceof object.location.Location) {
				locazioni = (ArrayList<object.location.Location>) read;
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Impossibile creare l'oggetto.");
			e.printStackTrace();
		}
	}
	
	/** Metodo per chiudere il file */
	public void closeFile() throws IOException {
		if ( input != null )		
			input.close(); 	
	}
		
	/** Questo è il metodo riassuntivo per semplificare la vita all'utente, apre il file, lo legge, e lo chiude.
	 * Ritorna l'arrayList di locazioni.
	 * @return un arrayList di locazioni
	 */
	public ArrayList<object.location.Location> load() throws IOException {
		openFile();
		readFile();
		closeFile();
		return locazioni;
	}
}