package adapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoadFromFollower {
	public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/tag";
	public static final String DBUSER = "root";
	public static final String DBPASS = "";

	public static void main(String args[]) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT CompanyName,FollowerID,Tags from follower";
		Class.forName(DBDRIVER);
		conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		FileWriter fw = new FileWriter("followerTags.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		while (rs.next()) {
			String companyName = rs.getString("CompanyName");
			String followerID = rs.getString("FollowerID");
			String tags = rs.getString("Tags");
			System.out.println("companyName = " + companyName + "£»");
			System.out.println("ID = " + followerID + "£»");
			System.out.println("tags" + tags + "£»");
			String output = companyName + "," + followerID + "," + tags;
			bw.write(output);
			if (!rs.isLast()) {
				bw.write("\n");
			}
		}
		bw.close();
		fw.close();
		rs.close();
		stmt.close();
		conn.close();
	}
}
