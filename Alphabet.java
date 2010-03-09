package Varenne;

public class Alphabet{
	private final char[] normal="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private byte[] codice;
	
	public Alphabet(char c) throws IllegalArgumentException{
		codice=new byte[26];
		int j,i=Character.toLowerCase(c);
		if(i<'a'||i>'z')
			throw new IllegalArgumentException(""+c);
		for(j=0;i<='z';i++,j++)
			codice[j]=(byte)i;
		for(i='a';j<26;i++,j++)
			codice[j]=(byte)i;
	}
	public Alphabet(byte b){
		codice=new byte[256];
		byte i=b;
		int j;
		for(j=0;j<codice.length;i++,j++)
			codice[j]=i;
	}
	
	public char getChar(char C){
		if(!World.isLetter(C))
			return C;
		int i;
		for(i=0;i<52;i++)
			if(normal[i]==C)
				break;
		return (char)((i<26)?codice[i]:Character.toUpperCase((char)codice[i-26]));
	}
	public byte getChar(byte C){
		return codice[(C>0)?C:256+C];
	}
	public byte getCod(byte b){
		return (byte)func(b);
	}
	public char getCod(char C){
		if(!World.isLetter(C))
			return C;
		byte c=(byte)Character.toLowerCase(C);
		int i=func(c);
		return (Character.isLowerCase(C))?normal[i]:normal[i+26];
	}
	private int func(byte c){
		int i;
		for(i=0;i<codice.length;i++)
			if(codice[i]==c)
				break;
		return i;
	}
	public static void main(String s[]){
		Alphabet a;
		byte[] b=new byte[]{1,9,15};
		a=new Alphabet(b[0]);
		System.out.println(""+a.getChar(b[1])+" "+a.getChar(b[2]));
		System.out.println(""+a.getCod(b[1])+" "+a.getCod(b[2]));
		a=new Alphabet('b');
		System.out.println(""+a.getChar('A')+" "+a.getChar('b'));
		System.out.println(""+a.getCod('A')+" "+a.getCod('b'));
	}
	public String toString(){
		return ""+(char)codice[0];
	}
}
