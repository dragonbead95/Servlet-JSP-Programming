package web.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.Member;

@WebServlet("/register")
public class RegisterController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String re_password = request.getParameter("re_password");
		
		// ��й�ȣ üũ
		if(!password.equals(re_password))
		{
			response.sendRedirect("register.html");
		}
		

		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String sql = "insert into member values(?,?)";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url,"board_admin","board_admin"); // ����̹� �Ŵ����� ���ؼ� ���� ��ü ����
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			pst.executeQuery();
			
			pst.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request
		.getRequestDispatcher("/WEB-INF/view/index/index.jsp")
		.forward(request, response);
		
	}
	
}
