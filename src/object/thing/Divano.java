/** Divano.java
 * 11/mag/2012 19:10:25
 * Last edit: 11/mag/2012 19:10:25
 * 
 * 
 */

package object.thing;

/**
 * 
 * Questo e' un generico oggetto divano. Cosa deve fare? O.o
 * Semplicisticamente, prende in riferimento un attore e lo si fa sedere o alzare.
 * Per il momento funziona così: il metodo faiSedere prende in input un Attore, e lo setta in seduto. Per cui,
 * ci si aspetta che l'attore implementi un metodo contentente qualcosa del tipo: (Divano).faiSedere(this)
 * 
 * Attualmente, non è stato implementato, ed il divano viene trattato come un semplice oggetto di arredamento.
 */
public class Divano extends Furniture {
	protected object.actor.Actor seduto;	
	
	public Divano(String code, String name) {
		this(code, name, null);
	}
	
	public Divano(String code, String name, object.actor.Actor seduto) {
		super(code, name);
		this.seduto = seduto;
	}
	
	public void faiSedere(object.actor.Actor in) throws AlreadyInStateException {
		if (seduto != null)
			throw new AlreadyInStateException();
		
		seduto = in;
	}
	
	public void faiAlzare() throws AlreadyInStateException {
		if (seduto == null)
			throw new AlreadyInStateException();
					
		seduto = null;
	}
}
