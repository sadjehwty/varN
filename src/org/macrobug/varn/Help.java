package org.macrobug.varn;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import org.macrobug.util.*;

public class Help implements ActionListener{
	
	private Dialogo d;
	
	public void actionPerformed(ActionEvent ev){
		d.show();
	}
	
	public Help(int i,JFrame f){
		URL url=getClass().getResource("/resources/image/horse.gif");
		switch(i){
			case 0:{d=new Dialogo("About","Varenne 1.0\nby MacroBug\nScritto da SaThot <dices@ctonet.it>\n2003/11/17 20:09:00\n\nVisita il sito internet per le novit�:\nhttp://dices.web.ctonet.it",url,f);break;}
			case 1:{d=new Dialogo("Guida",getClass().getResource("/resources/guida.html"),url,f);break;}
		}
	}
}