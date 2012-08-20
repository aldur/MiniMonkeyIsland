
package gui;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import object.GameObject;
import object.actor.Actor;
import object.thing.ClosedObjectException;
import object.thing.Thing;
import object.thing.Through;
import object.thing.Utils;

/** Il pannello superiore, contentente le comboBox */
public class TopPanel extends JPanel {
	
	private JComboBox<object.thing.Utils> inventoryCombo;
	private JComboBox<object.thing.Thing> objectCombo;
	private JComboBox<object.actor.Actor> actorCombo;
	private JComboBox<object.thing.Through> throughCombo;
	
	private ComboListener comboListener;
	
	private JComboBox<object.GameObject> partnerCombo;
	private JLabel partnerLabel;
	private PartnerListener partnerListener;
	
	public TopPanel() {
		super();
		
		//Le combo BOX		
		JLabel inventoryLabel = new JLabel("Inventario:");
		this.add(inventoryLabel);
				
		inventoryCombo = new JComboBox<Utils>();
		this.add(inventoryCombo);
		
		JLabel objectLabel = new JLabel("Oggetti:");
		this.add(objectLabel);
		
		objectCombo = new JComboBox<Thing>();
		this.add(objectCombo);
		
		JLabel actorLabel = new JLabel("Personaggi:");
		this.add(actorLabel);
		
		actorCombo = new JComboBox<Actor>();
		this.add(actorCombo);
		
		JLabel throughLabel = new JLabel("Aperture:");
		this.add(throughLabel);
		
		throughCombo = new JComboBox<Through>();
		this.add(throughCombo);
		
		partnerLabel = new JLabel("Partner:");
		this.add(partnerLabel);
		partnerLabel.setVisible(false);
		
		partnerCombo = new JComboBox<GameObject>();
		this.add(partnerCombo);
		partnerCombo.setVisible(false);
		partnerListener = new PartnerListener();
	}

	/** Questo metodo legge la lista di locazioni e aggiunge man mano gli oggetti. 
	 * NB, il primo oggetto di ogni combobox è un segnaposto, perchè per un BUG di Java
	 * una comboBox con un solo elemento non lancia alcun evento.
	 * L'evento viene lanciato solo quando la selezione viene effettivamente cambiata.
	 * Per ulteriori riferimenti: http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4135029
	 * Per il segnaposto scelgo un oggetto di tipo locazioni, perchè il più semplice e generico.
	 * So che la soluzione non è la migliore, ma le mie conoscenze di Grafica si fermano qui. */
	public void fillFromLocation(object.location.Location in) {
				
		/* Prima di tutto svuoto le comboBox */
		objectCombo.removeAllItems();
		actorCombo.removeAllItems();
		throughCombo.removeAllItems();
		
		/* Evito di aggiungere il personaggio giocante alla lista dei personaggi: */
		object.actor.Actor player = null;
		/* Trovo il giocatore umano. */
		for (object.actor.Actor p : in.getActorList()) {
			if (p instanceof object.actor.HumanPlayer && p.getCode().equals("PERS_1"))
				player = (object.actor.HumanPlayer)p;
		}
		
		Dimension defaultDimension = new Dimension(100, 30);
		
		objectCombo.setEnabled(true);
		objectCombo.setPreferredSize(null);
		
		for (object.thing.Thing o : in.getObjectList()) {
			objectCombo.addItem(o);
		}
		
		if (objectCombo.getItemCount() == 0) {
			objectCombo.setEnabled(false);
			objectCombo.setPreferredSize(defaultDimension);
		}
		
		actorCombo.setEnabled(true);
		objectCombo.setPreferredSize(null);
		
		for (object.actor.Actor o : in.getActorList()) {
			if (!o.equals(player))
				actorCombo.addItem(o);
		}
		
		if (actorCombo.getItemCount() == 0) {
			actorCombo.setEnabled(false);
			actorCombo.setPreferredSize(defaultDimension);
		}
		
		throughCombo.setEnabled(true);
		objectCombo.setPreferredSize(null);
		
		for (object.thing.Through o : in.getThroughList()) {
			throughCombo.addItem(o);
		}
		
		if (throughCombo.getItemCount() == 0) {
			throughCombo.setEnabled(false);
			throughCombo.setPreferredSize(defaultDimension);
		}
		
		Singleton.getFrame().pack();
	}
	
	/** Questo metodo, prende un giocatore in input e riempie la comboBox inventario. 
	 * 
	 * @param p
	 */
	public void fillFromHumanPlayer(object.actor.HumanPlayer p) {
		
		inventoryCombo.removeAllItems();
		
		inventoryCombo.setEnabled(true);
		inventoryCombo.setPreferredSize(null);
		
		for (object.thing.Utils u : p.getInventory()) {
			inventoryCombo.addItem(u);
		}
		
		if (inventoryCombo.getItemCount() == 0) { 
			inventoryCombo.setEnabled(false);
			inventoryCombo.setPreferredSize(new Dimension(100, 30));
		}
		
		Singleton.getFrame().pack();
	}
	
