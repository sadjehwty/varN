package org.macrobug.varn;

import javax.swing.event.*;
import javax.swing.*;

public class Doc implements DocumentListener{
	
	private JTextArea a,t;
	private JCheckBox c;
	private World w;
	
	public Doc(JTextArea a,JTextArea t,JCheckBox c){
		this.a=a;
		this.t=t;
		this.c=c;
	}
	
	public void insertUpdate(DocumentEvent e){
		w=Varenne.getWorld();
		removeUpdate(e);
	}
	
	public void removeUpdate(DocumentEvent e){
		String s=t.getText();
		w.reset();
		a.setText(w.trad(s,c.isSelected()));
	}
	
	public void changedUpdate(DocumentEvent e){}
}