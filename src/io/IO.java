/** Main.java
 * 23/mag/2012 12:55:09
 * Last edit: 23/mag/2012 12:55:09
 * 
 * 
 */

package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/** Questa classe implementa metodi statici utili per la creazione del mondo */
public class IO {
	
	/** Questo metodo legge un file di parsing, crea gli oggetti e ritorna la lista di locazioni.
	 * Se trova una sintassi incorretta nel parser, stampa la linea incriminata ed esce.
	 * 
	 * @param path
	 */
	public static ArrayList<object.location.Location> startFromFile(String path) throws FileNotFoundException {
		
		CreateObjects co = new CreateObjects(path);
		
		try {
			co.createLocation();
			co.createAperture();
			co.createActor();
			co.createObjects();			
			co.createInventory();
			co.createObjectLocation();
			co.createOwners();
			
			if (co.getLocation().size() == 0) {
				// Controllo che esista almeno una locazione.
				throw new IllegalParserValueException();
			}
		}
		
		catch (IllegalParserValueException e) {
			System.out.println(e);
			// A questo punto, potrei anche ignorare l'eccezione e riprendere, ma che senso avrebbe? Si avrebbero risultati inaspettati, preferisco uscire e permettere all'utente di correggere.
			System.exit(-2);
		}
		
		return co.getLocation();
	}
	
	/** Questo metodo legge dal file .ser di default e ritorna l'arrayList di locazioni. */
	public static ArrayList<object.location.Location> loadFromFile() throws IOException {
		LoadState l = new LoadState();
		return l.load();
	}
	
	/** Questo metodo invece legge da un file .ser personalizzato */
	public static ArrayList<object.location.Location> loadFromFile(String path) throws IOException{
		LoadState l = new LoadState(path);
		return l.load();
	}
	
	/** Questo metodo prende l'arrayList di locazioni e lo salva nel file .ser di default
	 * Naturalmente ogni salvataggio successivo sovrascrive i precedenti. */
	public static void saveToFile(ArrayList<object.location.Location> locs) throws IOException{
		SaveState s = new SaveState(locs);
		s.save();
	}
	
	/** Questo metodo invece salva in un percorso personalizzato. */
	public static void saveToFile(ArrayList<object.location.Location> locs, String path) throws IOException {
		SaveState s = new SaveState(locs, path);
		s.save();
	}
}
