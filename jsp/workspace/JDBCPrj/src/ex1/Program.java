package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����̹��� �ε��ϰ� �Ǹ� �޸� �� ������ �ȴ�.
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String sql = "select * from notice";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			Statement st = con.createStatement(); // ���� ���� ����
			ResultSet rs = st.executeQuery(sql); // ���� ������ ����� �� fetch�ؿü� �ְ���
			
			
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
