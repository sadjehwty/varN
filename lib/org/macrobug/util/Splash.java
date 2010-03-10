/**
* @author SaThot
* @version 0.1
* @(#)Splash
*/
package org.macrobug.util;

import java.awt.*;
import javax.swing.*;
import java.net.URL;
/**Classe che gestisce gli Spalsh Screen*/
public class Splash{

    private JLabel splashLabel = null;
    private JWindow splashScreen = null;
    private URL url=null;
    private ImageIcon ico;

    /**
    * Inizializza lo <code>Splash Screen</code>
    * @param s Percorso immagine da immettere nello Splash
    * @throws IllegalArgumentException se l'immagine non &egrave; stata trovata
    * @see #showSplashScreen
    * @see #hideSplash
    */
	public Splash(String s) throws IllegalArgumentException{
		url=getClass().getResource("/resources/image/"+s);
		if(url==null)
			throw new IllegalArgumentException();
		ico=new ImageIcon(url);splashLabel = new JLabel(ico);
		splashScreen = new JWindow();
		splashScreen.getContentPane().add(splashLabel);
		splashScreen.pack();
		Dimension d=splashScreen.getToolkit().getScreenSize();
		splashScreen.setLocation((d.width-ico.getIconWidth())/2,(d.height-ico.getIconHeight())/2);
	}

	/**
	* Visualizza lo Splash creato
	*/
	public void showSplashScreen() {

		splashScreen.setVisible(true);;

		}
	/**
	* Nasconde lo Splash creato
	*/
	public void hideSplash() {
		splashScreen.setVisible(false);
		splashScreen = null;
		splashLabel = null;
	}
}
