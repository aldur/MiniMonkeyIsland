/** WrongCodeException.java
 * 11/mag/2012 18:59:23
 * Last edit: 11/mag/2012 18:59:23
 * 
 * 
 */

package object.thing;

/**
 * Eccezione che gestiste il codice della cassaforte sbagliato.
 */

public class WrongCodeException extends Exception {

	/** Il costruttore non prende alcun argomento in input, ma chiama il super() con il messaggio:
	 * "Il codice inserito è sbagliato."
	 */
	public WrongCodeException() {		
		super("Il codice inserito è sbagliato.");		
	}
	
	/** Il toString() di OGNI eccezione del gioco ne ritorna il messaggio. */
	@Override
	public String toString(){		
		return getMessage();
	}	
}