/** Container.java 
 * 10/mag/2012 13:49:12
 * Last edit: 10/mag/2012 13:49:12
 * 
 * Classe astratta che idealizza gli oggetti che ne possono contenere altri
 */

package object.thing;

import java.util.ArrayList;

/**
 * Questa è la classe astratta degli oggetti che contengono altri oggetti.
 */
public abstract class Container extends Furniture
								   implements Openable {
	
	protected ArrayList<Utils> container;
	protected boolean cercato;
	protected boolean aperto;

	/** Questo è il costruttore usato dal parser, inizializza la lista di oggetti contenuti vuota. */
	public Container (String code, String name) {
		this(code, name, new ArrayList<Utils>());
	}
	
	public Container (String code, String name, ArrayList<Utils> container) {
		super(code, name);
		cercato = false;
		aperto = false;
		this.container = container;
	}
	
	@Override
	/** Il metodo esamina, in caso l'oggetto sia aperto, ritorna una lista degli oggetti contenuti. */
	public String esamina() {
		/* Si segna esaminato in ogni caso, se poi l'oggetto è aperto allora si ritorna il contenuto in una stringa.
		 * Altrimenti, si chiama il toString della superclasse.
		 * Edit, perchè il casino con le eccezioni? Se è chiuso ritorno semplicemente che l'oggetto è chiuso, lo sto solo esaminando.
		 */		
		examinated = true;
		
		if (!aperto) {
			return name + " (Chiuso).";
		}			
			
		cercato = true;			
		String s = name + " contiene:";
			
		for (Utils u : container) {
			s += "\n" + u.toString();
		}
			
		if (s.equals(name + " contiene:")) 
			s = name + " è vuoto.";
				
		return s;
	}
	
	public boolean cercato() {
		return cercato;
	}
	
	public void apri() throws WrongCodeException {
		aperto = true;
	}
	
	public void chiudi() {
		aperto = false;
	}
	
	public boolean getState() {
		return aperto;
	}
	
	/** Questo metodo ritorna il contenuto sotto forma di ArrayList di Utils */
	public ArrayList<Utils> getContent() throws ClosedObjectException {
		if (!aperto) throw new ClosedObjectException();
		return container;
	}
	
	/** Aggiunte un oggetto al contenitore */
	public void addContent(Utils in) {
		container.add(in);
	}
	
	/** Controlla se il contenitore contiene un determinato oggetto */
	public boolean contains(Utils in) {
		return container.contains(in);
	}
}
