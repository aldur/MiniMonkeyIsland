/** Turnable.java

 * 10/mag/2012 15:00:32
 * Last edit: 10/mag/2012 15:00:32
 * 
 * Interfaccia che prevede due metodi per (thing)oggetti che possono essere accesi o spenti
 */

package object.thing;

/**
 * Questa interfacci caratterizza gli oggetti che possono essere accesi o spenti.
 */
public interface Turnable {
	/** Questo metodo accende un oggetto, e lancia un'eccezione nel caso sia già acceso. */
	void turnOn() throws AlreadyInStateException;
	/** Questo metodo spegne un oggetto, e lancia un'eccezione nel caso sia già spento. */
	void turnOff() throws AlreadyInStateException;
	/** Questo metodo serve a ritornare lo stato dell'oggetto. */
	boolean getState();
}
