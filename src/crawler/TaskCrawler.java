package crawler;

import tools.HttpOperation;
import tools.Parser;
import config.Config;
import config.Task;

public class TaskCrawler implements Runnable{

	// crawl the tags for each user, and put them into the store queue
	
	@Override
	public void run() {
		while(true){
			Task task = Config.TaskQueue.poll();
			if(task != null){
				try {
					String content = HttpOperation.getPageContent(task.taskURL);
					task.tags = Parser.getTags(content);
					
					log(task);
					
					Config.StoreQueue.add(task);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// logs
	public void log(Task task){
		System.out.print(task.userID + " ");
		for(String val : task.tags){
			System.out.print(val + " ");
		}
		System.out.println();
	}
	
}
