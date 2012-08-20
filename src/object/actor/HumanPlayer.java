/** HumanPlayer.java
 * 22/mag/2012 19:17:09
 * Last edit: 22/mag/2012 19:17:09
 * 
 * 
 */

package object.actor;

import java.util.ArrayList;

import object.location.Location;

/**
 * Questa è la classe per il giocatore umano, il cui codice di default, se omesso, è "HUMAN".
 * Questa classe è stata implementata solo per una ferrea separazione del giocatore umano dagli NPG.
 * Inoltre, risolve l'attributo abstract della superclasse Actor.
 */
public class HumanPlayer extends Actor {

	public HumanPlayer(String code, String name, Location currentLocation,
			ArrayList<Event> eventList) {
		super(code, name, currentLocation, eventList);
	}
	
	/** Questo è il costruttore usato dal parser */
	public HumanPlayer(String code, String name, Location currentLocation) {
		super(code, name, currentLocation);
	}
	
	public HumanPlayer(String name, Location currentLocation) {
		super("HUMAN", name, currentLocation);
	}
	
}
