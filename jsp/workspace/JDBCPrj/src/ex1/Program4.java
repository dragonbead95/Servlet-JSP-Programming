package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 드라이버를 로드하게 되면 메모리 상에 잡히게 된다.
		String title = "test3";
		String writer_id = "newlec";
		String content = "hahaha3";
		String files = "";
		int id = 111;
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String sql = "delete notice where id = ?";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			
			int result = st.executeUpdate();
			
			System.out.println(result);
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
