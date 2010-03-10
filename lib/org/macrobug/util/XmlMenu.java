package org.macrobug.util;

import javax.xml.parsers.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.awt.*;

public class XmlMenu extends Thread{

	private Document d;
	private JMenu mb;
	private File f;
	
	public XmlMenu(String file) throws ParserConfigurationException,SAXException,IOException{
		this(new File(file));
	}
	
	public void run(){
		DOMSource dom=new DOMSource(d);
		TransformerFactory tFactory =TransformerFactory.newInstance();
		try{
			Transformer transformer = tFactory.newTransformer();
			StreamResult result = new StreamResult(f);
			transformer.transform(dom, result);
		}
		catch(Exception e){}
	}
	
	public XmlMenu(File file) throws ParserConfigurationException,SAXException,IOException{
		f=file;
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db=dbf.newDocumentBuilder();
		d=db.parse(file);
		Node nl=d.getDocumentElement();
		mb=createMenu(nl);
		Runtime.getRuntime().addShutdownHook(this);
	}

	private JMenu createMenu(Node n){
		JMenu mi=new JMenu(n.getNodeName());
		NodeList nl=n.getChildNodes();
		JMenuItem m=null;
		for(int i=0;i<nl.getLength();i++)
			if(nl.item(i).getNodeType()==Node.ELEMENT_NODE&&nl.item(i).hasChildNodes())
				mi.add(createMenu(nl.item(i)));
		if(!n.hasAttributes()&&(!hasElement(nl)||!n.hasChildNodes())){
			m=new JMenuItem("empty");
			m.setEnabled(false);
		}
		else{
			m=new JMenuItem(n.getNodeName());
			m.addActionListener(new Page(n));
		}
		mi.add(m);
		return mi;
	}
	private boolean hasElement(NodeList nl){
		for(int i=0;i<nl.getLength();i++)
			if(nl.item(i).getNodeType()==Node.ELEMENT_NODE)
				return true;
		return false;
	}
	public JMenu getMenu(){
		return mb;
	} 

	public static void main(String[] args) {
		MyF f=new MyF();
		f.setDefaultCloseOperation(MyF.EXIT_ON_CLOSE);
		XmlMenu xm=null;
		try{
			xm=new XmlMenu("prova.xml");
		}catch(Exception e){
			StackTraceElement[] stack= e.getStackTrace();
			StringBuilder s=new StringBuilder();
			for(int i=0;i<stack.length;i++)
				s.append(stack[i]+"\n");
			Dialogo d=new Dialogo("Errore",s.toString());d.show();
		}
		JMenuBar bar=new JMenuBar();
		bar.add(xm.getMenu());
		f.setJMenuBar(bar);
		f.setVisible(true);
	}
	class Page implements ActionListener{

		public void actionPerformed(ActionEvent ae){
			d.setVisible(true);
		}
		JDialog d;
		public Page(Node n) throws IllegalArgumentException{
			d=new JDialog((JFrame)null,n.getNodeName(),true);
			d.getContentPane().add(createPanel(n));
			d.pack();
		}
		private JPanel createPanel(Node n) throws IllegalArgumentException{
			JPanel p=new JPanel();
			p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
			NodeList nl=n.getChildNodes();
			for(int i=0;i<nl.getLength();i++){
				if(!nl.item(i).hasChildNodes()&&nl.item(i).getNodeType()==Node.ELEMENT_NODE){
					Box b=Box.createHorizontalBox();
					String text="";
					NamedNodeMap nnm=nl.item(i).getAttributes();
					b.add(new JLabel(nl.item(i).getNodeName()));
					if(nnm!=null&&nnm.getLength()>0)
						text=nnm.item(0).getNodeValue();
					b.add(new Tag(text,nl.item(i)));
					p.add(b);
				}
			}
			JButton bu=new JButton("Ok");
			bu.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					Container co=d.getContentPane();
					Component[] c=co.getComponents();
					for(int i=0;i<c.length;i++){
						if(c[i] instanceof JPanel){
							c=((JPanel)c[i]).getComponents();
							for(int j=0;j<c.length;j++){
								if(c[j] instanceof Box){
									Component C[]=((Box)c[j]).getComponents();
									for(int k=0;k<C.length;k++){
										if(C[k] instanceof Tag){
											Tag t=(Tag)C[k];
											NamedNodeMap nn=t.getTag().getAttributes();
											nn.item(0).setNodeValue(t.getText());
										}
									}
								}
							}
							break;
						}
					}
					d.setVisible(false);
			}});
			p.add(bu);
			return p;
		}
		private class Tag extends JTextField{
			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 3546074761651042611L;
			public Tag(String s,Node n){
				super(s);
				this.n=n;
			}
			public Node getTag(){
				return n;
			}
			private Node n;
		}
	}
}
