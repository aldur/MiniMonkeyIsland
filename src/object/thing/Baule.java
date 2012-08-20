/** Baule.java
 * 11/mag/2012 18:45:37
 * Last edit: 11/mag/2012 18:45:37
 * 
 * 
 */

package object.thing;

import java.util.ArrayList;

/**
 *	Questa e' la classe Baule, un generico contenitore che accetta una capienza massima di elementi uguale a 20 per default, o modificabile.
 */
public class Baule extends Container {
	
	/** Questo costruttore prende in input anche una capienza massima. */
	public Baule(String code, String name, int max) {
		super(code, name, new ArrayList<Utils>(max));		
	}
	
	/** Il costruttore di default */
	public Baule(String code, String name) {
		this(code, name, 20);
	}
}
