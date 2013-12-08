package main;

import config.Config;
import crawler.CompanyCrawler;


public class Main {

	public static void main(String[] args){
		Config.config();
		Config.pool.execute(new CompanyCrawler());
	}
	
}
