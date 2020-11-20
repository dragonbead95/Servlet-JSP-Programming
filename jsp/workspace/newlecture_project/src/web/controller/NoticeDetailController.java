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
			Connection con = DriverManager.getConnection(url,"newlec","newlec"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			PreparedStatement st = con.prepareStatement(sql); //prepared�� ���⼭ �̸� sql���� �ִ´�. (?)�� �ִ� ����̴�.
			st.setInt(1, id); // sql ���������� ù��° ?�� id �������� �ִ´�.
			ResultSet rs = st.executeQuery(); // ���� ������ ����� �� fetch�ؿü� �ְ���
			
			rs.next(); // ���� ���ڵ带 �����´�.
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
		
		// Servlet���� �ٸ� Servelt���� �̵��ϴ� 2���� ���
		// 1. redirect => request,response �������� �ʰ� �׳� Servelt�� �̵���Ŵ
		// 2. forward => request,response�� �ٸ� Servelt���� �����ϸ鼭 �̵���Ŵ
		request
		.getRequestDispatcher("/notice/detail.jsp")
		.forward(request, response);
		
		
		
	}
	
}
