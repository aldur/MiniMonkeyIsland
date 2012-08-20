
package gui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextPane;

/** Il pannello console, in cui visualizzare i messaggi di info */
public class ConsolePanel extends JPanel {

	private JTextPane consoleText;
	
	public ConsolePanel() {
		super();
		
		this.setLayout(new GridLayout(0, 1, 0, 0));
		
		consoleText = new JTextPane();
		consoleText.setText("Qui appariranno eventuali messaggi.");
		consoleText.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
		consoleText.setOpaque(false);
		consoleText.setEditable(false);
		this.add(consoleText);
	}
	
	public void setText(String in) {
		consoleText.setText(in);
	}

}
