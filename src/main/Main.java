package main;

import store.StoreTag;
import config.Config;
import crawler.CompanyCrawler;
import crawler.TaskCrawler;


public class Main {

	public static void main(String[] args){
		Config.config();
		Config.Pool.execute(new CompanyCrawler());
		startTaskThreads(20);
		startStoreThreads(1);
	}
	
	public static void startTaskThreads(int num){
		for(int i = 0; i < num; i++){
			Config.Pool.execute(new TaskCrawler());
		}
	}	
	
	public static void startStoreThreads(int num){
		for(int i = 0; i < num; i++){
			Config.Pool.execute(new StoreTag());
		}
	}	
	
}
