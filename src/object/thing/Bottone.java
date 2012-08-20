/** Bottone.java
 * 16/mag/2012 12:10:30
 * Last edit: 16/mag/2012 12:10:30
 * 
 * 
 */

package object.thing;

/**
 * Un bottone, se premuto, apre o chiude un oggetto di tipo through.
 * Dopo una revisione, esso estende Furniture, e non Utils, in quanto gli oggetti Utils possono essere messi nell'intentario, e sarebbe ambiguo avere un bottone nell'inventario.
 */
public class Bottone extends Furniture
					  implements Usable, EventCreator {
	
	protected boolean usato;
	
	protected final Through with;
	protected final String colore;
	
	/** Questo costruttore prende in input anche il colore del bottone. */
	public Bottone(String code, String name, Through with, String colore) {
		super(code, name);
		this.with = with;
		this.colore = colore;
		this.usato = false;
	}
	
	/** Questo è il costruttore che sarà utilizzato dal parser. */
	public Bottone(String code, String name, Through with) {
		this(code, name, with, "Rosso");
	}
	
	/** Questo è il costruttore più semplice, ma non associa il bottone ad alcun oggetto. */
	public Bottone(String code, String name) {
		this(code, name, null, "Rosso");
	}
	
	/** Il metodo usa del bottone associa il bottone stesso all'oggetto cui si riferisce, ed apre o chiude l'oggetto. */
	@Override
	public void usa() {
		usato = true;
		
		with.getKey(this);
		
		if (with.getState())
			with.chiudi();
		
		else
			with.apri();
	}
	
	public boolean usato() {
		return usato;
	}

	
	@Override
	/** Il metodo esamina del bottone ne ritorna il colore, e lo stato (se premuto o meno) */
	public String esamina() {
		String str = "Questo è un oggetto di tipo bottone, colore: " + colore;
		
		if (usato)  {
			str += " (premuto)";
		}
		
		return str;
	}

}
