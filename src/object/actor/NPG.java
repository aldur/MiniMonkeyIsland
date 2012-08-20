/** NPG.java
 * 23/mag/2012 17:42:09
 * Last edit: 23/mag/2012 17:42:09
 * 
 * 
 */

package object.actor;

import java.util.ArrayList;

import object.location.Location;

/**
 * Questa classe rende la classe Actor instanziabile.
 */
public class NPG extends Actor {

	public NPG(String code, String name, Location currentLocation) {
		super(code, name, currentLocation);
	}
	
	public NPG(String code, String name, Location currentLocation,
			ArrayList<Event> eventList) {
		super(code, name, currentLocation, eventList);
	}
	
}
