/** EndGameException.java
 * 30/giu/2012 18:00:57
 * Last edit: 30/giu/2012 18:00:57
 * Questa è la classe rappresentante l'eccezione di fine gioco.
 */

package object;

/**
 * Questa eccezione viene lanciata nel momento in cui si raggiunge una determinata locazione contraddistinta dal codice di fine (come da specifiche).
 * Il chiamante poi, notificherà all'utente il completamento del gioco.
 */
public class EndGameException extends Exception {
	
	/** Il costruttore non prende alcun argomento in input, ma chiama il super() con il messaggio:
	 * "Congratulazioni, hai finito il gioco!"
	 */
	public EndGameException(){	
		super("Congratulazioni, hai finito il gioco!");		
	}
	
	/** Il toString() di OGNI eccezione del gioco ne ritorna il messaggio. */
	@Override
	public String toString(){		
		return getMessage();
	}
}