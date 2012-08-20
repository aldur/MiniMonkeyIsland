/** ClosedObjectException.java
 * 11/mag/2012 10:26:48
 * Last edit: 11/mag/2012 10:26:48
 * 
 */

package object.thing;

/**
 * Questa è l'eccezione che verrà lanciata quando si proverà ad eseguire determinate azioni su oggetti chiusi.
 * Esempio: cercare il contenuto di un contenitore chiuso.
 */
public class ClosedObjectException extends Exception {

	/** Il costruttore non prende alcun argomento in input, ma chiama il super() con il messaggio:
	 * "L'oggetto è chiuso!"
	 */
	public ClosedObjectException(){		
		super("L'oggetto è chiuso!");		
	}
	
	/** Il toString() di OGNI eccezione del gioco ne ritorna il messaggio. */
	@Override
	public String toString(){		
		return getMessage();
	}	
}