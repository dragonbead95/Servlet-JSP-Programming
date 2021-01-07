package web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import web.entity.Post;

public class PostService {
	public List<Post> getPostList()
	{
		List<Post> list = new ArrayList<Post>();
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String sql = "select * from post";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"board_admin","board_admin"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			
			while(rs.next())
			{
				Post post = new Post(
										rs.getInt("id"),
										rs.getString("title"),
										rs.getString("writer_id"),
										rs.getString("content"),
										rs.getDate("regdate"),
										rs.getInt("hit"),
										rs.getString("files")
									);
				
				list.add(post);
			}
			
			rs.close();
			pst.close();
			con.close();	
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean insertPost(String title, String writer_id, String content, String files)
	{
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String sql = "INSERT INTO post (title, writer_id, content, files) values(?,?,?,?)";
					
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"board_admin","board_admin"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(0, title);
			pst.setString(1, writer_id);
			pst.setString(2, content);
			pst.setString(3, files);
			pst.executeUpdate();
			
			pst.close();
			con.close();	
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updatePost()
	{
		return false;
	}
	
	public boolean deletePost()
	{
		return false;
	}
}
