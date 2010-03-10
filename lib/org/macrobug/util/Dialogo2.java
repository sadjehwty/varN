/**
 * Versione deprecata di <code>Dialogo</code>
 * @(#)Dialogo2.java
 * @version 0.2
 * @author SaThot
 * @see Dialogo#Dialogo
 */
package org.macrobug.util;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Dialogo2 extends JDialog{
	
	
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3257847692674807604L;
	public Dialogo2(String ti,String s,String im, boolean bool,Frame f){
		super(f,ti,bool);
		setResizable(false);
		setBounds(400,300,255,265);
		Container dc=getContentPane();
		DialogoHTML d=new DialogoHTML(s,im);
		dc.add(d);
		pack();
	}
	public Dialogo2(String ti,String s,String im){
		this(ti,s,im,false,null);
	}
	public Dialogo2(String ti,String s){
		this(ti,s,null);
	}
	public Dialogo2(String s){
		this(s,"Errore Generale");
	}
	public Dialogo2(){
		this("Errore");
	}

	public static void main(String argv[]){
		Dialogo2 D=new Dialogo2("Con Testo","\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nTesto","icon.jpg");
		Dialogo2 d=new Dialogo2("HTML","macro.html");
		JFrame f=new MyF("Titolo",ClassLoader.getSystemResource("/resources/image/icon.jpg"));
		Dialogo2 df=new Dialogo2("Html","Icona, dove 6","icon.jpg",true,f);
		D.setVisible(true);;
		d.setVisible(true);;
		f.setVisible(true);;
		df.setVisible(true);;
	}
	private class DialogoHTML extends JPanel{
		
		/**
		 * Comment for <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = 3256728376919208244L;
		private Box b;
		private JLabel eti;
		private JEditorPane edit;
		
		public DialogoHTML(String s,String st){
		final String path=new String("/resources/");
		URL url=null;
		b=new Box(BoxLayout.X_AXIS);
		if(st!=null){
			url=getClass().getResource(path+"image/"+st);
			if(url!=null){
				eti=new JLabel(new ImageIcon(url));
				b.add(eti);
			}
		}
		if(s!=null){
			url=getClass().getResource(path+s);
			edit=new JEditorPane();
			edit.setEditable(false);
			if(url!=null){
				try{
					edit.setPage(url);
        /*questo servirà per i link
         *per ora inutilizzato
         *         	edit.addHyperlinkListener(createHyperLinkListener());*/
        		}
				catch(Exception ex){
					edit.setText("File NON Trovato");}
			}
			else
				edit.setText(s);
		}
		JScrollPane scroller = new JScrollPane();
		scroller.setPreferredSize(new Dimension(250, 125));
		scroller.getViewport().add(edit);
        b.add(scroller);
        edit.select(0,0);
        add(b);
	}
	
	/*Serve sempre per i link
	 *come sopra
	 *public HyperlinkListener createHyperLinkListener() {
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
    } */
}}
