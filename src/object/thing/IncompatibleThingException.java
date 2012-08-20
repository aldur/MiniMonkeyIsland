/** IncompatibleThingException.java
 * 11/mag/2012 19:33:51
 * Last edit: 11/mag/2012 19:33:51
 * 
 * 
 */

package object.thing;

/**
 * Questa eccezione verrà lanciata se si provano ad usare insieme due oggetti di tipo non compatibile.
 */
public class IncompatibleThingException extends Exception {

	/** Il costruttore non prende alcun argomento in input, ma chiama il super() con il messaggio:
	 * "Impossibile eseguire questa azione su un oggetto di questo tipo."
	 */
	public IncompatibleThingException() {
		super("Impossibile eseguire questa azione su un oggetto di questo tipo.");
	}
	
	/** Il toString() di OGNI eccezione del gioco ne ritorna il messaggio. */
	@Override
	public String toString() {
		return getMessage();
	}	
}
