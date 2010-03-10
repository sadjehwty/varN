package org.macrobug.util;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.io.*;

public class Load extends JPanel implements ActionListener{
	
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3258690992340283957L;
	protected JTextComponent area;
	protected JFileChooser chooser;
	protected JFrame f;
	protected String s;
	
	public Load(JTextComponent area,JFrame f,String s){
		this.area=area;
		this.f=f;
		this.s=s;
	}
	public Load(JTextComponent area,String s){
		this(area,null,s);
	}
	
	public void actionPerformed(ActionEvent eve){
		chooser=new JFileChooser();
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.addChoosableFileFilter(new ExampleFileFilter(s, "File "+s));
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setDialogTitle("Apri");
		int retval = chooser.showOpenDialog(this);
		if (retval == JFileChooser.APPROVE_OPTION) {
			getText(chooser.getSelectedFile(),area);
		}
		if(f!=null)
			f.pack();
	}
	
	public static void getText(File f,JTextComponent in){
		FileReader input=null;
		try{
			input=new FileReader(f);
		}
		catch(IOException exce){
			Dialogo2 d=new Dialogo2("Errore","File Non Trovato\nMa xchè se ho usato il FileChooser?\nchissà");
			d.setVisible(true);;
		}
		BufferedReader buff=new BufferedReader(input);
		try{
			String temp;
			while(buff.ready()){
				temp=buff.readLine();
				in.setText(in.getText()+temp+"\n");
			}
		}
		catch(IOException dioDog){
			Dialogo2 d=new Dialogo2("Errore di Lettura","Boh, non sò cosa è successo\nProva a chiudere e riAprire");
			d.setVisible(true);
		}
	}
}
