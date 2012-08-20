/** Pillola.java
 * 16/mag/2012 11:55:39
 * Last edit: 16/mag/2012 11:55:39
 * 
 * 
 */

package object.thing;

/**
 * Una pillola, come da specifiche, teletrasporta il giocatore in una data locazione.
 * Per farlo, crea una porta "temporanea", e la fa usare al giocatore.
 */
public class Pillola extends Utils 
						implements Teleporter{
	
	protected String colore;
	protected final object.location.Location to;
	
	/** Questo costruttore prende in input una stringa colore, oltre alla locazione. */
	public Pillola(String code, String name, String colore, object.location.Location to) {
		super(code, name);
		this.colore = colore;
		this.to = to;
	}
	
	/** Questo è il costruttore usato dal parser */
	public Pillola(String code, String name, object.location.Location to) {
		super(code, name);
		this.colore = "Rosso";
		this.to = to;
	}
	
	@Override
	/** Il metodo esamina per le pillole ritorna il colore, se impostato */
	public String esamina() {
		examinated = true;
		String str = "Questa è una pillola dagli effetti misteriosi"; 
		
		if (colore != null) {
			str += "di colore" + colore;
		}
		
		return str;
	}
	
	public object.location.Location getLocation() {
		return to;
	}
}
