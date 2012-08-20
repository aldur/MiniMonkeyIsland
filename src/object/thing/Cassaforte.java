/** Cassaforte.java
 * 11/mag/2012 18:53:50
 * Last edit: 11/mag/2012 18:53:50
 * 
 * 
 */

package object.thing;

import java.util.ArrayList;

/**
 * Questa e' la classe Cassaforte. La peculiarita' e' che si apre solo inserendo la giusta combinazione (default = 0000).
 * Per default contiene 10 oggetti.
 */
public class Cassaforte extends Container {
	private final String password;
	protected String inputCode;
	protected boolean locked;
	
	/** Costruttore che prende in input capienza massima e parola chiave */
	public Cassaforte(String code, String name, int max, String password) {
		super(code, name, new ArrayList<Utils>(max));
		this.password = password;
		locked = true;
	}
	
	/** Costruttore che prende in input capienza massima */
	public Cassaforte(String code, String name, int max) {
		this(code, name, max, "0000");	
	}
		
	/** Costruttore usato dal parser */
	public Cassaforte(String code, String name) {
		this(code, name, 10, "0000");
	}
	
	/** Questo metodo permette di settare la parola chiave */
	public void setInputCode(String in) {
		inputCode = in;
	}
	
	@Override
	/** Questo metodo permette di aprire la cassaforte.
	 * Sta al chiamante chiedere la password, che verrà confrontata con l'attributo in memoria.
	 * In caso negativo, lancia l'eccezione apposita.
	 */
	public void apri() throws WrongCodeException {
		if (inputCode != null && inputCode.equals(password)) {
			aperto = true;
			locked = false;
			return;
		}
		
		throw new WrongCodeException();		
	}
	
	public boolean isLocked() {
		return locked;
	}
}
