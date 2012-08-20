/** Mappa.java
 * 13/mag/2012 13:49:32
 * Last edit: 13/mag/2012 13:49:32
 * 
 * 
 */

package object.thing;

/**
 * Questa e' la classe per gli oggetti di tipo mappa. 
 * Dopo l'aggiornamento delle specifiche, implementa l'interfaccia readable.
 */
public class Mappa extends Utils 
					implements Readable {
	
	protected String contents;
	protected boolean letto;

	/** Costruttore usato dal parser */
	public Mappa(String code, String name, String contents) {
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
	/**Il metodo esamina chiama semplicemente il metodo leggi. */
	public String esamina() {
		examinated = true;
		return leggi();
	}

	@Override
	public boolean letto() {
		return letto;
	}
}
