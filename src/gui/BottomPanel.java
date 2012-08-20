
package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/** Il pannello di fondo */
public class BottomPanel extends JPanel {

	private InfoPanel infoPanel;
	private ButtonPanel buttonPanel;

	private ConsolePanel consolePanel;
	
	/** Il costruttore aggiunge il pannello informazioni, il pannello bottoni, e la console */
	public BottomPanel() {
		super();
		this.setLayout(new BorderLayout(0, 0));
		
		infoPanel = new InfoPanel();
		this.add(infoPanel, BorderLayout.NORTH);
		
		buttonPanel = new ButtonPanel();
		this.add(buttonPanel, BorderLayout.CENTER);
		
		consolePanel = new ConsolePanel();
		this.add(consolePanel, BorderLayout.SOUTH);
	}
	
	/** Questo metodo passa l'aggiornamento delle informazioni al pannello Info. */
	public void updateInfo(object.GameObject o) {
		infoPanel.updateInfo(o);
		infoPanel.validate();
	}

	/** Questo metodo passa l'abilitazione al pannello bottoni. */
	public void updateButton(object.GameObject o) {
		buttonPanel.enableButtons(o);
	}
	
	/** Questo metodo aggiorna il messaggio della console */
	public void updateConsole(String s) {
		consolePanel.setText(s);
	}
	
	public InfoPanel getInfoPanel() {
		return infoPanel;
	}

	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}

	public ConsolePanel getConsolePanel() {
		return consolePanel;
	}
	
	/** Questo metodo crea i listener, in particolare quello per i bottoni */
	public void createListener() {
		buttonPanel.addButtonListener(new ButtonListener());
	}
}
