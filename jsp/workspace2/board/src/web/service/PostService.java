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
			Connection con = DriverManager.getConnection(url,"board_admin","board_admin"); // 드라이버 매니저를 통해서 연결 객체 생성
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
	
	public boolean insertPost()
	{
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
