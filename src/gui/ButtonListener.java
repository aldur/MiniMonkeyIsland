/** ButtonListener.java
 * 19/giu/2012 15:45:31
 * Last edit: 19/giu/2012 15:45:31
 *  
 */

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import object.EndGameException;
import object.actor.Actor;
import object.thing.AlreadyInStateException;
import object.thing.ClosedObjectException;
import object.thing.Container;
import object.thing.MissingSlaveException;
import object.thing.Through;
import object.thing.UnusableThingException;
import object.thing.Utils;
import object.thing.WrongCodeException;

/**
 * Questa è la classe listener per i bottoni, funziona su base switch ed enum.
 * A seconda del bottone premuto chiama i metodi necessari.
 */
public class ButtonListener implements ActionListener{

	private MainFrame frame;
	private object.GameObject currentObject;
	
	/** Questo enum aiuta a capire da quale pulsante proviene l'evento */
	public enum ButtonType {Esamina, Usa, Mostra_Contenuto, Leggi, Parla, Domanda, 
		Usa_Con, Apri_Chiudi, Accendi_Spegni, Entra, Dai, Prendi };
		
	/* Creo la variabile di riferimento e la setto ad esamina di default */
	private ButtonType type = ButtonType.Esamina;
	
	public ButtonListener() {
		this.frame = Singleton.getFrame();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton t = (JButton) arg0.getSource();
		
		currentObject = frame.getCurrentObject();
		/* Aggiungo questo controllo solo per assicurarmi di non lavorare su NULL, o sul segnaposto. */
		if (currentObject == null) return;
		
		type = ButtonType.valueOf(t.getText().replaceAll(" ", "_").replaceAll("/", "_"));

		/* Mi è comoda per aggiornare la conosole */
		String s;
		boolean state;
		
		/* Ora, so il bottone che è stato premuto e l'oggetto a cui si riferisce, quindi a seconda dei casi
		 * sbrigo le pratiche. */
		
		switch (type) {
		
		case Esamina:
			frame.getBottomPanel().updateConsole(((object.thing.Thing)currentObject).esamina());
			break;
		
		case Usa:
			/* Prima il caso di pillola e telefono */
			if (currentObject instanceof object.thing.Teleporter) {
				frame.getPlayer().usa((object.thing.Teleporter)currentObject);

				frame.updateTitle();
				frame.getTopPanel().fillFromLocation(frame.getPlayer().getCurrentLocation());
				frame.getTopPanel().fillFromHumanPlayer(frame.getPlayer());
				
				frame.getBottomPanel().updateConsole("Sei stato teletrasportato con successo.");
			}
			/* Poi quello della cabina telefonica */
			else if (currentObject instanceof object.thing.CabinaTelefonica) {
				try {
					((object.thing.CabinaTelefonica)currentObject).usa(frame.getPlayer());
					frame.updateTitle();
					frame.getTopPanel().fillFromLocation(frame.getPlayer().getCurrentLocation());
					frame.getTopPanel().fillFromHumanPlayer(frame.getPlayer());
				} catch (MissingSlaveException e) {
					frame.getBottomPanel().updateConsole("Non puoi chiamare senza aver prima inserito un gettone!");
				}
			}
			/* Quello dei creatori di eventi */
			else if (currentObject instanceof object.thing.EventCreator && currentObject instanceof object.thing.Usable) {
				((object.thing.Usable)currentObject).usa();
				frame.getBottomPanel().updateConsole("È successo qualcosa da qualche parte.");
			}
			/* Poi tutti gli altri */
			else  {
				((object.thing.Usable)currentObject).usa();
				frame.getBottomPanel().updateConsole("L'oggetto è stato usato correttamente.");
			}
			
			break;
			
		case Mostra_Contenuto:
			s = ((object.thing.Container)currentObject).esamina();
			
			if (!s.contains("vuoto")) {
				s += "\nPer interagire con gli oggetti, usa la ComboBox partner.";
			}
			
			frame.getBottomPanel().updateConsole(s);
			
			frame.getTopPanel().fillFromContainer((Container) currentObject);
			break;
			
		case Leggi:
			s = ((object.thing.Readable)currentObject).leggi();
			frame.getBottomPanel().updateConsole(s);
			frame.getBottomPanel().updateInfo(currentObject);
			break;
			
		case Parla:
			object.actor.Event ev = Singleton.getPlayer().parlaCon((Actor) currentObject);
			frame.getBottomPanel().updateConsole(ev.toString());
			break;
			
		case Domanda:
			object.actor.Event in = Singleton.getPlayer().lastEvent();
			object.actor.Event qev = Singleton.getPlayer().domanda((Actor) currentObject, in);
			
			if (qev == null)
				frame.getBottomPanel().updateConsole("Il giocatore non sa nulla al riguardo.");
			
			else
				frame.getBottomPanel().updateConsole(qev.toString());
			break;
			
		case Usa_Con:
			if (currentObject instanceof object.thing.Chiave) {
				frame.getTopPanel().fillFromObject((object.thing.Chiave) currentObject, Singleton.getPlayer().getCurrentLocation(), Singleton.getPlayer());
			}
			else if (currentObject instanceof object.thing.Slavable<?>)
				frame.getTopPanel().fillFromObject((object.thing.Slavable<?>)currentObject, Singleton.getPlayer().getCurrentLocation(), Singleton.getPlayer());
			else if (currentObject instanceof object.thing.Master)
				frame.getTopPanel().fillFromObject((object.thing.Master)currentObject, Singleton.getPlayer().getCurrentLocation(), Singleton.getPlayer());
			
			frame.getBottomPanel().updateConsole("Seleziona dall'ultimo menù a tendina il partner per l'azione.");
			break;
			
		case Apri_Chiudi:
			state = ((object.thing.Openable)currentObject).getState();
			
			if (state) {
				((object.thing.Openable)currentObject).chiudi();
				frame.getBottomPanel().updateConsole(currentObject.getName() + " è stato chiuso.");
			}
			
			else {
				try {
					if (currentObject instanceof object.thing.Cassaforte) {
						String code = JOptionPane.showInputDialog(frame, "Inserisci la combinazione per la cassaforte.");
						((object.thing.Cassaforte) currentObject).setInputCode(code);
					}
					((object.thing.Openable)currentObject).apri();		
					frame.getBottomPanel().updateConsole(currentObject.getName() + " è stato aperto.");
				}			
				catch (WrongCodeException e)  {
					frame.getBottomPanel().updateConsole("Il codice inserito non è valido.");
				}
			}		
			frame.getBottomPanel().updateInfo(currentObject);
			break;
			
		case Accendi_Spegni:
			/* Al momento implementativo, le eccezioni erano state aggiunte per un mero scopo didattico.
			 * In questo caso però, risultano veramente scomode, per cui si è preferito utilizzare la struttura condizionale.
			 */
			try {
				/* Se acceso: */
				if (((object.thing.Turnable)currentObject).getState()) {
					((object.thing.Turnable)currentObject).turnOff();
					frame.getBottomPanel().updateConsole(currentObject.getName() + " è stato spento.");
				}
				else {
					((object.thing.Turnable)currentObject).turnOn();
					frame.getBottomPanel().updateConsole(currentObject.getName() + " è stato acceso.");
				}
			} catch (AlreadyInStateException e) {
				System.out.println("Questo non doveva succedere.");
				assert false;
			}

			frame.getBottomPanel().updateInfo(currentObject);
			break;
			
		case Entra:
			try {
				frame.getPlayer().entra((Through) currentObject);
				frame.updateTitle();
				
				/* Ed ecco la clausola di fine gioco. */
				if (frame.getPlayer().getCurrentLocation().getName().equals("Fine")) {
					throw new object.EndGameException();
				}
				
				frame.getTopPanel().fillFromLocation(frame.getPlayer().getCurrentLocation());
			} catch (ClosedObjectException e) {
				frame.getBottomPanel().updateConsole(currentObject.getName() + " è chiuso, non puoi entrarci!");
			} catch (EndGameException e) {
				JOptionPane.showMessageDialog(frame, "Congratulazioni, hai completato il gioco!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			}
			break;
			
		case Dai:
			frame.getTopPanel().fillFromObject((object.thing.Utils)currentObject, frame.getPlayer().getCurrentLocation(), frame.getPlayer());
			frame.getBottomPanel().updateConsole("Seleziona dall'ultimo menù a tendina il partner per l'azione.");
			
			break;
			
		case Prendi:
			try {
				frame.getPlayer().prendi((Utils) currentObject);
				frame.getBottomPanel().updateConsole(currentObject.getName() + " è stato messo nell'inventario.");
				frame.getTopPanel().fillFromLocation(frame.getPlayer().getCurrentLocation());
				frame.getTopPanel().fillFromHumanPlayer(frame.getPlayer());
			} catch (UnusableThingException e) {
				// Se arriviamo qui, c'è qualcosa che non va!
				assert false;		
				// Stampo la traccia solo per visibilità
				e.printStackTrace();
			}
			break;

		default:
			frame.getBottomPanel().updateConsole(currentObject.toString());
			break;
		}
		
		frame.validate();
	}
}
