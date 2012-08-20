/** UnusableThingException.java
 * 27/mag/2012 16:00:59
 * Last edit: 27/mag/2012 16:00:59
 * 
 * 
 */

package object.thing;

/**
 * Questa eccezione verrà lanciata se si provano ad usare un oggetto impossibile da usare, ad esempio che si trova in un altra stanza.
 */
public class UnusableThingException extends Exception {
	
	/** Il costruttore non prende alcun argomento in input, ma chiama il super() con il messaggio:
	 * "È impossibile eseguire quest'azione sull'oggetto selezionato."
	 */
	public UnusableThingException() {
		super("È impossibile eseguire quest'azione sull'oggetto selezionato.");
	}
	
	/** Il toString() di OGNI eccezione del gioco ne ritorna il messaggio. */
	@Override
	public String toString() {
		return getMessage();
	}
	
}
