/** SaveState.java
 * 29/mag/2012 18:25:26
 * Last edit: 29/mag/2012 18:25:26
 * 
 * 
 */

package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Questa è la classe per il salvataggio dello stato di una partita su file.
 * Per il salvataggio della partita su file, per il momento mi limito a salvare l'arrayList di locazioni.
 * Infatti, in teoria almeno, ogni oggetto deve avere una locazione, che sia nell'inventario di un giocatore, un giocatore stesso, o un oggetto fisso.
 * Per cui, salvando le locazioni, dovrei salvare il tutto.
 * L'unico problema in cui potrei incappare è la perdita di informazioni, come un'ArrayList contentente una lista di tutti gli oggetti, ecc. 
 */
public class SaveState {
	
	private ObjectOutputStream output;
	private ArrayList<object.location.Location> locazioni;
	
	private String path;
	
	/** Costruttore che prende in input l'array di locazioni. */
	public SaveState(ArrayList<object.location.Location> locazioni) {
		this.locazioni = locazioni;		
		this.path = System.getProperty("user.home")+System.getProperty("file.separator")+"location.ser";
	}
	
	public SaveState(ArrayList<object.location.Location> locazioni, String path) {
		this.locazioni = locazioni;
		this.path = path;
	}
	
	/** Metodo per aprire il file, gestisce tutte le eccezioni. */
	public void openFile()	 throws IOException {
		output = new ObjectOutputStream(new FileOutputStream(path));
	}
	
	/** Metodo per scrivere sul file, gestisce tutte le eccezioni */
	public void writeFile() throws IOException {
		output.writeObject(locazioni);
	}

	/** Metodo per chiudere il file, gestisce ogni eccezione */
	public void closeFile() throws IOException {
		if ( output != null )		
			output.close();
	}
	
	/** Questo è il metodo riassuntivo salva, che apre il file, lo scrive, e chiude. */
	public void save() throws IOException {
		openFile();
		writeFile();
		closeFile();
	}
}
