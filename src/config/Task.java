package config;

import java.util.ArrayList;

public class Task {
	public String userID;
	public String taskURL;
	public ArrayList<String> tags;
	
	public Task(String userID, String taskURL){
		this.userID = userID;
		this.taskURL = taskURL;
		tags = new ArrayList<String>();
	}
	
	
	// parse the page content into properties
	public void parse(String pageContent){
		
	}
	
}
