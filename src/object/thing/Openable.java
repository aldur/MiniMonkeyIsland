/** Openable.java
 * 09/mag/2012 17:37:02
 * Last edit: 09/mag/2012 17:37:02
 * 
 * Questo e' il file dell'interfaccia "apribile".
 */

package object.thing;

/**
 * Questa interfaccia caratterizza gli oggetti che possono essere aperti.
 */
public interface Openable {
	/** Questo metodo permette di aprire l'oggetto. */
	public void apri() throws WrongCodeException;
	/** Questo metodo permette di chiudere l'oggetto */
	public void chiudi();
	/** Questo metodo ritorna vero se l'oggetto è aperto. */
	public boolean getState();
}
