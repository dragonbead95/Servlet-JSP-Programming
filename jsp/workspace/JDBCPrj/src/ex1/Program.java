package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 드라이버를 로드하게 되면 메모리 상에 잡히게 된다.
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String sql = "select * from notice";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			Statement st = con.createStatement(); // 실행 도구 생성
			ResultSet rs = st.executeQuery(sql); // 쿼리 실행후 결과를 얻어서 fetch해올수 있게함
			
			
			if(rs.next()) {
				
				String name = rs.getString("title");
				System.out.printf("NAME:%s\n",name);	
			};
			
			
			
			rs.close();
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
