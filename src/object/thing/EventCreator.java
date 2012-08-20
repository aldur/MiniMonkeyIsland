/** EventCreator.java
 * 22/mag/2012 17:46:40
 * Last edit: 22/mag/2012 17:46:40
 * 
 * 
 */

package object.thing;

/**
 * Questa e' una "finta" interfaccia. Viene usata in modo tale che solo oggetti che la implementano possono essere linkati ad una botola o un passaggio segreto.
 */
public interface EventCreator {	
	/** Il metodo getCode, peraltro già implementato dalla superclasse gameObject, per assicurare al compilatore che gli oggetti di tipo
	 * EventCreator avranno un codice unico. 
	 */
	public String getCode();
}
