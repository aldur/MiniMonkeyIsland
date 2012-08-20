/** Botola.java
 * 22/mag/2012 17:41:51
 * Last edit: 22/mag/2012 17:41:51
 * 
 * 
 */

package object.thing;

/**
 * La Botola è chiusa per default, apribile solo tramite una leva o un bottone.
 */
public class Botola extends Through {
	
	public Botola(String code, String name, object.location.Location linkA, object.location.Location linkB, String keyCode) {
		super(code, name, linkA, linkB, false, true, keyCode);
	}
	
	/** Questo è il costruttore che verrà usato dal parser, che crea la botola chiusa a chiave. */
	public Botola(String code, String name, object.location.Location linkA, object.location.Location linkB) {
		super(code, name, linkA, linkB, false, true, null);
	}

	@Override
	/** Questo metodo ritorna vero se l'oggetto in input è quello destinata ad aprire questa botola. (metodo della superclasse)
	 * Inoltre, se la il "codice" coincide, apre la botola.
	 */
	public boolean getKey(EventCreator in) {
		if (super.getKey(in)) {
			if (!opened)
				apri();
			
			else
				chiudi();
				
			return true;
		}
			
		return false;
	}
}

