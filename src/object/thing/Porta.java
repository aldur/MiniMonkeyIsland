/** Porta.java
 * 22/mag/2012 17:11:10
 * Last edit: 22/mag/2012 17:11:10
 * 
 * 
 */

package object.thing;

import object.location.Location;

/**
 * La classe Porta instanzia Through, permette di creare oggetti chiusi ma sbloccati.
 */

public class Porta extends Through {
	
	/** Costruttore "completo", just in case */
	public Porta(String code, String name, Location linkA, Location linkB, boolean opened,
			boolean locked, String keyCode) {		
		super(code, name, linkA, linkB, opened, locked, keyCode);
	}
	
	/** Costruttore con codice chiave */
	public Porta(String code, String name, Location linkA, Location linkB, boolean locked, String keyCode) {		
		super(code, name, linkA, linkB, false, locked, keyCode);
	}
	
	/** Costruttore usato dal parser */
	public Porta(String code, String name, Location linkA, Location linkB) {
		super(code, name, linkA, linkB, false, false, null);
	}
	
}
