/** Through.java
 * 09/mag/2012 17:28:02
 * Last edit: 09/mag/2012 17:28:02
 * 
 * Questo e' il file della superclasse Oggetto (fisico) che porta da un'altra parte. Porte, finestre, ecc.
 * Implementa "Apribile", per ovvii motivi, ed usabile.
 */

package object.thing;

/**
 * Ogni oggetto di tipo Through, oltre ai soliti attributi auto-escplicativi, ha due attributi final "link", che marcano dove porta quel passaggio.
 * Per comodità, e per non abusare delle eccezioni, ho deciso di non lanciarne una se si prova ad aprire o chiudere una porta già in quello stato.
 * Ma ne viene comunque lanciata una se si prova ad entrare in una porta chiusa.
 * Una grossa modifica all'implementazione è che ogni oggetto through collega due differenti locazioni.
 * Inizialmente non era stato implementato in questo modo, ma poi con il parser, si è reso necessario.
 * La porta implementa Master per la chiave.
 */
public abstract class Through extends Thing
								implements Openable, Master {
	
	protected boolean opened;
	protected boolean locked;
	protected boolean usato;
	
	protected String keyCode;
	protected Chiave key;
	
	protected final object.location.Location linkA;
	protected final object.location.Location linkB;
	
	/** Questo è il costruttore più semplice, oltre ai parametri di default prende in input le due locazioni che l'oggetto collegherà. */
	public Through(String code, String name, object.location.Location linkA, object.location.Location linkB) {
		this (code, name, linkA, linkB, false, false, null);
	}
	
	/** Questo costruttore invece, aggiunge alle locazioni lo stato dell'oggetto, e se esso è chiuso oppure no. */
	public Through(String code, String name, object.location.Location linkA, object.location.Location linkB, boolean opened, boolean locked) {
		this(code, name, linkA, linkB, opened, locked, null);
	}
	
	/** Infine questo costruttore, oltre alle locazioni, lo stato e la chiusura, aggiunge un codice univoco della chiave. */
	public Through(String code, String name, object.location.Location linkA, object.location.Location linkB, boolean opened, boolean locked, String keyCode) {
		super(code, name);
		this.linkA = linkA;
		this.linkB = linkB;
		
		this.opened = opened;
		this.locked = locked;
		this.keyCode = keyCode;
		this.usato = false;
	}
	
	public void apri() {
		if (!locked)
			opened = true;
	}
	
	public void chiudi(){
		opened = false;
	}
	
	public void lock() {
		locked = true;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	/** Questo metodo ritorna vero se la chiave è quella destinata ad aprire questa porta. */
	public boolean getKey(EventCreator in) {
		
		if (in.getCode().equals(keyCode)) {
			locked = false;
		}
		
		return !locked;
	}
	
	/** Questo metodo prende in input una chiave, e nel caso corrisponda, sblocca il Through */
	public boolean getKey(Chiave in) {
		if (in.getCode().equals(keyCode))
			locked = false;
		
		return !locked;
	}
	
	public void setKey(String in) {
		this.keyCode = in;
	}
	
	public boolean getState() {
		return opened;
	}
	
	public boolean usato() {
		return usato;
	}
	
	public object.location.Location getLocationA() {
		return linkA;
	}
	
	public object.location.Location getLocationB() {
		return linkB;
	}
	
	@Override
	/** Il metodo esamina per un Through specifica quali locazioni collega. */
	public String esamina() {
		examinated = true;
		return this.getClass().getSimpleName() + ": " + getName() + " che collega " + linkA.getName() + "/" + linkB.getName();
	}
	
	/** Questo metodo serve soltato per la GUI */
	public String linkString() {
		return linkA.getName() + "/" + linkB.getName();
	}

}
