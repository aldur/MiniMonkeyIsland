/** MissingSlaveException.java
 * 13/mag/2012 13:35:31
 * Last edit: 13/mag/2012 13:35:31
 * 
 * 
 */

package object.thing;

/**
 * Questa eccezione verrà lanciata provando ad usare un oggetto che non può funzionare da solo senza la controparte.
 * Ad esempio, un dischetto per computer.
 */
public class MissingSlaveException extends Exception {

	/** Il costruttore non prende alcun argomento in input, ma chiama il super() con il messaggio:
	 * "L'oggetto non può funzionare da solo!"
	 */
	public MissingSlaveException(){		
		super("L'oggetto non può funzionare da solo!");		
	}
	
	/** Il toString() di OGNI eccezione del gioco ne ritorna il messaggio. */
	@Override
	public String toString(){		
		return getMessage();
	}	
}