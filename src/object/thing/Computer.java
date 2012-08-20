/** Computer.java
 * 10/mag/2012 20:22:05
 * Last edit: 10/mag/2012 20:22:05
 * 
 * Questo e' il file contentente la classe computer.
 */

package object.thing;

/**
 * Questa e' la classe computer. La pecularita' e' che un computer deve poter leggere un cd, e stamparne i dati contenuti.
 */
public class Computer extends Utils
						implements Turnable, Master {
	
	// Riferimento al cd e booleano se ne contiene uno
	protected DischettoPerComputer in;
	protected boolean disk;
	
	// booleani, se acceso e letto contenuto cd
	protected boolean on;
	protected boolean letto;
	
	/** Questo costruttore prende in input un CD e lo stato del Computer */
	public Computer(String code, String name, DischettoPerComputer in, boolean on) {
		super(code, name);
		this.in = in;
		this.disk = true;
		this.on = on;
		letto = false;
	}
	
	/** Questo costruttore prende in input un cd */
	public Computer(String code, String name, DischettoPerComputer in) {
		this(code, name, in, false);
	}
	
	/** Questo è il costruttore che verrà usato dal parser. */
	public Computer(String code, String name) {
		this(code, name, null, false);
		disk = false;
	}
	
	/** Questo metodo espelle un CD se inserito.
	 * 
	 * @throws OffObjectException
	 * @throws AlreadyInStateException
	 */
	public void eject() throws OffObjectException, AlreadyInStateException {
		if (!on) {
			throw new OffObjectException();
		}
		
		if (!disk) {
			throw new AlreadyInStateException();
		}
		
		in = null;
		disk = false;		
	}
	
	/** Ritorna vero se il computer ha un disco inserito. */
	public boolean hasDisk() {
		return disk;
	}
	
	/** Ho eliminato i controlli in quanto vengono eseguiti dall'oggetto slave. */
	public void insertDisk(DischettoPerComputer in) {		
		this.in = in;
		disk = true;
	}
	
	/** Questo metodo legge il contenuto di un CD inserito nel PC.
	 * 
	 * @return il contenuto del CD
	 * @throws OffObjectException
	 * @throws MissingSlaveException
	 */
	public String leggi() throws OffObjectException, MissingSlaveException {
		
		if (!on) {
			throw new OffObjectException();
		}
		
		if (!disk) {
			throw new MissingSlaveException();
		}
		
		letto = true;
		return in.esamina();
	}
		
	@Override
	/** Il metodo esamina di un computer ritorna il nome del proprietario, lo stato, e se ha o meno un cd inserito. */
	public String esamina() {
		examinated = true;
		String str = "Computer ";
		
		if (owner != null)
			str += " di: " + owner.getName();
		
		try {
			str += "\n" + leggi();
		}
		
		catch (OffObjectException e) {
			str += " (Spento)";
		}
		
		catch (MissingSlaveException e) {
			str += " (Senza CD)";
		}
		
		return str;		
	}
	
	public void turnOn() {
		on = true;
	}
	
	public void turnOff() {
		on = false;
	}
	
	public boolean getState() {
		return on;
	}
	
	public boolean letto() {
		return letto;
	}
}
