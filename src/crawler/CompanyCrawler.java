package crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import config.Config;

public class CompanyCrawler implements Runnable{

	@Override
	public void run() {
		while(!Config.companyQueue.isEmpty()){
			while(Config.currentCompany.taskNum < Config.taskNumPerCompany){ // if the company tasknum is not enough
				try{
					String currentTasksPage = getCurrentTasksPage();
					
					// put into the tasks
					Config.currentCompany.putTasks(currentTasksPage);
					
					System.out.println(currentTasksPage);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			// need to go to next company
			Config.currentCompany = Config.companyQueue.poll();
		}
	}
	
	public String getCurrentTasksPage() throws Exception{
		HttpURLConnection conn = Config.getHttpConnection(Config.currentCompany.getCurrentURL());
      	BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      	String line;
      	StringBuilder sb = new StringBuilder();
	    while ((line = rd.readLine()) != null) {
	       sb.append(line);
	    }
        rd.close();
        return sb.toString();
	}
	
}
