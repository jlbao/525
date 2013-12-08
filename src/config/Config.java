package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

import tools.ThreadPool;

public class Config {

	public final static HttpConfig httpConfig = new HttpConfig();
	public final static ConcurrentLinkedQueue<Company> companyQueue = new ConcurrentLinkedQueue<Company>();
	public final static ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<Task>();
	public final static int taskNumPerCompany = 1000;
	public final static ThreadPool pool = new ThreadPool(100, 200, 30);
	public static Company currentCompany;
	
	private Config(){}

	public static void config(){
		try {
			loadHttpConfig();
			getCompanyConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void loadHttpConfig() throws Exception{
		Properties p = new Properties();
		File f = new File("./HttpConfig.properties");
		p.load(new FileInputStream(f));
		httpConfig.accept = p.getProperty("Accept");
		httpConfig.cacheControl = p.getProperty("Cache-Control");
		httpConfig.connection = p.getProperty("Connection");
		httpConfig.cookie = p.getProperty("Cookie");
		httpConfig.host = p.getProperty("Host");
		httpConfig.referer = p.getProperty("Referer");
		httpConfig.userAgent = p.getProperty("User-Agent");
		httpConfig.connection = p.getProperty("Connection");
	}
	
	// store http config
	public static void saveHttpConfig(){
		Properties p = new Properties();
		File f = new File("./httpConfig.properties");
		try {
			String cookieString = "bcookie=\"v=2&e2179dd5-a078-45cc-86ad-cf1db8fae15a\"; __qca=P0-1060873076-1384747104000; visit=\"v=1&M\"; X-LI-IDC=C1; JSESSIONID=\"ajax:5963468495909667384\"; L1c=5d3f00e8; L1e=1e6383c; leo_auth_token=\"LIM:308896134:a:1386446766:ebd056ca40d0196183d17c6530a58c943a9fe7b7\"; lidc=\"b=LB06:g=12:u=1:i=1386446766:t=1386533166:s=2058880623\"; sdsc=22%3A1%2C1386446801825%7EMBR2%2C0ej8jJvkoXRxLnW982rLL52gZut4%3D; __utma=23068709.1230364730.1384747104.1384747104.1386446683.2; __utmb=23068709.18.10.1386446683; __utmc=23068709; __utmz=23068709.1384747104.1.1.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); __utmv=23068709.user; _lipt=\"0_06l969EPn0eD5MwGi1LnhAUp2BcUHzmLBSTwyTNqaN_Y9_XV0vJse6mb-1r3XMI29I_5ibHShkEg3dVQolwQWhbXofpVT-lPvlPYsSp525bNp4BaK7Gdeg6EllxBzEc0\"; L1l=67117164; RT=s=1386446983538&r=http%3A%2F%2Fwww.linkedin.com%2Fcompany%2Famazon%2Ffollowers%3Fpage_num%3D1; lang=\"v=2&lang=en-us&c=\"";
			p.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			p.put("Cache-Control", "max-age=0");
			p.put("Connection", "keep-alive");
			p.put("Cookie", cookieString);
			p.put("Host", "www.linkedin.com");
			p.put("Referer", "http://www.linkedin.com/company/google/followers?page_num=1&trk=extra_biz_followers");
			p.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
			p.store(new FileOutputStream(f), "httpRequest cookie data");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public synchronized static HttpURLConnection getHttpConnection(String urlString) throws Exception{
		return httpConfig.getHttpConnection(urlString);
	}
	
	// get company queue config
	static void getCompanyConfig(){
		companyQueue.add(new Company("amazon"));
		companyQueue.add(new Company("google"));
		companyQueue.add(new Company("microsoft"));
		companyQueue.add(new Company("linkedin"));
		companyQueue.add(new Company("facebook"));
		companyQueue.add(new Company("emc"));
		currentCompany = companyQueue.poll();
	}
	
}
