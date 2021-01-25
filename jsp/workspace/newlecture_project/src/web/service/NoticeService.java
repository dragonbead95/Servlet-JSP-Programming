package web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	public int pubNoticeAll(int[] oids, int[] cids)
	{
		List<String> oidsList = new ArrayList<String>();
		for(int i=0;i<oids.length;i++)
		{
			oidsList.add(String.valueOf(oids[i]));
		}
		
		List<String> cidsList = new ArrayList<String>();		
		for(int i=0;i<cids.length;i++)
		{
			cidsList.add(String.valueOf(cids[i]));
		}
		
		return pubNoticeAll(oidsList, cidsList);
	}
	
	public int pubNoticeAll(List<String> oids, List<String> cids)
	{
		String oidsCSV = String.join(",", oids);
		String cidsCSV = String.join(",", cids);
		return pubNoticeAll(oidsCSV, cidsCSV);
	}
	
	// "20, 30, 43,56"
	public int pubNoticeAll(String oidsCSV, String cidsCSV)
	{
		String sqlOpen = String.format("update notice set pub=1 where id in (%s)", oidsCSV);
		String sqlClose = String.format("update notice set pub=0 where id in (%s)", cidsCSV);
		
		int result = 0;
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try 
		{	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			Statement stOpen = con.createStatement(); // 실행 도구 생성
			result += stOpen.executeUpdate(sqlOpen);
			Statement stClose = con.createStatement(); // 실행 도구 생성
			result += stClose.executeUpdate(sqlClose);
			
			
			stOpen.close();
			stClose.close();
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
		return result;
	}
		
	
	public int insertNotice(Notice notice)
	{
		int result = 0;
		
		String sql = "insert into notice(title, content, writer_id,regdate,hit, pub, files) values(?,?,?,sysdate,?,?,?)";
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try 
		{	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql); // 실행 도구 생성
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getWriter_id());
			st.setInt(4, 0);
			st.setBoolean(5, notice.getPub());
			st.setString(6, notice.getFiles());
			
			result = st.executeUpdate();
			
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
		return result;
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
		return getNoticeList("title", "", 1); // 기본값 page 1
	}
	public List<NoticeView> getNoticeList(int page)
	{
		//3개의 getNoticeList를 각각 구현하면 관리가 힘드므로 매개변수가 가장 많은 3번째 getNoticeList를
		//하는 방향으로 구현한다.
		return getNoticeList("title", "", page);
	}
	public List<NoticeView> getNoticeList(String field/*title, writer_id*/, String query/*A*/, int page)
	{
		List<NoticeView> list = new ArrayList<NoticeView>();
		String sql = "select * from (" + 
				"    select rownum NUM, N.* " + 
				"    from (select * from notice_view where "+field+" like ? order by regdate desc) N " + 
				") " + 
				"where num between ? and ?";
		
		// 1, 11, 21, 31 -> an = 1 + (page-1)*10
		// 10, 20, 30, 40 -> page*10
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try 
		{	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql); // 실행 도구 생성
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			ResultSet rs = st.executeQuery(); // 쿼리 실행후 결과를 얻어서 fetch해올수 있게함
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				//String content = rs.getString("content");
				boolean pub = rs.getBoolean("pub");
				int cmtCount = rs.getInt("cmt_count");
				
				NoticeView notice = new NoticeView(id, title, writer_id, regdate, hit, files, pub,cmtCount);
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
	
	public List<NoticeView> getNoticePubList(String field, String query, int page) {
		// TODO Auto-generated method stub
		List<NoticeView> list = new ArrayList<NoticeView>();
		String sql = "select * from (" + 
				"    select rownum num, N.* " + 
				"    from (select * from notice_view where "+field+" like ? order by regdate desc) N " + 
				") " + 
				"where pub=1 and num between ? and ?";
		
		// 1, 11, 21, 31 -> an = 1 + (page-1)*10
		// 10, 20, 30, 40 -> page*10
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try 
		{	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql); // 실행 도구 생성
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			ResultSet rs = st.executeQuery(); // 쿼리 실행후 결과를 얻어서 fetch해올수 있게함
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				boolean pub = rs.getBoolean("pub");
				int cmtCount = rs.getInt("cmt_count");
				
				NoticeView notice = new NoticeView(id, title, writer_id, regdate, hit, files, pub, cmtCount);
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
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql); // 실행 도구 생성
			st.setString(1, "%"+query+"%");
			ResultSet rs = st.executeQuery(); // 쿼리 실행후 결과를 얻어서 fetch해올수 있게함
			
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
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql); // 실행 도구 생성
			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); // 쿼리 실행후 결과를 얻어서 fetch해올수 있게함
			
			if(rs.next())
			{
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				boolean pub = rs.getBoolean("pub");
				
				notice = new Notice(
										nid,
										title,
										writer_id,
										regdate,
										hit,
										files,
										content,
										pub
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
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql); // 실행 도구 생성
			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); // 쿼리 실행후 결과를 얻어서 fetch해올수 있게함
			
			if(rs.next())
			{
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				boolean pub = rs.getBoolean("pub");
				
				notice = new Notice(
										nid,
										title,
										writer_id,
										regdate,
										hit,
										files,
										content,
										pub
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
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql); // 실행 도구 생성
			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); // 쿼리 실행후 결과를 얻어서 fetch해올수 있게함
			
			if(rs.next())
			{
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				boolean pub = rs.getBoolean("pub");
				
				notice = new Notice(
										nid,
										title,
										writer_id,
										regdate,
										hit,
										files,
										content,
										pub
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

	public int deleteNoticeAll(int[] ids) {
		int result = 0;
		String params = "";
		
		for(int i=0;i<ids.length;i++)
		{
			params += ids[i];
			if(i<ids.length-1)
			{
				params += ",";
			}
		}
		
		String sql = "delete notice where id in ("+params+")";
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try 
		{	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			Statement st = con.createStatement(); // 실행 도구 생성
			result = st.executeUpdate(sql);
			
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
		return result;
	}

	


}
