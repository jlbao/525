package main;

import config.Config;
import crawler.CompanyCrawler;
import crawler.TaskCrawler;


public class Main {

	public static void main(String[] args){
		Config.config();
		Config.Pool.execute(new CompanyCrawler());
		startTaskThreads(1);
	}
	
	public static void startTaskThreads(int num){
		for(int i = 0; i < num; i++){
			Config.Pool.execute(new TaskCrawler());
		}
	}	
}
