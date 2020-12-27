package web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberService {
	public boolean Login(String id, String password)
	{
		boolean answer = false;
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String sql = "select * from member where id=? and pwd=?";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"board_admin","board_admin"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			
			rs.next();
			
			String id_ = rs.getString("id");
			String pwd_ = rs.getString("pwd");
			
			if(id.equals(id_) && password.equals(pwd_))
			{
				//�α��� ����
				answer = true;
			}

			rs.close();
			pst.close();
			con.close();	
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return answer;
	}
	
	public void Register(String id, String password)
	{
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String sql = "insert into member values(?,?)";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"board_admin","board_admin"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, password);
			pst.executeUpdate();
			
			pst.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
