package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����̹��� �ε��ϰ� �Ǹ� �޸� �� ������ �ȴ�.
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String sql = "select * from notice where hit>10";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			Statement st = con.createStatement(); // ���� ���� ����
			ResultSet rs = st.executeQuery(sql); // ���� ������ ����� �� fetch�ؿü� �ְ���
			
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writerId = rs.getString("writer_id");
				Date regDate = rs.getDate("regdate");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");

				System.out.printf("id :%d, title:%s, writerId:%s, regDate:%s, content:%s, hit:%d\n", id,title,writerId,regDate,content,hit);
				
			}
			
			
			
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
