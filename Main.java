package textmining_bibel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
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

public class Main {
	Parser parser;

	public static void main(String[] args) throws IOException {
		new Main().navi();
	}

	public void navi() throws IOException {
		String filePath = "iso88";
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("ISO-8859-15"));

		if (file.exists())
			this.parser = new Parser(inputStreamReader);
		else
			return;

		Scanner input = new Scanner(System.in);
		int auswahl;
		System.out.println();
		while (true) {
			System.out.println("#NAVI#\n 1 - Wortsuche \n 2 - Top-10 Wörter \n 3 - Exit \n Bitte geben Sie eine Zahl an:");
			auswahl = input.nextInt();

			switch (auswahl) {
			case 1:
				System.out.println("Bitte geben Sie das gesuchte Wort ein:");
				String w = input.next();
				int i = parser.woertersuche(w);
				System.out.println("Das Wort " + w + " wurde " + i + " mal in der Bibel gefunden.\n");
				break;

			case 2:
				System.out.println("Bitte geben Sie die Zahl der ersten Elemente ein:"); //burayi daha g�zel yaz istersen
				int j = input.nextInt();
				List<Entry<String, Integer>> list = parser.getTopWords(j);
				for (Entry<String, Integer> entry : list) {
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}
				System.out.println();
				break;

			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("Falsche Eingabe !");
				break;
			}
		}
	}

}
