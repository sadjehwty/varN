package org.macrobug.varn;
public class World{
	
	private Alphabet string[];
	private int index=0,len;
	
	public void reset(){
		index=0;}
	
	public void inc(){
		index++;}
		
	public void dec(){
		index--;}
	
	public static boolean isLetter(char c){
		return (c>='\u0041'&&c<='\u005a')||(c>='\u0061'&&c<='\u007a');
	}
	
	public boolean isValid(String s){
		int i=s.length()-1;
		for(;i>=0;--i)
			if(!isLetter(s.charAt(i)))
				break;
		return i==-1;
	}
	public World(String s){
		if(!isValid(s))
			throw new IllegalArgumentException(s);
		len=s.length();
		string=new Alphabet[len];
		for(int i=0;i<string.length;i++)
			string[i]=new Alphabet(s.charAt(i));
	}
	public World(byte[] b){
		string=new Alphabet[b.length];
		len=b.length;
		for(int i=0;i<b.length;i++)
			string[i]=new Alphabet(b[i]);
	}
	public String trad(String s,boolean b){
		StringBuilder temp=new StringBuilder();
		for(int i=0;i<s.length();i++){
			if(!b)
				temp.append(string[index%len].getChar(s.charAt(i)));
			else
				temp.append(string[index%len].getCod(s.charAt(i)));
			if(World.isLetter(s.charAt(i)))
				inc();
		}
		return temp.toString();
	}
	public byte trad(byte B,boolean b){
		byte ret;
		if(!b)
			ret=string[index%len].getChar(B);
		else
			ret=string[index%len].getCod(B);
		inc();
		return ret;
	}
	public static void main(String s[]){
		World w=new World("wolias");
		System.out.println(w.trad("wolias",true));
	}
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(Alphabet a:string)
			sb.append(a+"\n");
		return sb.toString();
	}
}
