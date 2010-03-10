/**
* @(#)MyF.java
* @version 0.5
* @author SaThot
*/
package org.macrobug.util;

import java.awt.*;
import javax.swing.*;
import java.net.URL;

/**Versione semplificata e personalizzata di JFrame
* Verisione aggiornata per i Jar file (Toolkit)
*/
public class MyF extends JFrame{
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3834593227096667185L;
	/** Costruttore completo
	*
	* Permette si settare il titolo, l'icona,
	* la ridimensionabilit&agrave; e la chiusa predefinita
	* @param s Titolo finestra
	* @param ico Percorso icona
	* @param b Settato a vero se il programma si chiude con X
	* @param r Settato a vero se la finestra &egrave; ridimensioanbile
	*/
	public MyF(String s,URL ico,boolean b,boolean r){
		super(s);
		setResizable(r);
		if(b)
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200,100);
		Toolkit tk=getToolkit();
		if(ico!=null)
			setIconImage(tk.getImage(ico));}

	/**
	*Costruttore
	* Permette di settare titolo, icona e chiusura predefinita
	* La finestra non si ridimensiona
	* @param s Titolo finestra
	* @param ico Percorso icona
	* @param b Settato a vero se il programma si chiude con X
	*/
	public MyF(String s,URL ico,boolean b){
		this(s,ico,b,false);}
	/**
	* Costruttore
	* Permette di settare titolo e icona
	* La finestra non si chiude
	* @param s Titolo finestra
	* @param ico Percorso icona
	*/
	public MyF(String s,URL ico){
		this(s,ico,false);}
	/**
	* Costruttore
	* Setta il titolo
	* l'icona se viene travata sar&agrave; logo.gif
	* @param s Titolo finestra
	*/
	public MyF(String s){
		this(s,ClassLoader.getSystemResource("resources/image/logo.gif"));
		setBounds(400,300,200,150);
		}
	/**
	*Costruttore
	* Il titolo &egrave; <----- NONE ----->
	*/
	public MyF(){
		super("<----- NONE ----->");
		setBounds(400,300,200,150);
		}
	/**
	* Eseguibile di prova
	*/
	public static void main(String argv[]){
		MyF f=new MyF("Logo");
		MyF fi=new MyF("Mela",ClassLoader.getSystemResource("resources/image/null.gif"));
		fi.setVisible(true);
		f.setVisible(true);
		MyF F=new MyF("NotFound",ClassLoader.getSystemResource("resources/image/devil.gif"));
		F.setVisible(true);}}
