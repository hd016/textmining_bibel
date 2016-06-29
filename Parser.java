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

		Pattern p = Pattern
				.compile("([A-Z][a-z]{2,2}|[1-9][A-Z][a-z]) [1-9][0-9]*:[1-9][0-9]*");

		while (bufferedReader.ready()) {
			s = bufferedReader.readLine();
			Matcher m = p.matcher(s);

			stringBuilder.append(m.replaceAll(""));
		}

		String woerterArray[] = stringBuilder.toString()
				.replaceAll("[_[^\\w\\däüöÄÜÖ\\+\\- ]]", "").split(" ");
		for (int i = 0; i < woerterArray.length; i++) {
			if (woerter.containsKey(woerterArray[i]))
				woerter.put(woerterArray[i], woerter.get(woerterArray[i]) + 1);
			else
				woerter.put(woerterArray[i], 1);
		}

		String saetzeArray[] = stringBuilder.toString().split("\\.|!|\\?");
		for (int i = 0; i < saetzeArray.length; i++) {
			if (saetze.containsKey(saetzeArray[i]))
				saetze.put(saetzeArray[i], saetze.get(saetzeArray[i]) + 1);
			else
				saetze.put(saetzeArray[i], 1);
		}
	}

	public int woertersuche(String wort) {
		if (woerter.containsKey(wort))
			return woerter.get(wort).intValue();
		return 0;
	}

	// -----------------MITHILFE VON ORKAN YÜCEDAG, UNI
	// KONSTANZ-------------------------------------\\

	public List<Map.Entry<String, Integer>> getTopWoerter(int i) {
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				woerter.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return list.subList(0, i);
		// ------------------------------------------------------------------------------------------------\\
	}

	public List<Map.Entry<String, Integer>> getTopSaetze(int i) {
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				saetze.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return list.subList(0, i);
	}

	public String kuerzesterSatz() {
		ArrayList<String> list = new ArrayList<String>(saetze.keySet());
		String kuerzesterSatz = list.get(list.size() - 1);
		for (String s : list) {
			if (s.length() > 2 && s.length() < kuerzesterSatz.length())
				kuerzesterSatz = s;
		}

		return kuerzesterSatz;
	}

	public String laengsterSatz() {
		ArrayList<String> list = new ArrayList<String>(saetze.keySet());
		String laengsterSatz = list.get(list.size() - 1);
		for (String s : list) {
			if (s.length() > 2 && s.length() > laengsterSatz.length())
				laengsterSatz = s;
		}

		return laengsterSatz;
	}

}
