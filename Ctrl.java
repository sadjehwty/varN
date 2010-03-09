/*
 * Ctrl.java
 *
 * Created on 6 febbraio 2004, 18.42
 */

/**
 *
 * @author  Davide
 */
package Varenne;
import javax.swing.*;
import java.awt.event.*;

public class Ctrl extends JPanel implements ActionListener {
    
    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3688791358068766003L;
	JButton start = null;    
    JButton canc = null;
    JProgressBar jpb=null;
    private JFrame frame = null;
    private Box box = null;
    JTextArea jta;
    
    /** Creates a new instance of Ctrl */
    public Ctrl(JTextArea jta) {
        this.jta=jta;
        frame=new JFrame("Controllo robustezza");
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        jpb=new JProgressBar();
        add(jpb);
        box=new Box(BoxLayout.X_AXIS);
        start=new JButton("Inizia");
        canc=new JButton("Interrompi");
        canc.setEnabled(false);
        CtrlList cl=new CtrlList(this);
        start.addActionListener(cl);
        canc.addActionListener(cl);
        box.add(start);box.add(canc);
        add(box);
        frame.getContentPane().add(this);
        frame.pack();
    }
    
    public Ctrl(){
        this(new JTextArea());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame prova=new JFrame("Prova");
        JButton b=new JButton("Start");
        b.addActionListener(new Ctrl());
        prova.getContentPane().add(new JPanel().add(b));
        prova.pack();
        prova.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent actionEvent) {
        frame.setVisible(true);
    }
    
}
