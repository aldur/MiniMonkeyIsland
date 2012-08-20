/** Leva.java
 * 16/mag/2012 12:22:15
 * Last edit: 16/mag/2012 12:22:15
 * 
 * 
 */

package object.thing;

/**
 * Una leva, come un bottone, è collegata in modo final ad un oggetto Through, e quando azionata lo apre o lo chiude.
 * Dopo una revisione, esso estende Furniture, e non Utils, in quanto gli oggetti Utils possono essere messi nell'intentario.
 */
public class Leva extends Furniture
					implements Usable, EventCreator {
	
	protected final Through with;
	protected boolean tirata;
	protected boolean usata;
	
	/** Il costruttore prende in input l'oggetto through cui associare la leva. */
	public Leva(String code, String name, Through with) {
		super(code, name);
		this.with = with;
		tirata = false;
		usata = false;
	}
	
	/** Il metodo usa setta i vari attributi di stato, e agisce sull'oggetto. */
	public void usa() {
		usata = true;
		
		with.getKey(this);
		
		if (!tirata)
			tirata = true;
		
		else
			tirata = false;
		
		if (with.getState())
			with.chiudi();
		
		else
			with.apri();	
	}
	
	public boolean usato() {
		return usata;
	}
	
	@Override
	/** Il metodo esamina per la leva aggiunge lo stato, se tirata o meno */
	public String esamina() {
		String str = "Questo è un oggetto di tipo leva.";
		
		if (tirata)
			str += " (tirata)";
		
		return str;
	}

}
