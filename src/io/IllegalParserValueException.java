/** IllegalParserValueException.java
 * 01/giu/2012 17:18:42
 * Last edit: 01/giu/2012 17:18:42
 * 
 * 
 */

package io;

/**
 * Questa eccezione verrà lanciata quando si avrà un argomento non valido nel parser, ad esempio un oggetto non implementato, o una grammatica sbagliata.
 */
public class IllegalParserValueException extends Exception {
	
	/** Il costruttore prende in input la riga in cui è presenta l'errore. */
	public IllegalParserValueException(String in){	
		super("FATAL: Un argomento del parser per la riga:\n\t" + in + "\nnon è valido.");		
	}
	
	/** O un avvertimento generico. */
	public IllegalParserValueException() {
		super("FATAL: Il file parser non è valido.");
	}
	
	/** Il toString() di OGNI eccezione del gioco ne ritorna il messaggio. */
	@Override
	public String toString(){		
		return getMessage();
	}
}
