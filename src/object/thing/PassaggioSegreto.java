/** PassaggioSegreto.java
 * 22/mag/2012 18:04:30
 * Last edit: 22/mag/2012 18:04:30
 * 
 * 
 */

package object.thing;

/**
 * Analogo a "Botola", chiuso per default, apribile solo tramite una leva o un bottone.
 */
public class PassaggioSegreto extends Through {
	
	/** Questo costruttore prende in input il codice della chiave */
	public PassaggioSegreto(String code, String name, object.location.Location linkA, object.location.Location linkB, String keyCode) {
		super(code, name, linkA, linkB, false, true, keyCode);
	}
	
	/** Costruttore che verrà usato dal parser: */
	public PassaggioSegreto(String code, String name, object.location.Location linkA, object.location.Location linkB) {
		super(code, name, linkA, linkB, false, true, null);
	}

	@Override
	/** Questo metodo ritorna vero se la chiave è quella destinata ad aprire questa porta. (metodo della superclasse)
	 * Inoltre, se la il "codice" coincide, apre la botola.
	 */
	public boolean getKey(EventCreator in) {
		if (super.getKey(in)) {
			if (!opened)
				apri();
				
			if (opened)
				chiudi();
				
			return true;
		}
			
		return false;
	}

}
