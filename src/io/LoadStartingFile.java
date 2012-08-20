/** LoadStartingFile.java
 * 23/mag/2012 11:55:58
 * Last edit: 23/mag/2012 11:55:58
 * 
 * 
 */

package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * Per leggere i file dal testo in input, si suppone, per comodita'
 * che i campi siano ordinati nello stesso modo del testo d'esempio, quindi:
 * 	-locazioni
 * 	- aperture
 * 	- personaggi
 * 	- sentimenti (Saltato perch�� implemento il progetto in singolo)
 * 	- oggetti
 * 	- inventariopersonaggi
 * 	- locazioneoggetti
 * 	- proprietariooggetti
 * 
 * Una volta finito, si potranno ritornare le liste di Stringhe cos�� create.
 */
public class LoadStartingFile {
	private ArrayList<String> locazioni;
	private ArrayList<String> aperture;
	private ArrayList<String> personaggi;
	private ArrayList<String> oggetti;
	private ArrayList<String> inventariopersonaggi;
	private ArrayList<String> locazioneoggetti;
	private ArrayList<String> proprietarioggetti;
	
	private Scanner sc;
	
	/** Questo enum manterr�� in memoria il campo che si sta esaminando. */
	private enum Field { LOCAZIONI, APERTURE, PERSONAGGI, SENTIMENTI,
			OGGETTI, INVENTARIO_PERSONAGGI, 
		LOCAZIONE_OGGETTI, PROPRIETARI};
	
	/** Apro il file e inizializzo le liste. */
	public LoadStartingFile(String path) throws FileNotFoundException {
		
		if (path == null || path.equals("")) {
			java.io.InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mini_monkey_island.txt");
			sc = new Scanner(is);
		} else if (path.equals("debug")) {
			java.io.InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("MyMiniMonkeyIsland.debug");
			sc = new Scanner(is);
		} else {
			sc = new Scanner(new File(path));
		}
		
		locazioni = new ArrayList<String>();
		aperture = new ArrayList<String>();
		personaggi = new ArrayList<String>();
		oggetti = new ArrayList<String>();
		inventariopersonaggi = new ArrayList<String>();
		locazioneoggetti = new ArrayList<String>();
		proprietarioggetti = new ArrayList<String>();
	}
	
	/** Leggo dal file, riempio le liste con i campi, e chiudo lo scanner. */
	public void fillFields(boolean verbose) {
		
		String l = "LOC_";
		String a = "APERT_";
		String p = "PERS_";
		String o = "OGG_";
		
		Field field = Field.LOCAZIONI;
				
		/* Finche' ci sono righe del file da leggere: */
		while (sc.hasNextLine()) {			
			
			String s = stripComments(sc.nextLine());
			
			/* Setto il valore di field. */
			if ( s.equals("LOCAZIONI") || s.equals("APERTURE") || s.equals("PERSONAGGI") || s.equals("SENTIMENTI") || s.equals("OGGETTI") ||
					s.equals("INVENTARIO_PERSONAGGI") || s.equals("LOCAZIONE_OGGETTI") || s.equals("PROPRIETARI") ) {
				
				field = Field.valueOf(s);
			}
			
			/* Il confronto con Field in questi casi �� un po' superfluo, ma l'ho aggiunto per sicurezza/leggibilit�� */
			if (s.startsWith(l) && field == Field.LOCAZIONI) {
				locazioni.add(s);
			}
			else if (s.startsWith(a) && field == Field.APERTURE) {
				aperture.add(s);
			}
			else if (s.startsWith(p) && field == Field.PERSONAGGI) {
				personaggi.add(s);
			}
			
			else if (s.startsWith(p) && field == Field.SENTIMENTI && verbose == true) {
				// Only for debugging purposes.
				System.out.println("Salto la riga della sezione sentimenti:\n" + s);
			}
			
			else if (s.startsWith(o)) {
				switch (field) {
				
				case OGGETTI:
					oggetti.add(s);
					break;

				case INVENTARIO_PERSONAGGI:
					inventariopersonaggi.add(s);
					break;
					
				case LOCAZIONE_OGGETTI:
					locazioneoggetti.add(s);
					break;
					
				case PROPRIETARI:
					proprietarioggetti.add(s);
					break;
				}
			}
			
		}
		
		
		/* Chiudo il file perch�� non dovrei averne pi�� bisogno. */
		sc.close();
	}
	
	/** Questo metodo prende una stringa e ne rimuove i commenti */
	private static String stripComments(String s) {
		
		if (s.contains("#")) {
			s = s.substring(0, s.indexOf('#'));
		}
		
		if (s.endsWith("\t")) {
			s = s.substring(0, s.lastIndexOf('\t'));
		}
		
		return s;
	}

	// Ed ecco i metodi get.
	
	private static ArrayList<String> getList(ArrayList<String> out) {
		return out;
	}
	
	public ArrayList<String> getLocazioni() {
		return getList(locazioni);
	}
	
	public ArrayList<String> getAperture() {
		return getList(aperture);
	}
	
	public ArrayList<String> getPersonaggi() {
		return getList(personaggi);
	}
	
	public ArrayList<String> getOggetti() {
		return getList(oggetti);
	}
	
	public ArrayList<String> getInventarioPersonaggi() {
		return getList(inventariopersonaggi);
	}
	
	public ArrayList<String> getLocazioneOggetti() {
		return getList(locazioneoggetti);
	}
	
	public ArrayList<String> getProprietariOggetti() {
		return getList(proprietarioggetti);
	}
}
