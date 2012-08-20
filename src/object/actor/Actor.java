/** Actor.java
 * 09/mag/2012 14:33:05
 * Last edit: 09/mag/2012 14:33:05
 * 
 * Questo e' il file della superclasse Attore, o personaggio di gioco.
 */

package object.actor;


import java.util.ArrayList;

import object.actor.Event.EventType;
import object.thing.*;

/**
 * Questa è la superclasse attore, o personaggio giocante.
 * Al momento implementativo, ho scelto di non fare alcuna distinzione tra il personaggio "umano", e gli NPG, per cui entrambi i tipi sono sottoclassi di Actor.
 * Ogni personaggio deve essere in grado di eseguire una serie di azioni, come da specifiche, ed in seguito sono implementati i vari metodi necessari.
 * 
 * 	<p>
 *	● dai oggetto a personaggio
 *	● prendi oggetto
 *	● cerca in un oggetto (ad esempio in un baule)
 *	● spingi, tira, apri, chiudi, esamina, usa oggetto
 *	● parla con un personaggio, il quale rivelerà eventualmente informazioni rilevanti
 *	● domanda qualcosa a un personaggio
 *	● entra in un passaggio che porta a un’altra locazione (es. attraverso una porta aperta)
 *	● perdi la memoria
 */
public abstract class Actor extends object.GameObject {

	/** currentLocation tiene in memoria la stanza in cui si trova al momento il giocatore. */
	protected object.location.Location currentLocation;
	
	/** inventory è un ArrayList di Utils, e quindi l'inventario del giocatore. */
	protected ArrayList<object.thing.Utils> inventory;
	
	/** Eventuali informazioni "contenute" dal personaggio, non sono settate a final perchè potrebbero cambiare nel corso della partita. */
	protected Event info;
	
	/** La lista di eventi che rappresenta la "memoria" del giocatore. */
	protected ArrayList<Event> eventList;
	
	/** Questo è il costruttore che verrà usato dal parser. 
	 * Prende in input due stringe, codice e nome del giocatore, e la locazione in cui si trova al momento.
	 */
	public Actor(String code, String name, object.location.Location currentLocation) {
		this(code, name, currentLocation, new ArrayList<Event>(), null);
	}
	
	/** Questo costruttore, oltre ai campi standard, permette di aggiungere una "memoria" precostruita per il personaggio. */
	public Actor(String code, String name, object.location.Location currentLocation, 
			ArrayList<Event> eventList) {
		this(code, name, currentLocation, eventList, null);
	}
	/** Questo è il costruttore più completo, permette di aggiungere una "memoria" e un informazione "contenuta" dal personaggio.
	 * 
	 * @param code
	 * @param name
	 * @param currentLocation
	 * @param eventList
	 * @param info
	 */
	public Actor(String code, String name, object.location.Location currentLocation, 
			ArrayList<Event> eventList, Event info) {
		super(code, name);
		this.currentLocation = currentLocation;
		this.eventList = eventList;
		this.info = info;
		this.inventory = new ArrayList<object.thing.Utils>();
	}
	
	
	/* Implementazioni metodi di "azione" a seguire. */
	
	/** Questo è il metodo per prendere un oggetto.
	 * Controlla che l'oggetto sia nella stessa stanza (o sia contenuto da un oggetto della stanza), in caso affermativo lo aggiunge all'inventario e crea l'evento appropriato.
	 * In caso contrario lancia un'eccezione.
	 * 
	 * @throws UnusableThingException
	 */
	public void prendi(Utils in) throws UnusableThingException {
		
		if (!currentLocation.getObjectList().contains(in) && 
				!currentLocation.checkContainedObjectList(in)) {
			throw new UnusableThingException();
		}
					
		inventory.add(in);
		currentLocation.removeObject(in);
		
		/* Creo l'evento, e lo propago a tutti i giocatori nella stessa stanza. */
		Event nevent = new Event(this, Event.Predicate.POSSIEDE, in);
		currentLocation.propagateEvent(nevent);
	}
	
	/** Questo è il metodo per prendere un oggetto da un altro giocatore.
	 * Controlla prima di tutto che l'altro giocatore si trovi nella stessa stanza.
	 * In caso affermativo, chiama la funzione prendi con l'oggetto appropriato.
	 * In caso contrario, lancia UnusableThingException
	 * 
	 * @param out
	 * @param with
	 * @throws UnusableThingException
	 */
	public void prendiDa(Utils out, Actor with) throws UnusableThingException {
		if (!currentLocation.getActorList().contains(with)) {
			throw new UnusableThingException();		
		}
		
		this.inventory.add(out);
		
		/* Creo l'evento e lo propago a tutti gli altri. */
		Event nevent = new Event(with, Event.Predicate.DA, out, this);
		currentLocation.propagateEvent(nevent);
	}

	
	/** Questo metodo permette di lasciar cadere un'oggetto.
	 * Per semplicità implementativa, tale oggetto non viene rimesso nella stanza, ma ritornato. Sta all'utente decidere cosa farne.
	 * 
	 * In caso non si possegga l'oggetto, viene lanciata un'eccezione.
	 * @param out
	 * @return l'oggetto lasciato
	 * @throws UnusableThingException
	 */
	public Utils lascia(Utils out) throws UnusableThingException {
		if (!this.possiede(out)) {
			throw new UnusableThingException();
		}
		
		inventory.remove(out);
		
		/* Cerco il codice del vecchio evento e lo rimuovo da tutti i giocatori della stanza. */
		String oldevent = Event.createEventCode(EventType.POSSESSION, this, Event.Predicate.POSSIEDE, out, null);
		currentLocation.dePropagateEvent(oldevent);
		
		return out;
	}
	
