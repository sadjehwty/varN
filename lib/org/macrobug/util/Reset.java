/**
* @(#)Reset
* @version 0.1
* @author SaThot
*/
package org.macrobug.util;

import java.awt.event.*;
import javax.swing.text.*;
/**
* Ascolatore per cancellare il testo in una <code>JTextArea</code>
*/
public class Reset implements ActionListener{

	private JTextComponent area;
	/**
	* Costruttore per l'ascoltatore
	* @param area JTextArea da cancellare
	*/
	public Reset(JTextComponent area){
		this.area=area;}
	/**
	*Esegue il reset
	*/
	public void actionPerformed(ActionEvent event){
		area.setText("");
	}
}
