package web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.Notice;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Notice> list = new ArrayList<Notice>();
				
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String sql = "select * from notice";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			Statement st = con.createStatement(); // 실행 도구 생성
			ResultSet rs = st.executeQuery(sql); // 쿼리 실행후 결과를 얻어서 fetch해올수 있게함
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				String hit = rs.getString("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				
				Notice notice = new Notice(
											id,
											title,
											writer_id,
											regdate,
											hit,
											files,
											content
											);
				list.add(notice);
				
			}
			
			request.setAttribute("list", list);
			// Servlet에서 다른 Servelt으로 이동하는 2가지 방법
			// 1. redirect => request,response 유지하지 않고 그냥 Servelt을 이동시킴
			// 2. forward => request,response를 다른 Servelt으로 유지하면서 이동시킴
			request
			.getRequestDispatcher("/WEB-INF/view/notice/list.jsp")
			.forward(request, response);
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
