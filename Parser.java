package textmining_bibel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	private HashMap<String, Integer> woerter;
	private HashMap<String, Integer> saetze;
	private StringBuilder stringBuilder;
	private BufferedReader bufferedReader;

	public Parser(InputStreamReader reader) throws IOException {
		woerter = new HashMap<String, Integer>();
		saetze = new HashMap<String, Integer>();

		bufferedReader = new BufferedReader(reader);
		this.stringBuilder = new StringBuilder();
		parse();
	}

	private void parse() throws IOException {
		String s = "";

		Pattern p = Pattern.compile("([A-Z][a-z]{2,2}|[1-9][A-Z][a-z]) [1-9][0-9]*:[1-9][0-9]*");

		while (bufferedReader.ready()) {
			s = bufferedReader.readLine();
			Matcher m = p.matcher(s);

			stringBuilder.append(m.replaceAll(""));
		}

		
		
		/** erstelle filter, für weniger interssante aber häufig vorkommende Wörter*/
		ArrayList<String> filter = new ArrayList<String>();
		String[] filterA = new String[] { "und", "der", "die", "zu", "sie",
				"den", "dass", "das", "Und", "er", "nicht", "in", "des", "dem",
				"ist", "von", "mit", "auf", "aber", "ein", "an", "es", "so",
				"wird","denn","sich","sein","da","wie","hat","vor","werden","war","aus","über","auch","sind","aus",
				"Da","ich","euch","ihn", "will","seine", "soll","sprach", "mir","was", "du","ihr","ihm","im" };
		for (int i = 0; i < filterA.length; i++)
			filter.add(filterA[i]);
		
		
		/** Füllen der Wörter in die Map mit der entsprechenden Häufigkeit.
		 * (<Wort>,<Häufigkeit>)*/
		
		s = stringBuilder.toString();
		s.replaceAll("[_[^\\w\\däüöÄÜÖ\\+\\- ]]", ""); //ersetze alle nicht Buchstaben
		
		String woerterArray[] = s.split(" ");// splitte bei jedem Leerzeichen -> Entstehung der Wörter
		
		
		for (int i = 0; i < woerterArray.length; i++) {
			if(!filter.contains(woerterArray[i])) //schaue ob Wort im Filter
				if (woerterArray[i].length() > 5)
				if (woerter.containsKey(woerterArray[i]))//wenn Wort bereits in der Map
					woerter.put(woerterArray[i], woerter.get(woerterArray[i]) + 1); //zähle die Häufigkeit hoch
				else
					woerter.put(woerterArray[i], 1); //wenn Wort noch nicht in der Map, füge es ein und setze Häufigkeit auf 1
		}

		/** Füllen der Sätze in die Map mit der entsprechenden Häufigkeit.
		 * (<Satz>,<Häufigkeit>)*/
		String saetzeArray[] = stringBuilder.toString().split("\\.|!|\\?"); //Satz endet mit Punkt, Ausrufzeichen oder Fragezeichen
		for (int i = 0; i < saetzeArray.length; i++) {
			if (saetze.containsKey(saetzeArray[i]))
				saetze.put(saetzeArray[i], saetze.get(saetzeArray[i]) + 1);
			else
				saetze.put(saetzeArray[i], 1);
		}
	}

	/** liefert die Häufigkeit, des entsprechenden Wortes*/
	public int woertersuche(String wort) {
		if (woerter.containsKey(wort))
			return woerter.get(wort).intValue();
		return 0;
	}

	/** erstelle eine Liste von Entrys aus der Map, um sie mit der ArrayList zu sortieren*/
	/*	 Integer i = 2;
	 Integer k = 3;
	 i.compareTo(k); //Ausgabe -1
	 // compareTo bei Integer Typ. Vergleich der Werte
	 http://stackoverflow.com/questions/2839137/how-to-use-comparator-in-java-to-sort
	  * 
	  * 
*/
	public List<Map.Entry<String, Integer>> getTopWoerter(int i) {
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(woerter.entrySet());

		//http://stackoverflow.com/questions/19682818/collections-sort-using-comparator
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return list.subList(0, i);
	}


	public List<Map.Entry<String, Integer>> getTopSaetze(int i) {
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(saetze.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return list.subList(0, i);
	}

	public String kuerzesterSatz() {
		ArrayList<String> list = new ArrayList<String>(saetze.keySet());
		String kuerzesterSatz = list.get(list.size() - 1); // start letzte Stelle
		for (String s : list) {
			if (s.length() > 2 && s.length() < kuerzesterSatz.length())
				kuerzesterSatz = s;
		}

		return kuerzesterSatz;
	}

	public String laengsterSatz() {
		ArrayList<String> list = new ArrayList<String>(saetze.keySet());
		String laengsterSatz = list.get(list.size() - 1); // start letzte Stelle
		for (String s : list) {
			if (s.length() > laengsterSatz.length())
				laengsterSatz = s;
		}

		return laengsterSatz;
	}

}
 
