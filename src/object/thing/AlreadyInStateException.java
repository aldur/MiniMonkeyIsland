/** AlreadyInStateException.java
 * 11/mag/2012 19:18:49
 * Last edit: 11/mag/2012 19:18:49
 * 
 * 
 */

package object.thing;

/**
 * Questa è l'eccezione che verrà lanciata quando si proverà ad eseguire un azione su un oggetto già in un determinato stato, 
 * ad esempio aprire una porta già aperta, accendere un computer già acceso, ecc..
 */
public class AlreadyInStateException extends Exception {

	/** Il costruttore non prende alcun argomento in input, ma chiama il super() con il messaggio:
	 * "L'azione non è stata eseguita perchè non necessaria o impossibile."
	 */
	public AlreadyInStateException() {
		super("L'azione non è stata eseguita perchè non necessaria o impossibile.");
	}
	
	/** Il toString() di OGNI eccezione del gioco ne ritorna il messaggio. */
	@Override
	public String toString() {
		return getMessage();
	}

}
