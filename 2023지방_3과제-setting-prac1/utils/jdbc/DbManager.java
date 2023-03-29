package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Iterator;
import java.util.Vector;

public class DbManager {

	
	private String url = "jdbc:mysql://localhost/?"
			+ "CharacterEncoding=UTF-8&"
			+ "serverTimezone=UTC&"
			+ "allowPublicKeyRetrieval=true&"
			+ "allowLoadLocalInfile=true&"
			+ "allowMultiQueries=true&";
	private String id = "root";
	private String pw = "1234";
	private Connection con;
	private PreparedStatement pstmt;
	public static DbManager db = new DbManager();
	public DbManager() {
		// TODO Auto-generated constructor stub
		try {
			con = DriverManager.getConnection(url,id,pw);
			System.out.println("연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("연결 실패");
		}
	}
	public int setData(String sql,Object...val) {
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < val.length; i++) {
				pstmt.setObject(i+1, val[i]);
			}
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("setData Faild");
			return -1;
		}
	}
	
	public Vector<Vector<String>> getData(String sql,Object...val) {
		Vector<Vector<String>> data = new Vector<>();
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < val.length; i++) {
				pstmt.setObject(i+1, val[i]);
			}
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					row.add(rs.getString(i+1));
				}
				data.add(row);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("setData Faild");
			return null;
		}
		return data;
	}
	
}
