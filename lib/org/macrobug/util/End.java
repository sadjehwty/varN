package org.macrobug.util;

import java.awt.event.*;
import javax.swing.*;

public class End implements WindowListener {
	
	private JFrame f=null;
	private int i;
	
	public End(){
		super();
	}
	public End(JFrame f){
		this(f,2000);
	}
	public End(JFrame f,int i){
		this.f=f;
		this.i=i;
	}
	
	public void windowClosed(WindowEvent e){}
	public void windowClosing(WindowEvent e){
		if(f!=null){
			f.setVisible(false);;
			final Splash spla=new Splash("splash.jpg");
			SwingUtilities.invokeLater(new Runnable() {
	   			public void run() {
					spla.showSplashScreen();
	    		}
			});
			try{Thread.sleep(i+1000);}
			catch(InterruptedException ex){}
		}
		System.exit(0);
	}
	public void windowOpened(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
}
