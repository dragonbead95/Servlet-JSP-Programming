package web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String sql = "select * from notice where id=?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement st = con.prepareStatement(sql); //prepared는 여기서 미리 sql문을 넣는다. (?)가 있는 경우이다.
			st.setInt(1, id); // sql 쿼리문에서 첫번째 ?에 id 정수값을 넣는다.
			ResultSet rs = st.executeQuery(); // 쿼리 실행후 결과를 얻어서 fetch해올수 있게함
			
			rs.next(); // 다음 레코드를 가져온다.
			String title = rs.getString("title");
			String writer_id = rs.getString("writer_id");
			Date regdate = rs.getDate("regdate");
			String hit = rs.getString("hit");
			String files = rs.getString("files");
			String content = rs.getString("content");
			
			request.setAttribute("title", title);
			request.setAttribute("writer_id", writer_id);
			request.setAttribute("regdate", regdate);
			request.setAttribute("hit", hit);
			request.setAttribute("files", files);
			request.setAttribute("content", content);
			
			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Servlet에서 다른 Servelt으로 이동하는 2가지 방법
		// 1. redirect => request,response 유지하지 않고 그냥 Servelt을 이동시킴
		// 2. forward => request,response를 다른 Servelt으로 유지하면서 이동시킴
		request
		.getRequestDispatcher("/notice/detail.jsp")
		.forward(request, response);
		
		
		
	}
	
}