	/** Questo metodo permette di dare un oggetto nel proprio inventario ad un altro giocatore.
	 * 
	 * Come al solito vengono controllati l'esistenza e la locazione dell'oggetto e del giocatore ricevente.
	 * In caso di fallimento viene lanciata un eccezione.
	 * 
	 * @param out
	 * @param to
	 * @throws UnusableThingException
	 */
	public void dai(Utils out, Actor to) throws UnusableThingException {
		if (!this.possiede(out) || !currentLocation.getActorList().contains(to)) {
			throw new UnusableThingException();
		}
		
		to.prendiDa(out, to);
		lascia(out);
	}
		
	/** Questo metodo è di facciata, è stato aggiunto per poter essere utilizzato dal parser senza generare eventi di volta in volta. */
	public void addInventory(Utils in) {
		inventory.add(in);
	}
	
	/** Il metodo cerca ritorna un'arrayList con il contenuto di un oggetto di tipo container.
	 * 
	 * @throws ClosedObjectException nel caso in cui l'oggetto in questione sia chiuso.
	*/
	public ArrayList<Utils> cerca(Container in) throws ClosedObjectException {
		return in.getContent();
	}
	
	/** Il metodo apri prende in input un oggetto di tipo apribile, e lo apre. */
	public void apri(Openable in) {
		try {		
			in.apri();
		}
		
		catch (WrongCodeException e) { /* Questa eccezione si verifica solo con la cassaforte, che prevede un metodo a parte.*/ }
	}
	
	/** Questo è il metodo apri per la cassaforte.
	 * A posteriori, questo metodo non è stato usato, perchè viene settato il codice per la cassaforte ed aperta in modo standard.
	 *  
	 * @param in
	 * @param password
	 * @throws WrongCodeException se la parola chiave fosse sbagliata.
	 * 
	 */
	public void apri(Cassaforte in, String password) throws WrongCodeException {
		in.setInputCode(password);
		in.apri();
	}
	
	/** Questo metodo chiude un oggetto Openable. */
	public void chiudi(Openable in) {
		in.chiudi();
	}
	
	/* A conti fatti questa funzione è inutile, usable sono rimasti solo leva e bottone. */
	/*
	public void usa(Usable in) {
		in.usa();
	}*/
	
	/** Il metodo usaCon è particolare, prende in input un oggetto Slave ed un Master, e rilancia le eccezioni al chiamante.
	 * È stata implementata in modo da rimanere più generica possibile.
	 * 
	 * @param with
	 * @param in
	 * @throws AlreadyInStateException
	 * @throws IncompatibleThingException
	 * @throws OffObjectException
	 * @throws WrongCodeException
	 */
	public void usaCon(Slavable<Master> with, Master in) 
			throws AlreadyInStateException, IncompatibleThingException, OffObjectException, WrongCodeException {
		with.usa(in);
	}
	
	/** Questo è il metodo esplicito per l'uso della pillola e del telefono.
	 * Il giocatore viene rimosso dalla locazione corrente ed aggiunto il quella di "teletrasporto". */
	public void usa(Teleporter with) {
		object.location.Location to = with.getLocation();
		
		this.currentLocation.removeActor(this);
		to.addActor(this);
		currentLocation = to;
		
		try {
			lascia((Utils) with);
		} catch (UnusableThingException e) {
			// Questo catch entra in gioco se si prova a lasciare, ad esempio, il telefono.
		}
	}
	
	/** Spingi e tira sono implementate solo perchè le richiede esplicitamente la traccia. 
	 * In realtà, per come sono stati implementati gli oggetti, sarebbe bastato un generico "usa".
	 */
	public void spingi(Bottone in) {
		in.usa();
	}
	
	/** Spingi e tira sono implementate solo perchè le richiede esplicitamente la traccia. 
	 * In realtà, per come sono stati implementati gli oggetti, sarebbe bastato un generico "usa".
	 */
	public void tira(Leva in) {
		in.usa();
	}

	
	/**
	 * Questo metodo ritorna l'eventuale informazione "contenuta" dal giocatore. 
	 * @return info
	 */
	public Event getInfo() {
		return info;
	}
	
	/** Questo metodo "inserisce" un'informazione nel giocatore. */
	public void setInfo(Event in) {
		this.info = in;
	}
	
