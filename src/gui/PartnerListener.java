/** PartnerListener.java
 * 29/giu/2012 21:22:11
 * Last edit: 29/giu/2012 21:22:11
 *  
 */

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

import object.GameObject;
import object.actor.Actor;
import object.thing.AlreadyInStateException;
import object.thing.IncompatibleThingException;
import object.thing.OffObjectException;
import object.thing.UnusableThingException;
import object.thing.WrongCodeException;


/**
 * Questo è il listener che si occupa, selezionato un partner, di capire se i due oggetti possono essere utilizzati insieme, e nel caso svolgere le relative azioni.
 */
public class PartnerListener implements ActionListener {

	@Override
	/** Questo metodo controlla prima di tutto quali istanze di quali oggetti sono i riferimenti,
	 * dopodichè chiama le opportune funzioni per l'utilizzo in concomitanza di due oggetti.
	 */
	public void actionPerformed(ActionEvent arg0) {

		MainFrame frame = Singleton.getFrame();
		object.actor.Actor player = Singleton.getPlayer();
		
		object.GameObject partner = (GameObject) ((JComboBox<?>)arg0.getSource()).getSelectedItem();
		object.GameObject currentObject = frame.getCurrentObject();
		
		try {
			
			/* se nel partner ci sono master: */
			if (partner instanceof object.thing.Master) {
				
				if (partner instanceof object.thing.Through && currentObject instanceof object.thing.Chiave) {
						((object.thing.Chiave) currentObject).usa((object.thing.Through)partner);
						frame.getBottomPanel().updateInfo(partner);
					}
					
					else if (partner instanceof object.thing.CabinaTelefonica && currentObject instanceof object.thing.Gettone) {
						((object.thing.Gettone) currentObject).usa((object.thing.CabinaTelefonica)partner);
					}
					else if (partner instanceof object.thing.Computer && currentObject instanceof object.thing.DischettoPerComputer) {
						((object.thing.DischettoPerComputer) currentObject).usa((object.thing.Computer)partner);
					}				
					else {
						throw new IncompatibleThingException();
					}
					
					frame.getBottomPanel().updateConsole("L'azione è stata eseguita correttamente.");
			}
		
			/* Altrimenti se ci sono Slavable */
			else if (partner instanceof object.thing.Slavable<?>) {
				
					if (partner instanceof object.thing.Chiave && currentObject instanceof object.thing.Through) {
						((object.thing.Chiave) partner).usa((object.thing.Through)currentObject);
						frame.getBottomPanel().updateInfo(currentObject);
					}	
					else if (partner instanceof object.thing.Gettone && currentObject instanceof object.thing.CabinaTelefonica) {
						((object.thing.Gettone) partner).usa((object.thing.CabinaTelefonica)currentObject);
					}
					else if (partner instanceof object.thing.DischettoPerComputer && currentObject instanceof object.thing.Computer) {
						((object.thing.DischettoPerComputer) partner).usa((object.thing.Computer)currentObject);
					}
					else {
						throw new IncompatibleThingException();
					}
					
					frame.getBottomPanel().updateConsole("L'azione è stata eseguita correttamente.");
			}
			
			/* Infine se ci sono attori */
			else if (partner instanceof object.actor.Actor) {
				player.dai((object.thing.Utils) currentObject, (Actor) partner);
				frame.getBottomPanel().updateConsole("L'azione è stata eseguita correttamente.");
				frame.getTopPanel().fillFromHumanPlayer(frame.getPlayer());
			}
		
		} catch (AlreadyInStateException e) {
			frame.getBottomPanel().updateConsole("L'azione non può essere eseguita perchè già in stato.");
		} catch (IncompatibleThingException e) {
			frame.getBottomPanel().updateConsole("I due oggetti non sono compatibili!");
		} catch (OffObjectException e) {
			frame.getBottomPanel().updateConsole("L'azione non può essere eseguita perchè l'oggetto è spento.");
		} catch (WrongCodeException e) {
			frame.getBottomPanel().updateConsole("L'azione non può essere eseguita perchè la chiave o il codice sono sbagliati.");
		} catch (UnusableThingException e) {
			frame.getBottomPanel().updateConsole("Non puoi dare quell'oggetto perchè non è nel tuo inventario.");
		}
		
		frame.getTopPanel().resetCombo();
		frame.getTopPanel().hidePartner();
	}
}
