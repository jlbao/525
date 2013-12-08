package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

	/**
	 * parse data from the pages we crawled
	 */

	// return the user urls
	public static ArrayList<String> doFollowerListParse(String str) throws Exception{
		String regex = "<p class=\"fn n\"><a href=\"/profile/view\\u003Fid=([0-9a-zA-Z]+)&amp;authType=name&amp;authToken=([0-9a-zA-Z]+)";
        ArrayList<String> list = new ArrayList<String>();
        
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);
        
        while (matcher.find()) {
        	String userID = matcher.group(1);
        	String token = matcher.group(2);
        	String url = String.format("http://linkedin.com/profile/view?id=%s&authType=name&authToken=%s&trk=hb_upphoto", userID, token);
            list.add(url); 
        }  
        return list;
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

}
