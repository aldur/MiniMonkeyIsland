/** OffObjectException.java
 * 13/mag/2012 13:26:26
 * Last edit: 13/mag/2012 13:26:26
 * 
 * 
 */

package object.thing;

/**
 * Questa eccezione verrà lanciata provando ad usare un oggetto spento.
 */
public class OffObjectException extends Exception {

	/** Il costruttore non prende alcun argomento in input, ma chiama il super() con il messaggio:
	 * "L'oggetto è spento!"
	 */
	public OffObjectException(){		
		super("L'oggetto è spento!");		
	}
	
	/** Il toString() di OGNI eccezione del gioco ne ritorna il messaggio. */
	@Override
	public String toString(){		
		return getMessage();
	}	
}