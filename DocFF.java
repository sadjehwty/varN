package Varenne;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocFF implements DocumentListener {

	private JTextArea a,t;
	private JCheckBox c;
	private World w;
	
	public DocFF(JTextArea a,JTextArea t,JCheckBox c){
		this.a=a;
		this.t=t;
		this.c=c;
	}
	
	public void insertUpdate(DocumentEvent e) {
		w=VarenneFF.getWorld();
		removeUpdate(e);
	}

	public void removeUpdate(DocumentEvent arg0) {
		byte b[]=t.getText().getBytes();
		w.reset();
		a.setText("");
		for(int i=0;i<b.length;i++)
			a.append(""+(char)w.trad(b[i],c.isSelected()));
	}

	public void changedUpdate(DocumentEvent arg0) {}

}
