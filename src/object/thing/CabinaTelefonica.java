/** CabinaTelefonica.java
 * 11/mag/2012 19:27:48
 * Last edit: 11/mag/2012 19:27:48
 * 
 * 
 */

package object.thing;

/**
 * Questo e' il primo esempio di oggetto "Master". Cioè che, per funzionare, ha bisogno di un gettone.
 * Per semplicità, un gettone una volta inserito è per sempre.
 * Altra possibile implementazione tramite un'interfaccia "Master"
 * Dopo l'aggiornamento delle specifiche, implemento la cabina telefonica come un Container, che può contenere solo un 
 * oggetto di tipo telefono.
 */
public class CabinaTelefonica extends Container
								implements Master {
	protected Gettone coin;
	
	// Aggiungo questo riferimento per pura visibilità.
	protected Telefono t;
	
	/** Questo costruttore prende in input un telefono */
	public CabinaTelefonica(String code, String name, Telefono t) {
		this(code, name);
		container.add(t);
		this.t = t;
	}
	
	/** Questo è il costruttore usato dal parser */
	public CabinaTelefonica(String code, String name) {
		super(code, name);
		this.coin = null;
	}
	
	/** Questo metodo permette di settare il telefono all'interno della cabina. */
	public void setTelefono(Telefono in) {
		this.t = in;
	}
	
	public Telefono getTelefono() {
		return t;
	}	
	
	/** Questo metodo permette di inserire un gettone nella cabina. */
	public void setGettone(Gettone in) throws AlreadyInStateException {
		if (coin != null)
			throw new AlreadyInStateException();
				
		this.coin = in;
	}
	
	/** Questo metodo espelle un gettone dalla cabina */
	public Gettone getGettone() throws MissingSlaveException {
		if (coin == null)
			throw new MissingSlaveException();
		
		Gettone temp = coin;
		coin = null;
		return temp;
	}
	
	/** Per chiamare da una cabina, controllo prima che vi sia un gettone inserito, ed in caso affermativo, chiamo il metodo usa del telefono.
	 * Si esamina il contenuto della cabina, e si chiama.
	 * 
	 * In realtà, se l'utente esamina il contenuto della cabina, che è un contenitore, può forzare il telefono a chiamare anche senza gettone.
	 * La risoluzione sembra troppo nel dettaglio per le specifiche del progetto.
	 * @throws MissingSlaveException
	 */
	public void usa(object.actor.Actor with) throws MissingSlaveException {
		if (coin == null) {
			throw new MissingSlaveException();
		}
		
		if (t != null)
			with.usa(t);
	}
}
