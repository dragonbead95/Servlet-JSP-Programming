package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 드라이버를 로드하게 되면 메모리 상에 잡히게 된다.
		String title = "test2";
		String writer_id = "newlec";
		String content = "hahaha";
		String files = "";
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String sql = "INSERT INTO notice (title, writer_id, content, files) VALUES (?,?,?,?)";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title); // id:0, title:1 ...
			st.setString(2, writer_id);
			st.setString(3, content);
			st.setString(4, files);
			
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
