/** Tappeto.java
 * 11/mag/2012 19:09:35
 * Last edit: 11/mag/2012 19:09:35
 * 
 * 
 */

package object.thing;

/**
 * Dopo l'aggiornamento delle specifiche, rendo il tappeto un oggetto che estende Container.
 * C'è da aggiungere che Container implementa Openable, quindi il risultato è un tappeto che si può aprire.
 * Non sarà elegantissimo, ma le specifiche lasciando intendere questo.
 * Per questo, sovrascrivo i metodi apri e chiudi e lo setto sempre aperto.
 */
public class Tappeto extends Container {
	
	/** Costruttore usato dal parser */
	public Tappeto(String code, String name) {
		super(code, name);
		this.aperto = true;
	}
	
	@Override
	public void apri() {
		
	}
	
	@Override
	public void chiudi() {
		
	}
}
