package org.macrobug.util;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;

public class StampaListener implements ActionListener{
	private JTextField tot;
	private JTextArea area;
	private String nssm;
	private PrintWriter fuori;
	public StampaListener(JTextField tot,JTextArea area,String nome){
		this.area=area;
		this.tot=tot;
		try{
			fuori=new PrintWriter(new FileWriter(nome),true);}
		catch(IOException ec){System.out.println("Errore");}
		if(fuori!=null)
			fuori.println("------------------------");}
	public void actionPerformed(ActionEvent e){
		nssm=area.getSelectedText();
		if(fuori!=null&&nssm!=null){
		fuori.println(nssm);
		int i=nssm.lastIndexOf(" ");
		String str=tot.getText();	
		float temp=0,temp2=0;
		try{
			temp=Float.parseFloat(str);
			temp2=Float.parseFloat(nssm.substring(i,nssm.length()));}
			catch(Exception ws){System.out.println("["+temp2+"]");}
		tot.setText(""+(temp+temp2));
			}
		}
	}
