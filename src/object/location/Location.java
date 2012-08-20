/** Location.java
 * 09/mag/2012 14:36:54
 * Last edit: 09/mag/2012 14:36:54
 * 
 * Questo e' il file della superclasse Luogo.
 */

package object.location;

import java.util.ArrayList;

import object.actor.Actor;
import object.actor.Event;

/**
 * Questa è la classe locazione, contiene le varie liste di oggetti, personaggi e passaggi, insieme ai metodi di aggiunta e rimozione degli stessi.
 */
public class Location extends object.GameObject {

	protected ArrayList<object.thing.Thing> objectList;
	protected ArrayList<object.actor.Actor> actorList;
	protected ArrayList<object.thing.Through> throughList;
	
	/** Questo è il costruttore base, che inizializza a null tutte le liste. Prende in input soltanto Codice e Nome.*/
	public Location(String code, String name) {
		super(code, name);
	
		/* Per il momento, mi accontento di inizializzare a zero le liste di oggetti, fornendo i metodi per aggiungerne altri. */
		objectList = new ArrayList<object.thing.Thing>();
		actorList = new ArrayList<object.actor.Actor>();
		throughList = new ArrayList<object.thing.Through>();
	}
		
	/** Questo metodo aggiunge un oggetto alla lista di oggetti della stanza
	 * 
	 * @param in
	 * @return vero se in è stato aggiunto.
	 */
	public boolean addObject(object.thing.Thing in) {
		return objectList.add(in);
	}
	
	/** Questo metodo rimuove un oggetto dalla lista di oggetti della stanza
	 * @param in
	 * @return vero se in è stato rimosso.
	 */
	public boolean removeObject(object.thing.Thing in) {
		return objectList.remove(in);
	}
	
	/** Questo metodo aggiunge un personaggio alla stanza, e crea i rispettivi eventi, aggiungendoli alla lista di tutti i personaggi già presenti nella stanza.
	 * Tale metodo verrà chiamato quando un personaggio entra in una stanza.
	 * 
	 * @param in
	 * @return vero se il personaggio è stato aggiunto.
	 */
	public boolean addActor(object.actor.Actor in) {
		Event nevent = new Event(in, Event.Predicate.SI_TROVA_IN, this);
		propagateEvent(nevent);
		
		return actorList.add(in);
	}
	
	/** Questo metodo invece viene chiamato quando un giocatore esce dalla stanza.
	 * Crea l'evento conseguente e lo rimuove dalle liste dei giocatori in stanza.
	 * 
	 * @return vero se il giocatore è stato rimosso.
	 */
	public boolean removeActor(object.actor.Actor in) {
		Event nevent = new Event(in, Event.Predicate.ESCE_DA, this);

		/* Creo il codice dell'evento creato precedentemente, quando il giocatore era entrato nella stanza */		
		/* E lo rimuovo dalle liste eventi dei giocatori nella stanza: */
		String oldevent = Event.createEventCode(Event.EventType.LOCATION, in, Event.Predicate.SI_TROVA_IN, this, null);
		
		/* E lo aggiungo nelle liste eventi di ogni giocatore nella stessa stanza. */
		propagateEvent(nevent);
		dePropagateEvent(oldevent);
		
		return actorList.remove(in);
	}
	
	/** Questo metodo aggiunge un'apertura alla stanza
	 * 
	 * @param in
	 * @return vero se è stata aggiunta
	 */
	public boolean addThrough(object.thing.Through in) {
		return 	throughList.add(in);
	}
	
	/** Questo metodo rimuove un'apertura dalla stanza
	 * 
	 * @param in
	 * @return vero se è stata rimossa
	 */
	public boolean removeThrough(object.thing.Through in) {
		return 	throughList.remove(in);
	}
	
	/** Questo metodo propaga un evento a tutti i giocatori presenti nella stanza. */
	public void propagateEvent(Event in) {
		for (Actor a : actorList) {
			a.addEvent(in);
		}
	}
	
	/** Questo metodo fa dimenticare un evento, o dePropagare, a tutti i giocatore presenti nella stanza. */
	public void dePropagateEvent(String out) {
		for (Actor a : actorList) {
			a.removeEvent(out);
		}
	}
	
	public ArrayList<object.thing.Through> getThroughList() {
		return throughList;
	}
	
	public ArrayList<object.actor.Actor> getActorList() {
		return actorList;
	}
	
	public ArrayList<object.thing.Thing> getObjectList() {
		return objectList;
	}
	
	/** Ho aggiunto questo metodo per un ulteriore controllo di sicurezza nel prendere gli oggetti, in particolare se contenuti in un container.
	 * Infatti, in quel caso il check dell'oggetto contenuto nella stanza ritorna falso, perchè è all'interno di un ulteriore oggetto. 
	 * Questo metodo quindi controlla che almeno un oggetto nella stanza contenga l'oggetto in input. 
	 */
	public boolean checkContainedObjectList(object.thing.Utils in) {
		
		for (object.thing.Thing t : objectList) {
			if (t instanceof object.thing.Container) {
				if (((object.thing.Container) t).contains(in))
					return true;
			}
		}
		
		return false;
	}
}
