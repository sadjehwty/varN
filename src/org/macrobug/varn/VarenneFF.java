package org.macrobug.varn;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import org.macrobug.util.MyF;

public class VarenneFF extends Varenne{
	
	private static final long serialVersionUID = 3256999951979591217L;
	
	public VarenneFF(JFrame g){
		super(g);
		in.getDocument().removeDocumentListener(d);
		in.getDocument().addDocumentListener(new DocFF(area,in,che));
	}
	
	public static void main(String args[]) {
		System.out.println("Starting Varenne...");
		if(!args[0].equalsIgnoreCase("text")){
			MyF mainFrame = new MyF("Varenne",ClassLoader.getSystemResource("resources/image/logo.gif"),true,true);
			VarenneFF v=new VarenneFF(mainFrame);
			mainFrame.getContentPane().add(v);
			mainFrame.pack();
			mainFrame.setVisible(true);}
		else{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean verso=true;
		String s=null;
		World w=null;
		try{
			while(true){
			System.out.println("Dammi una chiave in esadecimale:");
			s=br.readLine();
			try{
				byte b[]=new byte[s.length()/2];
				String temp=null;
				for(int i=0;s.length()!=0;i++){
					temp=s.substring(0,2);
					int in=Integer.parseInt(temp,16);
					b[i]=(byte)in;
					System.out.println("byte: "+b[i]+" int:"+in);
					s=s.substring(2);
				}
				w=new World(b);
				break;
			}
			catch(IllegalArgumentException iae){}
			}
			while(true){
				System.out.println("Codifica o Decodifica (c|d):");
				s=br.readLine();
				if(s.charAt(0)=='c')
					break;
				if(s.charAt(0)=='d'){
					verso=false;
					break;
				}
			}
			System.out.println("Pronto a tradurre\nDammi il file");
			s=br.readLine();}
		catch(IOException ioe){
			System.exit(-3);
		}
		FileInputStream fis=null;
		FileOutputStream fos=null;
		try{
			fis=new FileInputStream(s);}
		catch(FileNotFoundException fnfe){
			System.out.println("File non trovato\nChiudo");
			System.exit(-1);
		}
		try{
			System.out.println("Dove salvo?");
			s=br.readLine();
			fos=new FileOutputStream(s);
		}
		catch(IOException ioe){}
		try{
			int i=fis.read();
			while(i>=0){
				fos.write(w.trad((byte)i,verso));
				i=fis.read();
			}
			System.out.println("Fatto");
		}
		catch(IOException ioe){
			System.out.println("Errore in lettura file");
			System.exit(-2);
		}
	}}

	public void actionPerformed(ActionEvent eva) {
		if(eva.getSource().equals(che)){
			if(che.isSelected())
				che.setToolTipText("Decodifica");
			else
				che.setToolTipText("Codifica");
		}
		else{
			try{
				String s=cod.getText();
				byte b[]=new byte[s.length()/2];
				String temp=null;
				for(int i=0;s.length()!=0;i++){
					temp=s.substring(0,2);
					int in=Integer.parseInt(temp,16);
					b[i]=(byte)in;
					s=s.substring(2);
				}
				w=new World(b);
			}
			catch(IllegalArgumentException ex){
				cod.setText("Chiave non valida");
				cod.select(0,17);
				cod.requestFocus();
				return;
			}
			cod.setEditable(false);
			in.setEditable(true);
			in.requestFocus();
		}
	}
}
