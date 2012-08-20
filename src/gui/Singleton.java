/** Singleton.java
 * 29/giu/2012 16:48:21
 * Last edit: 29/giu/2012 16:48:21
 *  
 */

package gui;

import java.util.ArrayList;

/**
 * Questo è il singleton. Un desing pattern che permette di instanziare la classe una sola volta, leggermente modificato per contenere 
 * riferimenti globali al MainFrame, al giocatore, e all'arrayList di locazioni.
 */
public class Singleton {
  private static Singleton instance;
  private static MainFrame frame;
  private static object.actor.Actor player;
  private static ArrayList<object.location.Location> locations;

  private Singleton() {
	  
  }

  public static Singleton getInstance() {
    if (instance == null)
    	instance = new Singleton();

    return instance; 
  }
  
  public static MainFrame getFrame() {		  
	  return frame;
  }
  
  public static void setFrame(MainFrame f) {
	  frame = f;
  }
  
  public static object.actor.Actor getPlayer() {
	  return player;
  }
  
  public static void setPlayer(object.actor.Actor a) {
	 player = a;
  }
  
  public static void setLocations(ArrayList<object.location.Location> l) {
	  locations = l;
  }
  
  public static ArrayList<object.location.Location> getLocations() {
	  return locations;
  }
}