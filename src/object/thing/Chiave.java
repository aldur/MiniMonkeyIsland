/** Chiave.java
 * 16/mag/2012 12:28:59
 * Last edit: 16/mag/2012 12:28:59
 * 
 * 
 */

package object.thing;

/**
 * In questa implementazione una chiave è Slavable, ottiene un codice univoco dal costruttore, che verrà confrontato con quello della porta. Se corrisponde, la porta viene sbloccata.
 */
public class Chiave extends Utils
					 implements Slavable<Through> {
	
	/* Anche qui, sebbene l'oggetto Through esegua un controlla sulla chiave, lo faccio eseguire anche alla chiave stessa. */
	protected final Through with;
	
	/** Il costruttore usato dal parser, prende in input l'oggetto cui associare la chiave. */
	public Chiave(String code, String name, Through with) {
		super(code, name);
		this.with = with;
	}
	
	/** Usa controlla che l'oggetto in input sia quello per cui è stata progettata la chiave, ed in caso affermativo chiama il metodo getKey dei through */
	public void usa(Through t) throws AlreadyInStateException, WrongCodeException {
		/* Per usare una chiave, si intende inserirla nella toppa, e girare. Per aprire poi l'oggetto, andrà usato il metodo adeguato sull'oggetto stesso. */
		if (!t.equals(with))
			throw new WrongCodeException();
		
		t.getKey(this);
	}
}
