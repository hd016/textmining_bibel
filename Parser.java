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
	private HashMap<String, Integer> map;
	private StringBuilder stringBuilder;
	private BufferedReader bufferedReader;

	public Parser(InputStreamReader reader) throws IOException {
		map = new HashMap<String, Integer>();

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

			stringBuilder.append(m.replaceAll("") + "\n");
		}

		String text[] = stringBuilder.toString().replaceAll("[_[^\\w\\däüöÄÜÖ\\+\\- ]]", "").split(" ");
		for (int i = 0; i < text.length; i++) {
			if (map.containsKey(text[i]))
				map.put(text[i], map.get(text[i]) + 1);
			else
				map.put(text[i], 1);
		}
	}

	public int woertersuche(String wort) {
		if (map.containsKey(wort))
			return map.get(wort).intValue();
		return 0;
	}

	//-----------------MITHILFE VON ORKAN YÜCEDAG, UNI KONSTANZ-------------------------------------\\

	public List<Map.Entry<String, Integer>> getTopWords(int i) {
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				map.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return list.subList(0, i);
   //------------------------------------------------------------------------------------------------\\
	}

}
