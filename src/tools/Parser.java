package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		doParse(getFileContent());

	}
	
	public static String getFileContent() throws Exception{
		File f = new File("./aa.html");
		
	    BufferedReader br = new BufferedReader(new FileReader(f));

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append('\n');
            line = br.readLine();
        }
        br.close();
        
        return sb.toString();
	}
	
	public static void doParse(String str) throws Exception{
	
		//System.out.println(str);
		
	      // String to be scanned to find the pattern.
	      String pattern = "<li class=\"feed-item\">.*</li>";
	      
	      Pattern p = Pattern.compile(pattern);
	      Matcher m = p.matcher(str);

	      int count = 0;
	      while(m.find()) {
	         System.out.println(m.group(0));
	         count++;
	      }
	      System.out.println(count);
	}
	

}
