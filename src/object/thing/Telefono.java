/** Telefono.java
 * 16/mag/2012 12:00:55
 * Last edit: 16/mag/2012 12:00:55
 * 
 * 
 */

package object.thing;

/**
 * Dopo la revisione delle specifiche, i telefoni sono implementati stile matrix, cioè chiamando si viene teletrasportati
 * in un'altra locazione. Per cui, modifico il codice sul modello dell'oggetto Pillola.
 */
public class Telefono extends Utils 
						implements Teleporter {
	
	protected final object.location.Location to;
	
	/** Costruttore per il parser */
	public Telefono(String code, String name, object.location.Location to) {
		super(code, name);
		this.to = to;
	}
	
	@Override
	/** Esaminare un telefono fa sapere dove conduce */
	public String esamina() {
		examinated = true;
		return "Questo è un oggetto di tipo telefono, per il trasporto a: " + to.toString();
	}
	
	public object.location.Location getLocation() {
		return to;
	}
}
