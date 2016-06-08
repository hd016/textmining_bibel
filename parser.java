package textmining_bibel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
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
	public static void main(String[] args) throws 
    IOException {
		
		navi();
}

	public static int navi() throws FileNotFoundException, IOException{
		
		System.out.println("#NAVI#\n 1 - Parsen \n 2 - Wortsuche \n Bitte geben Sie eine Zahl an:");
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
			parsen();
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
	
	public static String parsen() throws IOException, FileNotFoundException {
		
		String filePath = "iso88";
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);	
		
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("ISO-8859-15"));
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuilder stringBuilder = new StringBuilder();
		String s = "";
		
		Pattern p = Pattern.compile("([A-Z][a-z]{2,2}|[1-9][A-Z][a-z]) [1-9][0-9]*:[1-9][0-9]*");
		
		if (file.exists()) {
			
		  while (bufferedReader.ready()) {
          s = bufferedReader.readLine();
          Matcher m = p.matcher(s);
          
          stringBuilder.append(m.replaceAll("") + "\n");
    }
		    //System.out.println(stringBuilder.toString());
		  
		  wortersuche(stringBuilder.toString());
				
		
	}
		return stringBuilder.toString();
	}
	
	public static String wortersuche(String text){
		
		HashMap<String, Integer> hash = new HashMap<>();

    	// text = text.replaceAll("[^a-zA-Z 0-9]", ""); //OHNE UMLAUTE
    	// "[_[^\\w\\däüöÄÜÖ\\+\\- ]]", ""				// MIT UMLAUTE
		
    	text = text.replaceAll("[_[^\\w\\däüöÄÜÖ\\+\\- ]]", "");

    	// ^/(?!ignoreme|ignoreme2|ignoremeN) 
	    String [] tokens = text.split("[\\s']");
		Integer value = 0;

        for(String s:tokens){
        	if(hash.containsKey(s)){
        		s.indexOf(value);
        	}
        	else{
            	hash.put(s,value);

        	}
	}
		System.out.println(hash.size());
		System.out.println(hash);
        
		return text;
		}
	
	
	}
