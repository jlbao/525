package config;

import java.util.ArrayList;

import tools.Parser;


public class Company {
	public String companyName;
	public String companyURL;
	public int currentPage;
	public int taskNum;
	
	public Company(String companyName){
		this.companyName = companyName;
		this.companyURL = String.format("http://www.linkedin.com/company/%s/followers", this.companyName);
		this.currentPage = 1;
	}
	
	public boolean increaseTaskNum(){
		taskNum++;
		if(taskNum > Config.taskNumPerCompany)
			return false;
		return true;
	}

	public void increaseCurrentPage(){
		this.currentPage++;
	}
	
	
	public String getCurrentURL() {
		return String.format("http://www.linkedin.com/company/%s/followers?page_num=%s", this.companyName, this.currentPage);
	}
	
	// use regular expression to put the tasks into the task queue
	public void putTasks(String pageContent){
		// parse
		try{
			ArrayList<String> list = Parser.doFollowerListParse(pageContent);
			for(String url : list){
				Task t = new Task(url);
				Config.taskQueue.add(t);
				this.taskNum++;
				// log
				System.out.println(url);
			}
			increaseCurrentPage();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
