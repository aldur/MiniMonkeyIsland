
package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

/** Il pannello di informazioni, contentente il nome dell'oggetto, ecc. */
public class InfoPanel extends JPanel {

	private JLabel nameLabel;
	private JLabel openLabel;
	private JLabel onLabel;
	private JLabel locationLabel;
	private JLabel variousLabel;
	
	public InfoPanel() {
		super();
		
		nameLabel = new JLabel("Nome: ");
		nameLabel.setVisible(false);
		this.add(nameLabel);
		
		openLabel = new JLabel();
		openLabel.setVisible(false);
		this.add(openLabel);
		
		onLabel = new JLabel();
		onLabel.setVisible(false);
		this.add(onLabel);
		
		locationLabel = new JLabel("");
		locationLabel.setVisible(false);
		this.add(locationLabel);
		
		variousLabel = new JLabel("");
		variousLabel.setVisible(false);
		this.add(variousLabel);		
	}
	
	public void updateInfo(object.GameObject in) {
		nameLabel.setVisible(false);
		nameLabel.setText("Nome: " + in.getName());
		nameLabel.setVisible(true);
		
		openLabel.setVisible(false);
		openLabel.setText("");
		onLabel.setVisible(false);
		onLabel.setText("");
		locationLabel.setVisible(false);
		locationLabel.setText("");
		variousLabel.setVisible(false);
		variousLabel.setText("");
		
		if (in instanceof object.thing.Openable)
			openLabel.setText(((object.thing.Openable) in).getState() 
					? "Stato: Aperto" : "Stato: Chiuso");
			openLabel.validate();
			openLabel.setVisible(true);
			
		if (in instanceof object.thing.Turnable) {
			onLabel.setText(((object.thing.Turnable) in).getState()
					? "Stato: Acceso" : "Stato: Spento");
			onLabel.validate();
			onLabel.setVisible(true);
		}
		
		if (in instanceof object.thing.Through) {
			locationLabel.setText("Tra: " + ((object.thing.Through) in).linkString());
			locationLabel.validate();
			locationLabel.setVisible(true);
		}
		
		if (in instanceof object.thing.Readable) {
			variousLabel.setText(((object.thing.Readable) in).letto()
					? "Stato: Letto" : "Stato: Non Letto");
			variousLabel.validate();
			variousLabel.setVisible(true);
		}
		
		else if (in instanceof object.thing.Through) {
			variousLabel.setText(((object.thing.Through) in).isLocked()
					? "Stato: Chiuso a chiave" : "Stato: NON chiuso a chiave");
			variousLabel.validate();
			variousLabel.setVisible(true);
		}
		
		this.revalidate();
		Singleton.getFrame().pack();
	}
}
