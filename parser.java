package textmining_bibel;
import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileReader; 
import java.io.IOException; 

public class parser {


	    private static void ladeDatei(String datName) { 

	        File file = new File(datName); 

	        if (!file.canRead() || !file.isFile()) 
	            System.exit(0); 

	            BufferedReader in = null; 
	        try { 
	            in = new BufferedReader(new FileReader(datName)); 
	            String zeile = null; 
	            while ((zeile = in.readLine()) != null) { 
	                System.out.println(zeile); 
	            } 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } finally { 
	            if (in != null) 
	                try { 
	                    in.close(); 
	                } catch (IOException e) { 
	                } 
	        } 
	    } 

	    public static void main(String[] args) { 
	        String dateiName = "Martin_Luther_Uebersetzung_1912.txt"; 
	        ladeDatei(dateiName); 
	    } 
	}



