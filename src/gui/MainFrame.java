
package gui;

import io.IO;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/** La classe MainFrame, che gestisce l'intero gioco.
 * 
 * L'avvio funziona in questo modo:
 * Si chiede all'utente se desidera iniziare una nuova partita o caricarne una precedente e in entrambi i casi gli si permette di inserire un percorso personalizzato,
 * per il file di creazione del mondo o il file di salvataggio.
 * 
 * Nel caso si inizi una nuova partita, viene letto il mondo dal file di parsing, e salvato nella locazione di default.
 * Viene quindi chiamata la funzione di caricamento dal percorso di default.
 * Se una linea del parser e' incorretta, viene stampata a schermo ed il programma esce.
 * 
 * Nel caso del caricamento invece, se si e' inserito un percorso valido, allora la partita inizia, altrimenti viene notificato l'errore all'utente e si esce.
 */
public class MainFrame extends JFrame {
	
	private TopPanel topPanel;
	private BottomPanel bottomPanel;
	
	/* L'oggetto corrente */
	private object.GameObject currentObject;
	
	/* Il mainframe deve avere necessariamente un riferimento al giocatore umano: */
	private object.actor.HumanPlayer player;
	
	/* E la lista di locazioni */
	private ArrayList<object.location.Location> locations;

	/** Questo costruttore prende in input il percorso del file da caricare */
	public MainFrame(ArrayList<object.location.Location> locations) {
		super();
		
		this.locations = locations;
		
		/* Trovo il giocatore umano. */
		for (object.location.Location l : locations) {
			for (object.actor.Actor p : l.getActorList()) {
				if (p instanceof object.actor.HumanPlayer && p.getCode().equals("PERS_1"))
					player = (object.actor.HumanPlayer)p;
			}
		}
				
		// Salvo i riferimenti nel Singleton.
		Singleton.getInstance();
		Singleton.setFrame(this);
		Singleton.setPlayer(player);
		Singleton.setLocations(locations);
		
		// Inizializzo il resto.
		initialize(locations);
	}
		
	/** Questo metodo aggiorna il titolo della finestra, sul modello:
	 * nomeGiocatore @ Stanza in cui si trova ~  Mini Monkey Island Mod ~ Adriano Di Luzio
	 */
	public void updateTitle() {
		this.setTitle(player.getName() + "@" + player.getCurrentLocation() +" ~ Mini Monkey Island Mod ~ Adriano Di Luzio");
	}
	
	/** Questo metodo prosegue l'inizializzazione dell'oggetto finestra, partendo dalla lista di locazioni riempie le JComboBox ecc. */
	public void initialize(ArrayList<object.location.Location> in) {
	
		this.setBounds(100, 100, 1024, 768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.updateTitle();
		
		this.setJMenuBar(new MenuBar());
		
		topPanel = new TopPanel();
		this.getContentPane().add(topPanel, BorderLayout.NORTH);
		
		/* A Questo punto la domanda e', riempio prima le combo, o aggiungo prima i listener? */
		topPanel.fillFromHumanPlayer(player);
		/* Per default, la prima stanza e' la locazione con indice piu' basso, o meglio quella del personaggio giocante? */
		topPanel.fillFromLocation(player.getCurrentLocation());
		topPanel.addComboListener(new ComboListener());
		
		bottomPanel = new BottomPanel();
		bottomPanel.createListener();
		this.getContentPane().add(bottomPanel, BorderLayout.CENTER);
		
		this.pack();
	}

	public TopPanel getTopPanel() {
		return topPanel;
	}

	public BottomPanel getBottomPanel() {
		return bottomPanel;
	}
	
	public object.GameObject getCurrentObject() {
		return currentObject;
	}
	
	public void setCurrentObject(object.GameObject o) {
		currentObject = o;
	}
	
	public object.actor.HumanPlayer getPlayer() {
		return player;
	}
	
	public ArrayList<object.location.Location> getLocations() {
		return locations;
	}
	
	/* --------- alcuni metodi statici per iniziare la partita -------- */
	
	/** Mostra la schermata di benvenuto e permette di scegliere se iniziare un nuovo gioco o caricarne uno precedente. */
	public static int showWelcomeScreen() {
        String [] options = { "Caricare la partita precedente", "Iniziare un nuovo gioco" };
        
        // Temporary Jframe to set icon
        JFrame window = new JFrame();
        window.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("icon.png")));

