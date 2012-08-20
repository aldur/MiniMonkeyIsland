/** CD.java
 * 10/mag/2012 20:26:43
 * Last edit: 10/mag/2012 20:26:43
 * 
 * 
 */

package object.thing;

/**
 * Un CD non può essere letto o usato da solo, ma ha bisogno di un pc. Contiene un'etichetta ed un testo contenuto.
 */
public class DischettoPerComputer extends Utils 
				 implements Slavable<Computer> {
	
	protected String label;
	protected String content;
	
	protected boolean usato;
	protected boolean inserito;
	
	/** Questo costruttore prende in input anche un etichetta per il CD */
	public DischettoPerComputer(String code, String name, String label, String content) {
		super(code, name);
		this.label = label;
		this.content = content;
		usato = inserito = false;
	}
	
	/** Questo è il costruttore che verrà usato dal parser. */
	public DischettoPerComputer(String code, String name, String content) {
		this(code, name, "", content);
	}
		
	@Override
	/** Il metodo esamina per il CD ritorna il contenuto se inserito in un PC, altrimenti un info al riguardo */
	public String esamina() {
		examinated = true;
		if (inserito) {
			return "CD Label: " + label + "\nContenuto: " + content;
		}
		else {
			return "CD Label: " + label + "\nPer vedere il contenuto, inseriscilo in un PC.";
		}
	}
	
	@Override
	/** Per usare un CD con un computer, si controlla prima che il computer non abbia già un disco al proprio interno.
	 * Nel caso, si prova ad inserirlo, e si setta usato.
	 */
	public void usa(Computer with) throws AlreadyInStateException, IncompatibleThingException, OffObjectException {		
		
		if (!with.getState()) {
			throw new OffObjectException();
		}
			
		if (with.hasDisk()) {
			throw new AlreadyInStateException();
		}
		
		with.insertDisk(this);
		inserito = usato = true;
	}
}
