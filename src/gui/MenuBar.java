/** MenuBar.java
 * 29/giu/2012 17:25:36
 * Last edit: 29/giu/2012 17:25:36
 *  
 */

package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * La classe MenuBar gestisce il menu della finestra MainFrame.
 * È stato incluso nella classe anche il relativo actionListener.
 */
public class MenuBar extends JMenuBar 
						implements ActionListener {
	
	private JMenu file;
	private JMenu about;
	
	private JMenuItem save;
	private JMenuItem load;
	private JMenuItem clear;
	private JMenuItem info;
	
	public MenuBar() {
		super();
		
		file = new JMenu("File");
		
		save = new JMenuItem("Salva");
		save.addActionListener(this);
		file.add(save);
		
		load = new JMenuItem("Carica");
		load.addActionListener(this);
		file.add(load);
		
		clear = new JMenuItem("Perdi la memoria");
		clear.addActionListener(this);
		file.add(clear);
		
		about = new JMenu("About");
		
		info = new JMenuItem("Info");
		info.addActionListener(this);
		about.add(info);
		
		this.add(file);
		this.add(about);
	}

	@Override
	/** Questo è il listener per la menuBar */
	public void actionPerformed(ActionEvent arg0) {		
		
		/** Per salvare prende la lista di locazioni e la salva */
		if (arg0.getSource().equals(save)) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.showSaveDialog(null);
			String path = fc.getSelectedFile().getPath();		       

			try {
				// Se l'utente non ha inserito alcun percorso salvo nella home:
		        if (path == null || path.equals("")) {
		        	io.IO.saveToFile(Singleton.getLocations());
		        } else {
				io.IO.saveToFile(Singleton.getLocations(), path);
		        }
			} catch (IOException e) {
				System.out.println("Impossibile salvare il file, assicurarsi di aver inserito un percorso esisnte e di avere i permessi di scrittura.");
			}
			Singleton.getFrame().getBottomPanel().updateConsole("La partita è stata salvata.");
		}
		
		// Per il caricamento ricostruisco da capo il frame
		else if (arg0.getSource().equals(load)) {
			String path = MainFrame.loadPath();
			
			Singleton.getFrame().setVisible(false);
			Singleton.setFrame(new MainFrame(MainFrame.startFromLoad(path)));
			Singleton.getFrame().setVisible(true);
		}
		
		else if (arg0.getSource().equals(clear)) {
			Singleton.getPlayer().perdiMemoria();
			Singleton.getFrame().getBottomPanel().updateConsole("Il giocatore ha perso la memoria.");
		}
		
		else if (arg0.getSource().equals(info)) {
			JOptionPane.showMessageDialog(Singleton.getFrame(),
			    "Progetto di Metodologie di programmazione, a.a 2012.\nAdriano Di Luzio.",
			    "About", JOptionPane.INFORMATION_MESSAGE, 
			    new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("icon.png"))));
		}
		
		Singleton.getFrame().validate();
	}
}
