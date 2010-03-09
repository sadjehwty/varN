/*
 * CtrlList.java
 *
 * Created on 6 febbraio 2004, 19.03
 */

/**
 *
 * @author  Davide
 */
package Varenne;
import java.awt.event.*;

public class CtrlList extends Thread implements ActionListener {

    private Ctrl frame = null;
    private Thread t=null;

    /** Creates a new instance of CtrlList */
    public CtrlList(Ctrl frame) {
        this.frame=frame;
    }

    public void run() {
        int k=100,i=0;
        while(i<=100){
            k/=2;
            i+=k;
            frame.jpb.setValue(i);
            System.out.println(""+i+" "+k);
        }
        frame.start.setEnabled(true);
        frame.canc.setEnabled(false);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(frame.start)){
            t=new Thread(this);
            t.start();
            frame.start.setEnabled(false);
            frame.canc.setEnabled(true);
        }
        else{
            
            frame.start.setEnabled(true);
            frame.canc.setEnabled(false);
        }
    }

    public static void main(String arv[]){
        Ctrl.main(arv);
    }
}