        int choice = JOptionPane.showOptionDialog(
                window,
                "Benvenuto nel Progetto2012 di Adriano Di Luzio.\nCosa vuoi fare?",
                "Welcome!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[1]);
        
        return choice;
	}
	
	/** Crea la finestra di input in cui si chiede all'utente di inserire il path del file del parser.
	 * Anche qui, se l'utente chiude la finestra o annulla, si prova a caricare il file di default. */
	public static String insertPath() {
		String [] options = { "Scegli un file da browser", "Inserisci il percorso del file", "Scegli il file di default" };
		String input = null;
		
		// Temporary Jframe to set icon
        JFrame window = new JFrame();
        window.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("icon.png")));
		
        int choice = JOptionPane.showOptionDialog(
                window,
                "In che modo vuoi scegliere il file?",
                "Scegli il file",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[2]);
		
        switch (choice) {
        case -1:
        	System.exit(NORMAL);
        	break;
		case 0:	        
	        JFileChooser fc = new JFileChooser();
	        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        fc.showOpenDialog(window);
	        try {
	        	input = fc.getSelectedFile().getPath();
	        }
	        catch (java.lang.NullPointerException e) {
	        	return null;
	        }
			break;

		case 1:
	         input = JOptionPane.showInputDialog(
	                window,
	                "Inserisci il percorso del file di parser:",
	                "Input",
	                JOptionPane.QUESTION_MESSAGE);
			break;
			
		default:
			input = "";
			break;
		}
        
        return input;
	}
	
	/** Partendo da una stringa, prova ad aprire il file, ne carica gli oggetti, e li ritorna */
	public static ArrayList<object.location.Location> startFromFile(String path) {
		ArrayList<object.location.Location> locs = null;
		
		if (path == null) {
			startFromFile(insertPath());
		}
		
		try {
			locs = IO.startFromFile(path);
		} catch (FileNotFoundException e) {
			System.out.println("Inserisci un perscorso valido per caricare il file di Creazione Mondo, o scegli quello di default.");
			System.exit(-1);
		}
		
		return locs;
	}
	
	/** Inizia la partita da un salvataggio precedente.
	 * Se l'utente annulla l'azione, si torna alla schermata di benvenuto.
	 */
	public static ArrayList<object.location.Location> startFromLoad(String path) {
		ArrayList<object.location.Location> locs = null;
		
		try {
			// Se l'utente non ha inserito nulla:
			if (path == null || path.equals("")) {
				frame();
			} else locs = IO.loadFromFile(path);
		} catch (IOException e) {
			System.out.println("Impossibile aprire il file per il caricamento. Se si sta usando il file di default, assicurarsi che sia stato prima creato, cioe' che la partita sia stata salvata almeno una volta in quella posizione. ");
			System.exit(-1);
		}
		
		return locs;
	}
	
	/** Metodo statico per scegliere il file di salvataggio da caricare */
	public static String loadPath() {
		
	// Temporary Jframe to set icon
       JFrame window = new JFrame();
       window.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("icon.png")));
		
       JFileChooser fc = new JFileChooser();
       fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
       fc.showOpenDialog(window);
       
       try { 
    	  return fc.getSelectedFile().getPath();
       }
       catch (java.lang.NullPointerException e) {
    	   // nel caso l'utente prema annulla, o chiuda il JFileChooser, ritorno null.
    	   return null;
       }
       
	}
	
	/* ----------- ecco il MAIN! ------------------- */

	public static void frame() {
		// Potrebbe essere migliorato inserendo da linea di comando il file di parsing e di salvataggio/caricamento 
		
		int choice = showWelcomeScreen();
		MainFrame window = null;

		// Se si è premuta la x
		if (choice == -1) {
			System.exit(NORMAL);
		}
		
		// Se si crea una nuova partita:
		else if (choice == 0) {
			window = new MainFrame(startFromLoad(loadPath()));
		}
		
		// Altrimenti, se se ne sta caricando una precedente:
		else {
			window = new MainFrame(startFromFile(insertPath()));
		}
				
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("icon.png")));
		window.setVisible(true);
	}
}
