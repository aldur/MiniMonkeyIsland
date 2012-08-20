import java.awt.Image;
import java.awt.Toolkit;
import java.lang.reflect.Method;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/** Main.java
 * 21/ago/2012 18:28:21
 * Last edit: 21/ago/2012 18:28:21
 */

/**
 * This is the main, it loads laf and system-specific settings.
 */
public class Main {
	public static void main(String[] args) {

		// If Mac: menu bar integration and aqua style
		if (System.getProperty("os.name").equals("Mac OS X")) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Progetto 2012");
						
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {  }
			
			try { // set dock icon using reflection
			    Class<?> util = Class.forName("com.apple.eawt.Application");
			    Method getApplication = util.getMethod("getApplication", new Class[0]);
			    Object application = getApplication.invoke(util);
			    Class<?> params[] = new Class[1];
			    params[0] = Image.class;
			    Method setDockIconImage = util.getMethod("setDockIconImage", params);
			    Image image = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("icon.png"));
			    setDockIconImage.invoke(application, image);
			} catch (Exception e) {  }
		}
		
		// If Linux
		else if (System.getProperty("os.name").equals("Linux")) {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			} catch (Exception e) {  }
		}
		
		// Windows, or unknown
		else {
			try {
			    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			        if ("Nimbus".equals(info.getName())) {
			            UIManager.setLookAndFeel(info.getClassName());
			            break;
			        }
			    }
			} catch (Exception e) {  } 
		}
			
		gui.MainFrame.frame();
	}
}
