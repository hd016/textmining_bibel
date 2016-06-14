package textmining_bibel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * TEXTMINING PROJEKT:
 *  - ENTFERNEN DER BUCHNAMEN UND VERSNUMMERN - OK
 * ANALYSE DER BIBEL,ÜBER:
 * 	- TOP 10 WÖRTER
 *  - TOP 10 2-WORT PHASEN, 3,4,..,n
 *  - WÖRTERVERGLEICHE
 *  - WÖRTER/PAARE - FAMILIEN 
 *  - POSITIV/NEGATIV
 *  
 *  TOOLS:
 *  HASH-MAP
 *  TFI
 *  SPACEVEKTORMODELL
 */
 
public class bparser


{
	static map localWordMap = new map();


    public static void main(String[] args) throws FileNotFoundException,
                IOException {
          navi();
    }

    public static int navi() throws FileNotFoundException, IOException{
	
		System.out.println("#NAVI#\n 1 - Wortsuche \n 2 - Wortsuche \n Bitte geben Sie eine Zahl an:");
		int auswahl;
		Scanner input = new Scanner(System.in);
		auswahl = input.nextInt();
		System.out.println();
		auswahl_in(auswahl);
		return auswahl;
		
	}
	
	private static int auswahl_in(int auswahl) throws FileNotFoundException, IOException {
		
		int auswahl_in;
		auswahl_in = auswahl;
		if(auswahl_in == 1)
		{
			wortersuche();
		}
		else if(auswahl_in == 2)
		{
			//gorevli();
			
		}
		else if(auswahl_in == 3)
		{
			System.out.println("null");
		}
		else 
		{
			System.out.println("null");
			auswahl_in(auswahl);
		}
		return auswahl_in;
	}
    
   
	 public static void wortersuche() throws FileNotFoundException, IOException{

         HashMap<String, Integer> wordMap = localWordMap.parse();
         Scanner input = new Scanner(System.in);
	  		String wort = null;
	  		wort = input.nextLine();
	  		
	  		System.out.println(wordMap.get(wort));
 
	 }
	  		
	 }
	 

