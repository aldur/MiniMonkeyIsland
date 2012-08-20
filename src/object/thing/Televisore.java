/** Televisore.java
 * 11/mag/2012 19:16:31
 * Last edit: 11/mag/2012 19:16:31
 * 
 * 
 */

package object.thing;

/**
 * Generica classe Televisore, si accende e si spegne, per il momento.
 */
public class Televisore extends Furniture
						 implements Turnable {
	
	protected boolean on;
	
	/** Costruttore di default, crea una Televisione spenta */
	public Televisore(String code, String name) {
		super(code, name);
		on = false;
	}
	
	public void turnOn() throws AlreadyInStateException {
		if (on)
			throw new AlreadyInStateException();
		
		on = true;
	}
	
	public void turnOff() throws AlreadyInStateException {
		if (!on)
			throw new AlreadyInStateException();
		
		on = false;
	}
	
	public boolean getState() {
		return on;
	}
}
