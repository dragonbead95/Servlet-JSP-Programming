package com.newlecture.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.app.entity.Notice;

public class NoticeService {
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String uid = "newlec";
	private String pwd = "newlec";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	public List<Notice> getList(int page)
	{
		int start = 1 + (page-1)*10; // 1 11 21 31...
		int end = page*10; // 10 20 30 40,
		List<Notice> list = new ArrayList<Notice>();
		try {
			String sql = "select * from (" + 
					"                select rownum num, n.* from (" + 
					"                                                    select * from notice order by regdate desc" + 
					"                                            ) n" + 
					"            )" + 
					"where num between ? and ?";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,uid,pwd); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, start);
			st.setInt(2, end);
			ResultSet rs = st.executeQuery(); // 쿼리 실행후 결과를 얻어서 fetch해올수 있게함
		
			
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writerId = rs.getString("writer_id");
				Date regDate = rs.getDate("regdate");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				Notice notice = new Notice(id, title, writerId, regDate, content, hit,files);
				
				list.add(notice);
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
		return list;
	}
	
	public int insert(Notice notice)
	{
		String title = notice.getTitle();
		String writer_id = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
		int result = 0;
		try {
			String sql = "INSERT INTO notice (title, writer_id, content, files) VALUES (?,?,?,?)";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,uid,pwd); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title); // id:0, title:1 ...
			st.setString(2, writer_id);
			st.setString(3, content);
			st.setString(4, files);
			
			result = st.executeUpdate();
					
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int update(Notice notice)
	{
		String title = notice.getTitle();
		String writer_id = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		int result = 0;
		try {
			String sql = "update notice set title=?, content=?, files=? where id=?";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,uid,pwd); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title); // id:0, title:1 ...
			st.setString(2, content);
			st.setString(3, files);
			st.setInt(4, id);
			
			result = st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int delete(Notice notice)
	{
		String title = notice.getTitle();
		String writer_id = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		int result = 0;
		try {
			String sql = "delete notice where id = ?";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,uid,pwd); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			
			result = st.executeUpdate();
			
			
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
