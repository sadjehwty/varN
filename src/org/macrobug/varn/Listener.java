package org.macrobug.varn;
import java.awt.event.*;
import javax.swing.*;

public class Listener implements ActionListener{
	
	private JTextArea in,out;
	private JTextField t;
	
	public void actionPerformed(ActionEvent eve){
		t.setEditable(true);
		in.setEditable(false);
		in.setText("");
		out.setText("");
		t.select(0,t.getText().length());
		t.requestFocus();
	}
	
	public Listener(JTextField t,JTextArea in,JTextArea out){
		this.t=t;
		this.in=in;
		this.out=out;
	}
}