/** ComboListener.java
 * 04/giu/2012 20:04:24
 * Last edit: 04/giu/2012 20:04:24
 * 
 * 
 */

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

/**
 * Il listener per le comboBox.
 * Anche qui, in base all'oggetto selezionato, viene aggiornato il riferimento nel mainFrame.
 * Sarà poi il ButtonListener ad eseguire effettivamente le azioni.
 */
public class ComboListener implements ActionListener {
	
	private MainFrame frame;
	
	public ComboListener() {
		this.frame = Singleton.getFrame();
	}
	
	@Override
	/** Questo è il listener che gestisce l'evento "Oggetto selezionato dalla combo box. */
	public void actionPerformed(ActionEvent arg0) {
		JComboBox<?> in = (JComboBox<?>) arg0.getSource();
		object.GameObject o = (object.GameObject) in.getSelectedItem();
		
		/* Se è il segnaposto non lancio alcun evento */
		if (o == null || o.getCode().equals(" ")) return;
		
		frame.setCurrentObject(o);

		frame.getBottomPanel().updateInfo(o);
		frame.getBottomPanel().updateButton(o);
		frame.validate();
	}
}