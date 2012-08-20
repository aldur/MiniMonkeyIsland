/** Denaro.java
 * 27/mag/2012 16:24:06
 * Last edit: 27/mag/2012 16:24:06
 * 
 * 
 */

package object.thing;

/**
 * La classe denaro. Visto che la traccia non fornisce alcuna indicazione, è stata implementata solo la quantità sotto forma di intero.
 */
public class Denaro extends Utils {

	protected int quantita;
	
	/** Questo costruttore prende in input una già data quantità di denaro */
	public Denaro(String code, String name, int quantita) {
		super(code, name);
		this.quantita = quantita;
	}
	
	/** Questo è il costruttore che verrà usato dal parser. */
	public Denaro(String code, String name) {
		this(code, name, 10);
	}
	
	public int getQuantita() {
		return quantita;
	}
	
	public void setQuantita(int in) {
		this.quantita = in;
	}
	
	/** Questo metodo aggiunge una quantità in input a quella già data. */
	public void addQuantita(int in) {
		this.quantita += in;
	}
	
}
