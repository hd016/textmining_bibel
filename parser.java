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
	public static HashMap<String, Integer> wordMap = new HashMap<>();
	 
    public static void main(String[] args) throws FileNotFoundException,
                IOException {
          parse();
    }

    public static void parse() throws IOException, FileNotFoundException {

          String filePath = "iso88";
          File file = new File(filePath);
          FileInputStream inputStream = new FileInputStream(file);

          InputStreamReader inputStreamReader = new InputStreamReader(
                      inputStream, Charset.forName("ISO-8859-15"));
          BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
          StringBuilder stringBuilder = new StringBuilder();
          String s = "";

          Pattern p = Pattern
                      .compile("([A-Z][a-z]{2,2}|[1-9][A-Z][a-z]) [1-9][0-9]*:[1-9][0-9]*");

          if (file.exists()) {

                while (bufferedReader.ready()) {
                      s = bufferedReader.readLine();
                      Matcher m = p.matcher(s);

                      stringBuilder.append(m.replaceAll("") + "\n");
                }
          }
          String text[] = stringBuilder.toString()
                      .replaceAll("[_[^\\w\\däüöÄÜÖ\\+\\- ]]", "").split(" ");
          for (int i = 0; i < text.length; i++) {
                if (wordMap.containsKey(text[i]))
                      wordMap.put(text[i], wordMap.get(text[i]) + 1);
                else
                      wordMap.put(text[i], 1);
          }
          System.out.println(wordMap.toString());
    }

}
