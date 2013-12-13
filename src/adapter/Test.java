package adapter;

public class Test {
	public static final String DBDRIVER="org.gjt.mm.mysql.Driver";
	public static void main(String args[]){
		try{
			Class.forName(DBDRIVER);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

}
