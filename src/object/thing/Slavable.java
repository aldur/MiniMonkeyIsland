/** Slavable.java
 * 10/mag/2012 17:37:59
 * Last edit: 10/mag/2012 17:37:59
 * 
 * Questa e' l'interfaccia che implementeranno gli oggetti utili che non possono funzionare da soli.
 */

package object.thing;

/** 
 * Questa è l'interfaccia che caratterizza gli oggetti che non possono funzionare da soli, ad esempio un Dischetto per Computer.
 * 
 * Implementando l'interfaccia con i Generics, posso collegare oggetti di vario tipo:
 * 	- la leva verrà usata con con un oggetto di tipo through;
 * 	- il gettone con un oggetto di tipo cabina
 * 	ecc.
 */
public interface Slavable <T>{
	/** Questo metodo specifica con quale oggetto va usato l'implementatore dell'interfaccia. */
	public void usa(T with) throws AlreadyInStateException, IncompatibleThingException, OffObjectException, WrongCodeException;
}
