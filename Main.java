package textmining_bibel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException {		
		navi();
	}

	public static void navi() throws IOException {
		String filePath = "iso88";
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("ISO-8859-15"));

		Parser parser;
		if (file.exists())
			parser = new Parser(inputStreamReader);
		else
			return; //bricht ab, wenn die Datei nicht existiert

		Scanner input = new Scanner(System.in);
		int auswahl;
		System.out.println();
		while (true) {
			System.out.println("#NAVI#\n 1 - Wortsuche \n 2 - Meist auftrende Wörter \n"
					+ " 3 - Meist auftretende Sätze \n 4 - Kürzester Satz "
					+ "\n 5 - Längster Satz \n 6 - Exit \n Bitte geben Sie eine Zahl an:");
			auswahl = input.nextInt();

			switch (auswahl) {
			case 1:
				System.out.println("Bitte geben Sie das gesuchte Wort ein:");
				String w = input.next();
				int i = parser.woertersuche(w);
				System.out.println("Das Wort " + w + " wurde " + i + " mal in der Bibel gefunden.\n");
				break;

			case 2:
				System.out.println("Bitte geben Sie die Zahl der ersten Elemente ein:");
				int j = input.nextInt();
				List<Entry<String, Integer>> woerterListe = parser.getTopWoerter(j);
				for (Entry<String, Integer> entry : woerterListe) {
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}
				System.out.println();
				break;
			case 3:
				System.out.println("Bitte geben Sie die Zahl der ersten Elemente ein:");
				int k = input.nextInt();
				List<Entry<String, Integer>> saetzeListe = parser.getTopSaetze(k);
				for (Entry<String, Integer> entry : saetzeListe) {
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}
				System.out.println();
				break;
			case 4:
				String kuerzesterSatz = parser.kuerzesterSatz();
				System.out.println("\""+kuerzesterSatz+"\" "+ kuerzesterSatz.length() +" Zeichen");
				break;
			case 5:
				String laengsterSatz = parser.laengsterSatz();
				System.out.println("\""+laengsterSatz+"\" "+ laengsterSatz.length() +" Zeichen");
				break;
			case 6:
				input.close();
				System.exit(0);
				break;
			default:
				System.out.println("Falsche Eingabe !");
				break;
			}
		}
	}

}
