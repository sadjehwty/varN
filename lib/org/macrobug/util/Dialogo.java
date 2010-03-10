/**
* @(#)Dialogo.java
* @author SaThot
* @version 0.1
*/

package org.macrobug.util;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.text.html.*;
import javax.swing.event.*;
import java.net.*;
/**Classe che facilita le impostazioni
 *per creare pannelli di dialogo
 *perfettamente funzionanti ed autonomi
 */
public class Dialogo{

	private String ti;
	private Frame f;
	private Image ii;
	private URL url=null;
	private DialogoHTML d;
	/**Fa apparire il dialogo creato*/
	public void show(){
		if(ii!=null)
			JOptionPane.showMessageDialog(f,d,ti,JOptionPane.WARNING_MESSAGE,new ImageIcon(ii));
		else
			JOptionPane.showMessageDialog(f,d,ti,JOptionPane.WARNING_MESSAGE);
	}
	/**
	* Costruttore
	* Pienamente personalizzabile
	* @param ti Titolo della finestra
	* @param s Testo o percorso del file da visualizzare
	* @param im Immagine da porre a lato, se trovata
	* @param f Frame principale da inibire se presente
	*/
	public Dialogo(String ti,Object s,URL im,Frame f){
		this.ti=ti;
		this.f=f;
		if(f==null)
			this.f=new MyF(ti,im);
		d=new DialogoHTML(s);
		if(im!=null)
			ii=this.f.getToolkit().getImage(im);
	}
	/**
	* Costruttore non inibente
	* @param ti Titolo della finestra di dialogo
	* @param s Testo o percorso del file da visualizzare
	* @param im percorso dell'immagine che se trovata viene visualizzata a lato
	*/
	public Dialogo(String ti,Object s,URL im){
		this(ti,s,im,null);
	}
	/**
	* Costruttore
	* Con titolo e testo ma senza immagine
	* @param ti titolo della finestra di dialogo
	* @param s Testo o percorso del file da visualizzare
	*/
	public Dialogo(String ti,Object s){
		this(ti,s,ClassLoader.getSystemResource("resources/image/logo.gif"));
	}
	/**
	* Costruttore
	* Finestra d'errore non messaggio d'errore di default
	* personalizzabile il titolo
	* @param ti Titolo della finestra di dialogo
	*/
	public Dialogo(String ti){
		this(ti,"Errore Generale");
	}
	/**
	* Costruttore senza parametri
	* Visualizza una finestra d'errore non personalizzabile
	*/
	public Dialogo(){
		this("Errore");
	}
	/**
	* Eseguibile di DeBug
	*/
	public static void main(String argv[]){
		Dialogo D=new Dialogo("Con Testo","\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nTesto");
		Dialogo d=new Dialogo("HTML",ClassLoader.getSystemResource("resources/macro.html"));
		Dialogo dop=new Dialogo("Mela","macro.html",ClassLoader.getSystemResource("resources/image/null.gif"));
		D.show();
		dop.show();
		d.show();
	}
/**
* Classe di servizio per <code>Dialogo</code>
*/
private class DialogoHTML extends JPanel{

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 4049072744123939120L;
	private JEditorPane edit;
	/**prova*/
	public DialogoHTML(Object s){
		if(s!=null){
			edit=new JEditorPane();
			edit.setEditable(false);
			setText(edit,s);
			edit.select(0,0);
		}
		JScrollPane scroller = new JScrollPane();
		scroller.setPreferredSize(new Dimension(250, 125));
		scroller.getViewport().add(edit);
        add(scroller);
	}
	
	private void setText(JEditorPane edit,Object obj){
		if(obj instanceof URL){
			try{
				URL url=(URL)obj;
				edit.setPage(url);
              	edit.addHyperlinkListener(createHyperLinkListener());
    		}
			catch(Exception ex){
				edit.setText("File NON Trovato: "+url.getPath());
			}
		}
		else
			edit.setText((String)obj);
	}
	
	public HyperlinkListener createHyperLinkListener() {
		return new HyperlinkListener() {
 	    	public void hyperlinkUpdate(HyperlinkEvent e) {
 				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
 		    		if (e instanceof HTMLFrameHyperlinkEvent) {
 						((HTMLDocument)edit.getDocument()).processHTMLFrameHyperlinkEvent(
 			    		(HTMLFrameHyperlinkEvent)e);
 		    		}
 		    		else {
 						try {
 			    			edit.setPage(e.getURL());}
 			    		catch (IOException ioe) {System.out.println("IOE: " + ioe);}
 		    		}
 				}
 	    	}
 		};
    }
}}
