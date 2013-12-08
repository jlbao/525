package tools;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class XmlLoad {

	public static void main(String[] args) throws Exception{
		
		loadXml("");
		
	}
	
	
	public static void loadXml(String content) throws Exception{
		
		File f = new File("./aa.html");
		
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc = builder.parse(f);
         XPathFactory xPathfactory = XPathFactory.newInstance();
         XPath xpath = xPathfactory.newXPath();
         XPathExpression expr = xpath.compile("/li");
         System.out.println((String)expr.evaluate(doc, XPathConstants.STRING));
         
	}
	
}
