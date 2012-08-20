/** Finestra.java
 * 22/mag/2012 17:40:06
 * Last edit: 22/mag/2012 17:40:06
 * 
 * 
 */

package object.thing;

import object.location.Location;

/**
 * Una finestra instanzia la classe astratta Through. Come da specifiche, viene creata chiusa ma sbloccata.
 */
public class Finestra extends Through {
	
	/** Costruita chiusa e sbloccata per default */	
	public Finestra(String code, String name, Location linkA, Location linkB) {		
		super(code, name, linkA, linkB, false, false, null);
	}
	
	/** Viene sovrascritto il metodo lock in modo che la finestra non possa essere chiusa a chiave. */
	@Override
	public void lock() {
		
	}
}