	/** Questo metodo serve per riempire la comboBox partner partendo da un oggetti di tipo slavable. */
	public void fillFromObject(object.thing.Slavable<?> o, object.location.Location in, object.actor.Actor p) {

		// Rimuovo i listener precedenti
		removePartnerListener();
		
		/* Rimuovo tutti gli elementi */
		partnerCombo.removeAllItems();
		
		/* Aggiungo il segnaposto */
		object.location.Location foo = new object.location.Location("Clicka per selezionare il partner", " ");
		partnerCombo.addItem(foo);
		
		for (object.thing.Thing m : in.getObjectList()) {
			if (m instanceof object.thing.Master)
				partnerCombo.addItem(m);
		}
		
		for (object.thing.Utils u : p.getInventory()) {
			if (u instanceof object.thing.Master)
				partnerCombo.addItem(u);
		}

		showPartner();
		
		partnerCombo.addActionListener(partnerListener);
	}
	
	/** Questo metodo serve per riempire la ComboBox partner, partendo da un oggetto di tipo Master */
	public void fillFromObject(object.thing.Master o, object.location.Location in, object.actor.Actor p) {
		
		removePartnerListener();

		/* Rimuovo tutti gli elementi */
		partnerCombo.removeAllItems();
		
		/* Aggiungo il segnaposto */
		object.location.Location foo = new object.location.Location("Clicka per selezionare il partner", " ");
		partnerCombo.addItem(foo);
		
		for (object.thing.Thing m : in.getObjectList()) {
			if (m instanceof object.thing.Slavable<?>)
				partnerCombo.addItem(m);
		}
		
		for (object.thing.Utils u : p.getInventory()) {
			if (u instanceof object.thing.Slavable<?>)
				partnerCombo.addItem(u);
		}
		
		showPartner();
		
		partnerCombo.addActionListener(partnerListener);
	}

	/** Questo metodo serve per riempire la comboBox partner partendo da un oggetto Utils.
	 * Questo è il caso in cui si vuole dare un oggetto del proprio inventario a un altro giocatore.
	 * @param o
	 * @param in
	 * @param p
	 */
	public void fillFromObject(object.thing.Utils o, object.location.Location in, object.actor.Actor p) {
		
		removePartnerListener();

		/* Rimuovo tutti gli elementi */
		partnerCombo.removeAllItems();
		
		/* Aggiungo il segnaposto */
		object.location.Location foo = new object.location.Location("Clicka per selezionare il partner", " ");
		partnerCombo.addItem(foo);
		
		for (object.actor.Actor a : in.getActorList()) {
			if (!a.equals(p))
				partnerCombo.addItem(a);
		}
				
		showPartner();
		
		partnerCombo.addActionListener(partnerListener);
	}
	
	/** Questo metodo riempie la comboBox partner partendo da un oggetto di tipo chiave. */
	public void fillFromObject(object.thing.Chiave k, object.location.Location in, object.actor.Actor p) {
		
		removePartnerListener();

		/* Rimuovo tutti gli elementi */
		partnerCombo.removeAllItems();
		
		/* Aggiungo il segnaposto */
		object.location.Location foo = new object.location.Location("Clicka per selezionare il partner", " ");
		partnerCombo.addItem(foo);
		
		for (object.thing.Through t : in.getThroughList()) {
			partnerCombo.addItem(t);
		}
		
		showPartner();
		
		partnerCombo.addActionListener(partnerListener);		
	}
	
	/** Questo metodo serve a riempire la comboBox partner partendo da un oggetto di tipo Container.
	 * La comboBox quindi avrà tutti gli elementi contenuti.
	 * Da notare che in questo caso si aggiunge il listener generico per le comboBox, in quanto si vuole poter agire sugli oggetti
	 * in modo "classico".
	 *  
	 * @param o
	 */
	public void fillFromContainer(object.thing.Container o) {
		
		removePartnerListener();

		/* Rimuovo tutti gli elementi */
		partnerCombo.removeAllItems();
		
		/* Aggiungo il segnaposto */
		object.location.Location foo = new object.location.Location("Clicka per selezionare il partner", " ");
		partnerCombo.addItem(foo);
		
		try {
			for (object.thing.Utils u : o.getContent()) {
				partnerCombo.addItem(u);
			}
		} catch (ClosedObjectException e) {
			// Qui non arriviamo mai perchè il controllo viene effettuato prima.
			assert false;
		}
		
		showPartner();
		
		partnerCombo.addActionListener(comboListener);
	}
	
	/** Questo metodo aggiunge il listener alle combobox standard, è stato aggiunto qui in modo
	 * da poter aggiungere gli oggetti alle Combobox senza generare fastidiosi eventi. */
	public void addComboListener(ComboListener l) {
		this.comboListener = l;
		inventoryCombo.addActionListener(l);
		objectCombo.addActionListener(l);
		actorCombo.addActionListener(l);
		throughCombo.addActionListener(l);
	}
	
	/** Questo metodo rimuove ogni listener dalla comboBox partner. */
	public void removePartnerListener() {
		partnerCombo.removeActionListener(comboListener);
		partnerCombo.removeActionListener(partnerCombo);
	}
	
	public void resetCombo() {
		inventoryCombo.setSelectedItem(null);
		objectCombo.setSelectedItem(null);
		objectCombo.setSelectedItem(null);
		actorCombo.setSelectedItem(null);
		throughCombo.setSelectedItem(null);
		partnerCombo.setSelectedItem(null);
	}
	
	public void hidePartner() {
		partnerLabel.setVisible(false);
		partnerCombo.setVisible(false);
		Singleton.getFrame().pack();
	}
	
	public void showPartner() {
		partnerLabel.setVisible(true);
		partnerCombo.setVisible(true);
		Singleton.getFrame().pack();
	}
}
