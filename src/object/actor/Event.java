/** Event.java
 * 24/mag/2012 17:54:23
 * Last edit: 24/mag/2012 17:54:23
 * 
 * 
 */

package object.actor;

/**
 * 
 * Questa è la classe di costruzione e gestione degli Eventi.
 * Un evento conserva semplicemente soggetto, predicato, complemento oggetto, ecc.
 * Più specificatamente, ogni oggetto ha:
 * 	un tipo
 *	un soggetto
 * 	un predicato
 * 	un complemento oggetto
 * 	ed eventualmente un personaggio "partner".
 */
public class Event extends object.GameObject {
	
	/** L'enum predicate può essere del tipo:
	 * <p>
	 * Da
	 * Posside
	 * Si trova in
	 * Esce da
	 * Domanda
	 * Parla
	 */
	public enum Predicate {DA, POSSIEDE, SI_TROVA_IN, ESCE_DA, DOMANDA, PARLA};
	
	/** L'enum EventType può essere del tipo:
	 * <p>
	 * Locazione
	 * Interazione
	 * Possesso
	 * Domanda
	 */
	public enum EventType {LOCATION, INTERACTION, POSSESSION, QUESTION};

	/* Attributi di stato. */
	private Actor subject;
	private Predicate verb;
	private object.GameObject cobject;
	private Actor atobject;
	
	private EventType type;
	
	/** Questo è il più semplice costruttore di evento, semplicemente un soggetto, un verbo, e un complemento oggetto. 
	 * Esempio:
	 * a Possiede o */
	public Event(Actor subject, Predicate verb, object.thing.Thing cobject) {
		super(createEventCode(EventType.POSSESSION, subject, verb, cobject, null), createEventCode(EventType.POSSESSION, subject, verb, cobject, null));
		this.subject = subject;
		this.verb = verb;
		this.cobject = cobject;
		this.type = EventType.POSSESSION;
		atobject = null;
	}
	
	/** Questo costruttore invece, si riferisce agli eventi del tipo:
	 * 	a SI_TROVA_IN b, oppure
	 * a ESCE_DA b */
	public Event(Actor subject, Predicate verb, object.location.Location cobject) {
		super(createEventCode(EventType.LOCATION, subject, verb, cobject, null), createEventCode(EventType.LOCATION, subject, verb, cobject, null));
		this.subject = subject;
		this.verb = verb;
		this.cobject = cobject;
		this.type = EventType.LOCATION;
		atobject = null;
	}	
	
	/** Questo costruttore invece riguarda le interazioni tra i personaggi, ad esempio:
	 * a DA o1 A b
	 */
	public Event(Actor subject, Predicate verb, object.thing.Thing cobject, Actor atobject) {
		super(createEventCode(EventType.INTERACTION, subject, verb, cobject, atobject), createEventCode(EventType.INTERACTION, subject, verb, cobject, atobject));
		this.type = EventType.INTERACTION;
		this.atobject = atobject;
	}
	
	/** Questo costruttore riguarda le interazioni tra personaggi:
	 * a domanda cobject a b
	 */
	public Event(Actor subject, Predicate verb, Event cobject, Actor atobject) {
		super(createEventCode(EventType.QUESTION, subject, verb, cobject, atobject), createEventCode(EventType.QUESTION, subject, verb, cobject, atobject));
		this.subject = subject;
		this.verb = verb;
		this.cobject = cobject;
		this.type = EventType.QUESTION;
		this.atobject = atobject;
	}
	
	/** Questo costruttore riguarda il parlare semplicemente:
	 * a parla (con) b
	 * @param subject
	 * @param verb
	 * @param atobject
	 */
	public Event(Actor subject, Predicate verb, Actor atobject) {
		super(createEventCode(EventType.QUESTION, subject, verb, null, atobject),createEventCode(EventType.QUESTION, subject, verb, null, atobject));
		this.subject = subject;
		this.verb = verb;
		this.type = EventType.QUESTION;
		this.atobject = atobject;
	}
	
	/** Questo metodo ha il compito di fornire ad ogni Evento un codice univoco, in modo da poter tratteri gli eventi come oggetti di gioco.
	 * In realta' univoco è una parola forte: tale codice sarà associato alle componenenti dell'evento. Quindi a parita' di componenti, anche il codice evento sarà lo stesso. */
	public static String createEventCode(EventType type, Actor subject, Predicate verb, object.GameObject cobject, Actor atobject) {
		String sr = "EV_";
		sr += type.toString() + "_" + subject.getCode() + "_" + verb.toString();
		
		if (cobject != null) {
			sr += "_" + cobject.toString();
		}
		
		if (atobject != null) {
			sr += "_" + atobject.toString();
		}
		
		return sr;
	}	
	
	public Actor getSubject() {
		return subject;
	}
	
	public Predicate getVerb() {
		return verb;
	}
	
	public object.GameObject getCobject() {
		return cobject;
	}
	
	public EventType getType() {
		return type;
	}
	
	public Actor getAtobject() {
		return atobject;
	}
	
	@Override
	/** Il metodo toString degli eventi ritorna, con una grammatica a dir poco stilizzata, la descrizione dell'evento. */
	public String toString() {
		String s;
		
		if (this.type == EventType.POSSESSION || this.type == EventType.LOCATION ) {
			s = subject.toString() + " " + verb.toString() + " " + cobject.toString(); 
		}
		
		else if (this.type == EventType.QUESTION && this.cobject == null) {
			s = subject.toString() + " " + verb.toString() + " " + atobject.toString();
		}
		
		else {
			s = subject.toString() + " " + verb.toString() + " " + cobject.toString() + " " + atobject.toString();
		}
		
		return s;
	}
}