	/** La traccia qui non è molto chiara, quindi questo potrebbe essere migliorato. 
	 * Per l'implementazione attuale, se il giocatore non sa nulla ritorna la sua posizione. 
	 * Viene propagato inoltre l'evento appropriato. */
	public Event parlaCon(Actor with) {
		/* Anche qui, creo l'evento e lo propago: */
		Event nevent = new Event(this, Event.Predicate.PARLA, with);
		currentLocation.propagateEvent(nevent);
		
		if (with.getInfo() == null)
			return new Event(with, Event.Predicate.SI_TROVA_IN, with.getCurrentLocation());
		return with.getInfo();
	}
	
	/** Questo è il metodo per domandare ad un altro giocatore di un evento.
	 * Prende in input il giocatore a cui domandare e l'evento, ed in caso affermativo ritorna l'evento contenuto dal giocatore.
	 * In questa implementazione, il più delle volte l'evento da domandare è preso come l'ultimo di quelli conosciuti.
	 * Può essere facilmente migliorato.
	 * 
	 * @param with
	 * @param in
	 * @return Event
	 */
	public Event domanda(Actor with, Event in) {		
		/* Innanzitutto creo l'evento e lo propago. */
		Event nevent = new Event(this, Event.Predicate.DOMANDA, in, with);
		currentLocation.propagateEvent(nevent);
		
		/* Poi, se il giocatore con cui si sta parlando conosce l'evento, ritorno l'informazione. */
		if (with.knows(in)) {
			return with.getInfo();
		}
		
		/* Altrimenti ritorno null. */
		return null;		
	}
	
	
	/** Questo è un metodo di controllo, serve per sapere se un giocatore conosce un determinato evento. */
	public boolean knows(Event in) {
		return eventList.contains(in);
	}
	
	/** Questo metodo invece aggiunge un evento alla lista eventi del giocatore. */
	public void addEvent(Event in) {
		eventList.add(in);
	}
	
	/** Questo metodo rimuove un evento, o meglio la prima occorrenza di quell'evento dalla lista del giocatore.
	 * 
	 * @param out
	 * @return vero se l'evento è stato effettivamente rimosso.
	 */
	public boolean removeEvent(String out) {
	/* Questo metodo confronta i codici eventi memorizzati con il codice del parametro.
	 * Se trova un'uguaglianza, elimina la prima occorrenza dell'evento e ritorna vero.	
	 */
		
		for (Event e : eventList) {
			if (e.getCode().equals(out)) {
				eventList.remove(e);
				return true;
			}
		}
		
		return false;
	}
	
	/** Questo metodo implementa il ritorno dell'ultimo evento conosciuto, una sorta di
	 * "la sai l'ultima?"
	 * @return l'ultimo evento in memoria
	 */
	public Event lastEvent() {
		return eventList.get(eventList.size()-1);
	}
	
	/** Questo metodo permette di entrare in un oggetto di tipo Through. 
	 * Dopo attente riflessioni, ho scoperto che è molto più semplice, per le azioni riguardanti il personaggio, farle fare direttamente a lui.
	 * E in effetti anche più logico, è l'attore che si sposta da una stanza all'altra.
	 * Lancia un'eccezione se la porta è chiusa, o bloccata. 
	 * @throws ClosedObjectException */
	public void entra(Through in) throws ClosedObjectException {
		if (!in.getState() || in.isLocked())
			throw new ClosedObjectException();
		
		object.location.Location lA = in.getLocationA();
		object.location.Location lB = in.getLocationB();
		
		/* Se il personaggio si trova nella prima locazione: */
		if (currentLocation.equals(lA)) {
			lA.removeActor(this);
			lB.addActor(this);
			currentLocation = lB;
		}
		
		else {
			lB.removeActor(this);
			lA.addActor(this);
			currentLocation = lA;
		}
		
	}
	
	/** Questo metodo svuota la memoria, cioè la lista eventi, del giocatore */
	public void perdiMemoria() {
		eventList.clear();
	}
	
	/** Questo metodo ritorna la locazione corrente del giocatore. */
	public object.location.Location getCurrentLocation() {
		return currentLocation;
	}
	
	/** Questo metodo esegue un check sugli oggetti nell'inventario, se ne trova alcuni il cui proprietario è diverso dal proprietario dell'inventario,
	 * li setta rubati.
	 */	
	public void checkProperty() {		
		for (Utils u : inventory ) {
			if (u.getOwner() != null && u.getOwner() != this)
				u.setStolen(true);
		}
	}
	
	/** Questa funzione è semplicemente di controllo.
	 * Ritorno vero se l'oggetto in input è contenuto nell'inventario del personaggio. 
	 * 
	 * @param in
	 * @return vero se Actor contiene in
	 */
	public boolean possiede(Utils in) {
		return this.inventory.contains(in);
	}
	
	/** Questo metodo ritorna l'inventario.
	 * 
	 * @return ArrayList<Utils>
	 */
	public ArrayList<Utils> getInventory() {
		return inventory;
	}

}
