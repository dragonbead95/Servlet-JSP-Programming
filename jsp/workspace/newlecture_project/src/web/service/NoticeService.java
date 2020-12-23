package web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

import web.entity.Notice;
import web.entity.NoticeView;

public class NoticeService {
	public int removeNoticeAll(int[] ids)
	{
		return 0;
	}
	
	public int pubNoticeAll(int[] ids)
	{
		return 0;
	}
	
	public int insertNotice(Notice notice)
	{
		return 0;
	}
	
	public int deleteNotice(int id)
	{
		return 0;
	}
	
	public int updateNotice(Notice notice)
	{
		return 0;
	}
	
	public List<Notice> getNoticeNewestList()
	{
		return null;
	}
	
	
	public List<NoticeView> getNoticeList()
	{
		return getNoticeList("title", "", 1); // �⺻�� page 1
	}
	public List<NoticeView> getNoticeList(int page)
	{
		//3���� getNoticeList�� ���� �����ϸ� ������ ����Ƿ� �Ű������� ���� ���� 3��° getNoticeList��
		//�ϴ� �������� �����Ѵ�.
		return getNoticeList("title", "", page);
	}
	public List<NoticeView> getNoticeList(String field/*title, writer_id*/, String query/*A*/, int page)
	{
		List<NoticeView> list = new ArrayList<NoticeView>();
		String sql = "select * from (" + 
				"    select rownum num, N.* " + 
				"    from (select * from notice_view where "+field+" like ? order by regdate desc) N " + 
				") " + 
				"where num between ? and ?";
		
		// 1, 11, 21, 31 -> an = 1 + (page-1)*10
		// 10, 20, 30, 40 -> page*10
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try 
		{	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			PreparedStatement st = con.prepareStatement(sql); // ���� ���� ����
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			ResultSet rs = st.executeQuery(); // ���� ������ ����� �� fetch�ؿü� �ְ���
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				//String content = rs.getString("content");
				int cmtCount = rs.getInt("cmt_count");
				NoticeView notice = new NoticeView(
											id,
											title,
											writer_id,
											regdate,
											hit,
											files,
											//content,
											cmtCount
											);
				list.add(notice);
				
			}
			
			rs.close();
			st.close();
			con.close();
		}catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public int getNoticeCount()
	{
		return getNoticeCount("title", "");
	}
	public int getNoticeCount(String field, String query)
	{
		List<Notice> list = new ArrayList<Notice>();
		int count=0;
		String sql = "select count(id) count from (" + 
				"	select rownum num, N.* " + 
				"   from (select * from notice where "+field+" like ? order by regdate desc) N " + 
				")";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try 
		{	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			PreparedStatement st = con.prepareStatement(sql); // ���� ���� ����
			st.setString(1, "%"+query+"%");
			ResultSet rs = st.executeQuery(); // ���� ������ ����� �� fetch�ؿü� �ְ���
			
			if(rs.next())
			{
				count = rs.getInt("count");
			}
			
			
			rs.close();
			st.close();
			con.close();
		}catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public Notice getNotice(int id)
	{
		Notice notice = null;
		String sql = "select * from notice where id = ?";
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try 
		{	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			PreparedStatement st = con.prepareStatement(sql); // ���� ���� ����
			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); // ���� ������ ����� �� fetch�ؿü� �ְ���
			
			if(rs.next())
			{
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				
				notice = new Notice(
										nid,
										title,
										writer_id,
										regdate,
										hit,
										files,
										content
									);
			}
			
			rs.close();
			st.close();
			con.close();
		}catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}
	public Notice getNextNotice(int id)
	{
		Notice notice = null;
		String sql = "select * from notice " + 
				"	where id = ( " + 
				"   select id from notice " + 
				"   where regdate > (select regdate from notice where id=?) " + 
				"   and rownum=1 " + 
				") ";
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try 
		{	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			PreparedStatement st = con.prepareStatement(sql); // ���� ���� ����
			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); // ���� ������ ����� �� fetch�ؿü� �ְ���
			
			if(rs.next())
			{
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				
				notice = new Notice(
										nid,
										title,
										writer_id,
										regdate,
										hit,
										files,
										content
									);
			}
			
			rs.close();
			st.close();
			con.close();
		}catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}
	public Notice getPrevNotice(int id)
	{
		Notice notice = null;
		String sql = "select id from (select * from notice order by regdate desc) " + 
				"    where regdate < (select regdate from notice where id=?) " + 
				"    and rownum=1";
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try 
		{	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			PreparedStatement st = con.prepareStatement(sql); // ���� ���� ����
			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); // ���� ������ ����� �� fetch�ؿü� �ְ���
			
			if(rs.next())
			{
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				
				notice = new Notice(
										nid,
										title,
										writer_id,
										regdate,
										hit,
										files,
										content
									);
			}
			
			rs.close();
			st.close();
			con.close();
		}catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}
}
