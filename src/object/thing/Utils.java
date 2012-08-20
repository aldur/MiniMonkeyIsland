/** Utils.java
 * 09/mag/2012 17:55:16
 * Last edit: 09/mag/2012 17:55:16
 * 
 * Questo e' il file della superclasse Oggetto Utile.
 */

package object.thing;

/**
 * Questa è la superclasse per gli oggetti utili, o per meglio dire tutti quelli che possono essere usati e messi nell'inventario.
 * 
 *	Per la definizione di oggetti utili si implementa l'interfaccia Usabile, anche se questo potrebbe creare problemi
 * con gli oggetti che non possono funzionare da soli.
 * Una possibile soluzione potrebbe essere quella di creare, negli "Slave" un attributo che indichi con cosa è associato
 * e chiamare usa() solo se tale attributo rispecchia gli standard richiesti.
 * (Risolto con le interfacce Master e Slavable)
 */
public abstract class Utils extends Thing {
	
	protected boolean stolen;
		
	public Utils(String code, String name) {
		this(code, name, null);
	}
	
	/** Questo costruttore, oltre ai valori di default, prende in input un Attore proprietario dell'oggetto. */
	public Utils(String code, String name, object.actor.Actor owner) {
		super(code, name);
		this.owner = owner;
		this.stolen = false;
	}

	public boolean isStolen() {
		return stolen;
	}
	
	/** Un oggetto in se non può sapere se è stato rubato oppure no. Saranno i chiamanti a deciderlo, 
	 * utilizzando questo metodo.
	 */
	public void setStolen(boolean in) {
		this.stolen = in;
	}
}
