package adapter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class InsertIntoCompany {
	public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/tag";
	public static final String DBUSER = "root";
	public static final String DBPASS = "";

	public static void main(String args[]) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		Class.forName(DBDRIVER);
		conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		stmt = conn.createStatement();
		FileReader fr = new FileReader("tags.txt");
		BufferedReader br = new BufferedReader(fr);
		HashMap<String, String> map = new HashMap<String, String>();
		String str;
		while ((str = br.readLine()) != null) {
			String company = str.split("\t")[0];
			String tags = str.split("\t")[1];
			map.put(company, tags);
		}
		br.close();
		fr.close();
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		// String sql = "INSERT INTO company(CompanyName,TopTags)" +
		// "VALUES(?,?)";
		// PreparedStatement statement = conn.prepareStatement(sql);
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			String sql = "INSERT INTO company(CompanyName,TopTags) "
					+ " VALUES ('" + entry.getKey() + "','" + entry.getValue()
					+ "')";
			// statement.setString(1, entry.getKey());
			// statement.setString(2, entry.getValue());
			// statement.executeUpdate(sql);
			stmt.executeUpdate(sql);

		}
		// String sql = "INSERT INTO company(CompanyName,FollowerID,Tags) "
		// + " VALUES ('Microsoft','1','Java')";
		stmt.close();
		// statement.close();

		conn.close();
	}
}
