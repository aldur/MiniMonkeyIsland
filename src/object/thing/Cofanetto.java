/** Cofanetto.java
 * 11/mag/2012 18:50:56
 * Last edit: 11/mag/2012 18:50:56
 * 
 * 
 */

package object.thing;

import java.util.ArrayList;

/**
 * Questa e' la classe Cofanetto, un generico contenitore che accetta una capienza massima di elementi uguale a 5 per default, o modificabile.
 */
public class Cofanetto extends Container {
	
	/** Questo costruttore prende in input una capienza massima */
	public Cofanetto(String code, String name, int max) {
		super(code, name, new ArrayList<Utils>(max));		
	}
		
	/** Il costruttore usato dal parser */
	public Cofanetto(String code, String name) {
		this(code, name, 5);
	}
}
