/** Thing.java
 * 09/mag/2012 14:39:33
 * Last edit: 09/mag/2012 14:39:33
 * 
 * Questo e' il file della superclasse Oggetto (fisico).
 */

package object.thing;

/**
 * Questa è la classe per ogni oggetti di gioco inanimato o immobile.
 * Oltre ai campi di default contiene variabili di stato, ad esempio examinated, ed un proprietario.
 */
public abstract class Thing extends object.GameObject {
	
	protected boolean examinated;
	protected object.actor.Actor owner;
	
	/** Questo costruttore, oltre ai valori di default, prende in input un Attore proprietario dell'oggetto. */
	public Thing(String code, String name, object.actor.Actor owner) {
		this(code, name);
		this.owner = owner;
	}
	
	/** Costruttore per il parser */
	public Thing(String code, String name) {
		super(code, name);
		examinated = false;
		owner = null;
	}
	
	/** Questo metodo permette di impostare il proprietario di un oggetto. */
	public void setOwner(object.actor.Actor owner) {
		this.owner = owner;
	}
	
	public object.actor.Actor getOwner() {
		return owner;
	}	
	
	/** Il metodo esamina ritorna una stringa per poter essere un po' piu' versatile, possibili miglioramenti in seguito. 
	 * Di default, setta la variabile di stato e ritorna il tipo dell'oggetto. */
	public String esamina() {
		examinated = true;
		return "Quest'oggetto è di tipo " + getClass().getSimpleName();
	}
}
