/** Usable.java
 * 09/mag/2012 17:44:01
 * Last edit: 09/mag/2012 17:44:01
 * 
 * Questo e' il file dell'interfaccia "usabile".
 */

package object.thing;

/**
 * Questa è l'interfaccia che caratterizza gli oggetti che possono essere usati.
 * Implementa due metodi, usa che permetterà di eseguire determinate azioni dipendenti dall'oggetto, ed usato che aggiorna la relativa variabile di stato.
 */
public interface Usable {
	/** Questo metodo, a secondo dell'oggetto, svolgerà una diversa funzione. */
	public void usa();
	/** Questo metodo aggiornerà le variabili di stato dell'oggetto. */
	public boolean usato();
}
