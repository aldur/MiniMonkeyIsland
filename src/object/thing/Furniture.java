/** Furniture.java
 * 09/mag/2012 17:50:03
 * Last edit: 09/mag/2012 17:50:03
 * 
 * Questo e' il file della superclasse oggetto di arredamento.
 */

package object.thing;

/**
 * Questa classe è solo uno specchietto per le allodole, aiuta a dividere gli oggetti Thing in tre grandi gruppi.
 * Specificatamente, si riferisce agli oggetti di arredamento.
 * Poichè non tutti gli oggetti di arredamento sono Apribili, Usabili, o Contenitori, questa classe non implementa alcuna intefaccia. 
 */
public abstract class Furniture extends Thing {
	
	/** Costruttore di default */
	public Furniture(String code, String name) {
		super(code, name);
	}
}
