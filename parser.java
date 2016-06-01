
package textmining_bibel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
 
public class bparser
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		String filePath = "iso88";
		File file = new File(filePath);
		if(file.isFile())
		{
			FileInputStream inputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("ISO-8859-15"));			
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder stringBuilder = new StringBuilder();
 
			stringBuilder.append(bufferedReader.readLine());
			while(bufferedReader.ready())
			{
				stringBuilder.append("\r\n");
				stringBuilder.append(bufferedReader.readLine());				
			}
 
			System.out.println(stringBuilder.toString());
		}				
	}
}
