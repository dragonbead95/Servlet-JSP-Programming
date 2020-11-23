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
			
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			Statement st = con.createStatement(); // ���� ���� ����
			ResultSet rs = st.executeQuery(sql); // ���� ������ ����� �� fetch�ؿü� �ְ���
			
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
			// Servlet���� �ٸ� Servelt���� �̵��ϴ� 2���� ���
			// 1. redirect => request,response �������� �ʰ� �׳� Servelt�� �̵���Ŵ
			// 2. forward => request,response�� �ٸ� Servelt���� �����ϸ鼭 �̵���Ŵ
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
