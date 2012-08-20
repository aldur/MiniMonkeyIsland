/** Lettera.java
 * 10/mag/2012 16:59:45
 * Last edit: 10/mag/2012 16:59:45
 * 
 * Una semplice lettera
 */

package object.thing;

/**
 * La classe lettera, contentente un testo, mittente e destinatario.
 */
public class Lettera extends Utils 
					  implements Readable
{
	protected final String destinatario;
	protected final String mittente;
	protected final String contents;
	
	protected boolean letto;
	
	/** Costruttore completo, con mittente, destinatario, e contenuto */
	public Lettera(String code, String name, String mittente, String destinatario, String contents) {
		super(code, name);
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.contents = contents;
	}
	
	/** Di default il destinario sei tu, ed il mittente è anonimo */
	public Lettera(String code, String name, String contents) {
		this(code, name, "Anonimo", "Tu", contents);
	}
	
	public String esamina() {
		examinated = true;
		return leggi();
	}
	
	public String leggi() {
		letto = true;
		return "Da: " + mittente + " a: " + destinatario + "\n" + contents;
	}
	
	public boolean letto() {
		return letto;
	}
}
