/**Classe principale
* @author SaThot
* @version 0.8
* @(#)Version.java*/
package org.macrobug.util;
/**
* Semplice classe che definisce la versione del software
*/
public class Version{

	/**Numero di versione*/
	private static final String v="MacroBug's Utility 0.8";

	/**
	* Stampa a termianle la versione corrente
	* @see #get
	*/
	public static void main(String s[]){
		System.out.println(v);
		System.out.println("\nNecessita di del seguente albero di cartelle");
		System.out.println("|_util");
		System.out.println("|_resources");
		System.out.println("  |_image");
	}

	/**
	* Restituisce la versione corrente
	* @return Il numero di versione
	*/
	public static String get(){
		return v;
	}
}
