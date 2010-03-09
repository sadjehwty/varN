package Varenne;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import util.*;
import java.io.*;

class Varenne extends JPanel implements ActionListener{

	
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3690191062090070073L;
	protected JTextField cod;
	protected JTextArea area,in;
	private JScrollPane scrol,perg;
	protected JCheckBox che;
	private Box box,or;
	protected static World w;
	public JMenuBar menu;
	private JMenu opzioni,help;
	private JMenuItem chcod,about,istr;
	private java.net.URL url;
	protected Doc d;
	
	public Varenne(JFrame f) {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));//
		box=new Box(BoxLayout.Y_AXIS);
		or=new Box(BoxLayout.X_AXIS);
		or.setMaximumSize(new Dimension(500,20));
		cod=new JTextField("Inserisci chiave");
		cod.addActionListener(this);
		che=new JCheckBox();
		url=getClass().getResource("/resources/image/dx.gif");
		if(url!=null)
			che.setIcon(new ImageIcon(url));
		url=getClass().getResource("/resources/image/sx.gif");
		if(url!=null)
			che.setSelectedIcon(new ImageIcon(url));
		che.setSelected(false);
		che.setToolTipText("Codifica");
		che.addActionListener(this);
		or.add(cod);
		or.add(che);
		in=new JTextArea("",4,20);
		in.setEditable(false);
		area=new JTextArea(6,20);
		area.setEditable(false);
		che.addActionListener(new Reset(area));
		che.addActionListener(new Reset(in));
		d=new Doc(area,in,che);
		in.getDocument().addDocumentListener(d);
		perg=new JScrollPane(in);
		scrol=new JScrollPane(area);
		box.add(or);
		box.add(Box.createVerticalStrut(5));
		box.add(perg);
		box.add(Box.createVerticalStrut(5));
		box.add(scrol);
		add(box);
		cod.select(0,16);
		cod.requestFocus();
		
		menu=new JMenuBar();
		opzioni=new JMenu("Opzioni");
		chcod=new JMenuItem("Cambia Chiave");
		chcod.addActionListener(new Listener(cod,in,area));
		opzioni.add(chcod);
		help=new JMenu("Help");
		istr=new JMenuItem("Help");
		about=new JMenuItem("About...");
		istr.addActionListener(new Help(1,f));
		about.addActionListener(new Help(0,f));
		help.add(istr);
		help.add(about);
		menu.add(opzioni);
		menu.add(help);
		f.setJMenuBar(menu);
	}

	public static World getWorld(){
		return w;
	}

	public void actionPerformed(ActionEvent eva){
		if(eva.getSource().equals(che)){
			if(che.isSelected())
				che.setToolTipText("Decodifica");
			else
				che.setToolTipText("Codifica");
		}
		else{
			try{w=new World(cod.getText());}
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
	
	public static void main(String args[]) {
		System.out.println("Starting Varenne...");
		if(args.length==0){
			MyF mainFrame = new MyF("Varenne",ClassLoader.getSystemResource("resources/image/logo.gif"),true,true);
			mainFrame.getContentPane().add(new Varenne(mainFrame));
			mainFrame.pack();
			mainFrame.setVisible(true);}
		else{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			boolean verso=true;
			String s=null;
			try{
				while(true){
					System.out.println("Dammi una chiave (Solo lettere):");
					s=br.readLine();
					try{
						w=new World(s);
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
				System.out.println("Pronto a tradurre\nSi esce con \"--end--\"");
				s=br.readLine();
				do{
					System.out.println(w.trad(s,verso));
					s=br.readLine();
				}
				while(!s.equals("--end--"));
			}
			catch(IOException ioe){System.out.println("Errore d'IO\nChiudo");System.exit(-99);}
		}
	}
}
