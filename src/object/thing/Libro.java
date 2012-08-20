/** Libro.java
 * 10/mag/2012 16:05:45
 * Last edit: 10/mag/2012 16:05:45
 * 
 * Primo oggetto fisico del gioco, un buon libro!
 */

package object.thing;

/**
 * Questa è la classe libro, titolo ed autore sono final, il contenuto no in modo che possa essere settato in un secondo momento.
 */
public class Libro extends Utils 
					implements Readable {
	
	protected String contents;
	protected final String author;
	protected final String title;
	
	protected boolean letto;
	
	public Libro(String code, String name) {
		this(code, name, "", "Anonymous", "Unknown");
	}
	
	/** Il costruttore per il parser */
	public Libro(String code, String name, String contents) {
		this(code, name, contents, "Anonymous", "Unknown");
	}
	
	/** Il costruttore che prende come input contenuto, titolo ed autore */
	public Libro(String code, String name, String contents, String author, String title) {
		super(code, name);
		this.contents = contents;
		this.author = author;
		this.title = title;
		letto = false;
	}	
	
	public void setContents(String c) {
		contents = c;
	}

	public boolean letto() {
		return letto;
	}
	
	@Override
	/** Il metodo esamina per un libro chiama semplicemente il metodo leggi(). */
	public String esamina() {
		examinated = true;
		return leggi();
	}
	
	@Override
	/** Il metodo leggi ritorna titolo autore e contenuto */
	public String leggi() {
		letto = true;
		return "Titolo: " + title + "\n" + "Autore: " + author + "\n" + contents;
	}
}
