
package gui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import object.thing.Utils;

/** Il pannello contentente i bottoni */
public class ButtonPanel extends JPanel {
						
	private JButton examineButton;
	private JButton usaButton;
	private JButton showContentButton;
	private JButton readButton;
	private JButton talkButton;
	private JButton askButton;
	private JButton useWithButton;
	private JButton openCloseButton;
	private JButton	 onOffButton;
	private JButton enterButton;
	private JButton giveButton;
	private JButton takeButton;
		
	public ButtonPanel() {
		super();
			
		this.setLayout(new GridLayout(5, 5, 0, 0));
		
		examineButton = new JButton("Esamina");
		examineButton.setEnabled(false);
		this.add(examineButton);
		
		usaButton = new JButton("Usa");
		usaButton.setEnabled(false);
		this.add(usaButton);
		
		showContentButton = new JButton("Mostra Contenuto");
		showContentButton.setEnabled(false);
		this.add(showContentButton);
		
		readButton = new JButton("Leggi");
		readButton.setEnabled(false);
		this.add(readButton);
		
		talkButton = new JButton("Parla");
		talkButton.setEnabled(false);
		this.add(talkButton);
		
		askButton = new JButton("Domanda");
		askButton.setEnabled(false);
		this.add(askButton);
		
		useWithButton = new JButton("Usa Con");
		useWithButton.setEnabled(false);
		this.add(useWithButton);
		
		openCloseButton = new JButton("Apri/Chiudi");
		openCloseButton.setEnabled(false);
		this.add(openCloseButton);
		
		onOffButton = new JButton("Accendi/Spegni");
		onOffButton.setEnabled(false);
		this.add(onOffButton);
		
		enterButton = new JButton("Entra");
		enterButton.setEnabled(false);
		this.add(enterButton);
		
		giveButton = new JButton("Dai");
		giveButton.setEnabled(false);
		this.add(giveButton);
		
		takeButton = new JButton("Prendi");
		takeButton.setEnabled(false);
		this.add(takeButton);
	}
	
	/** Questo metodo aggiunge il listener ad ogni bottone. */
	public void addButtonListener(ButtonListener l) {
		examineButton.addActionListener(l);
		usaButton.addActionListener(l);
		readButton.addActionListener(l);
		showContentButton.addActionListener(l);
		talkButton.addActionListener(l);
		askButton.addActionListener(l);
		openCloseButton.addActionListener(l);
		useWithButton.addActionListener(l);
		onOffButton.addActionListener(l);
		enterButton.addActionListener(l);
		giveButton.addActionListener(l);
		takeButton.addActionListener(l);
	}
	
	/** Questo metodo si occupa di abilitare/disabilitare i bottoni per i diversi tipi di oggetto. */
	public void enableButtons(object.GameObject o) {
				
		/* Li abilito/disabilito nell'ordine in cui li aggiungo. */
		
		examineButton.setEnabled(o instanceof object.thing.Thing);
		usaButton.setEnabled(o instanceof object.thing.Usable);
		showContentButton.setEnabled(o instanceof object.thing.Container);
		readButton.setEnabled(o instanceof object.thing.Readable);
		talkButton.setEnabled(o instanceof object.actor.Actor);
		askButton.setEnabled(o instanceof object.actor.Actor);
		useWithButton.setEnabled(o instanceof object.thing.Slavable<?> || o instanceof object.thing.Master);
		openCloseButton.setEnabled(o instanceof object.thing.Openable);
		onOffButton.setEnabled(o instanceof object.thing.Turnable);
		enterButton.setEnabled(o instanceof object.thing.Through);
		giveButton.setEnabled(o instanceof object.thing.Utils && 
				Singleton.getPlayer().possiede((Utils) o));
		takeButton.setEnabled(o instanceof object.thing.Utils &&
				!(Singleton.getPlayer().possiede((Utils) o)));
		
		/* Ed infine qualche caso particolare: */
		if (o instanceof object.thing.Through && !((object.thing.Through) o).isLocked()) {
			useWithButton.setEnabled(false);
		}
		
		if (o instanceof object.thing.Teleporter) {
			usaButton.setEnabled(true);
		}
		
		if (o instanceof object.thing.Through && ((object.thing.Through) o).isLocked())
			openCloseButton.setEnabled(false);
		
		if (o instanceof object.thing.CabinaTelefonica && ((object.thing.CabinaTelefonica)o).getTelefono() != null) {
			usaButton.setEnabled(true);
		}
	}
}
