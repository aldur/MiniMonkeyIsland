/** Gettone.java
 * 11/mag/2012 19:29:59
 * Last edit: 11/mag/2012 19:29:59
 * 
 * 
 */

package object.thing;

/**
 * Questo e' un gettone, cioè un oggetto slave che non può funzionare senza una cabina telefonica.
 */

public class Gettone extends Utils 
					  implements Slavable<CabinaTelefonica> {
	
	protected CabinaTelefonica cab;
	
	/** Questo costruttore prende in input una cabina telefonica in cui è inserito il gettone */
	public Gettone(String code, String name, CabinaTelefonica cab) {
		super(code, name);
		this.cab = cab;
	}
	
	/** Costruttore per il parser */
	public Gettone(String code, String name) {
		this(code, name, null);
	}
	
	/** Il metodo usa in sostanza fa questo:
	 * 	Controlla che la cabina non abbia già un gettone inserito e in caso lo inserisce, passando il proprio riferimento.
	 */
	public void usa(CabinaTelefonica out) throws AlreadyInStateException {		
		out.setGettone(this);
	}
		
}
