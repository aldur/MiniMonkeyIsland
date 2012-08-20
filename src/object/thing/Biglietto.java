/** Biglietto.java
 * 13/mag/2012 13:46:34
 * Last edit: 13/mag/2012 13:46:34
 * 
 * 
 */

package object.thing;

/**
 * Questa e' la classe per gli oggetti di tipo biglietto.
 * In seguito all'aggiornamento delle specifiche, si implementa l'interfaccia Readable.
 */
public class Biglietto extends Utils 
						implements Readable {
	
	protected boolean letto;
	protected String contents;

	/*
	public Biglietto(String code, String name) {
		super(code, name);
	}*/
	
	/** Il costruttore per il parser */
	public Biglietto(String code, String name, String contents) {
		super(code, name);
		this.letto = false;
		this.contents = contents;
	}

	@Override
	public String leggi() {
		letto = true;
		return contents;
	}

	@Override
	public boolean letto() {
		return letto;
	}
	
	@Override
	/** Il metodo esamina per il biglietto ne ritorna il contenuto. */
	public String esamina() {
		examinated = true;
		return "Un biglietto con su scritto: " + contents;
	}
}