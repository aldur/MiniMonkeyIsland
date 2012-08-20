/** GameObject.java
 * 09/mag/2012 14:29:20
 * Last edit: 09/mag/2012 14:29:20
 * 
 * Questo e' il file della superclasse oggetto di gioco.
 */

package object;

import java.io.Serializable;

/**
 * Questa è la superclasse cui ogni oggetto del gioco dovrà far capo.
 * Contiene il nome dell'oggetto ed il codice univoco, insieme ai metodi getters.
 * Sia nome che codice sono "final", in quanto non devono poter essere modificati a posteriori.
 */
public abstract class GameObject implements Serializable {
	
	/** il serialVersionUID è stato omesso per praticita', potrebbe essere facilmente implementato in una futura versione.
	private static final long serialVersionUID = 3194498504988957142L;
	*/

	/** La stringa name, rappresenta il nome "umano" dell'oggetto. È stata impostata a final perchè non è stata pensata in modo da poter essere cambiata. */ 
	protected final String name;
	
	/** La stringa code, anch'essa final, assegna un codice univoco ad ogni oggetto di gioco.
	 * In realtà, non vi è alcuna garanzia che due oggetti diversi abbiano uno stesso codice, sta allo sviluppatore del file di parsing garantirlo.
	 * Tutto ciò potrebbe essere evitato tramite l'uso di un database, ma è superfluo per i nostri scopi. */
	protected final String code;
	
	/** Il costruttore di default prende in input due stringhe, nome e codice e ritorna un oggetto di gioco. */
	public GameObject(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	/** 
	 * Metodo get per il campo nome 
	 * 
	 * @return Il nome dell'oggetto.
	*/
	public String getName() {
		return name;
	}
	
	/** Metodo get per il campo codice
	 * 
	 *  @return Il codice dell'oggetto.
	 */
	public String getCode() {
		return code;
	}
	
	/** Per default, ho scelto che il toString di un oggetto nel gioco ne ritorni il nome. */
	@Override
	public String toString() {
		return name;
	}
}
