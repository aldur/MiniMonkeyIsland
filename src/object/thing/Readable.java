/** Readable.java
 * 10/mag/2012 16:33:36
 * Last edit: 10/mag/2012 16:33:36
 * 
 * Questa interfaccia gestira' gli oggetti che possono essere "letti".
 */

package object.thing;

/**
 * Questa è l'interfaccia che caratterizza gli oggetti che possono essere letti.
 */
public interface Readable {
	/** Questo metodo ritorna il contenuto dell'oggetto. */
	public String leggi();
	/** Questo metodo ritorna la variabile di stato. */
	public boolean letto();
}
