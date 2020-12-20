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
		
		// 비밀번호 체크
		if(!password.equals(re_password))
		{
			response.sendRedirect("register.jsp");
		}
		
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String sql = "insert into member values(?,?)";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url,"board_admin","board_admin"); // 드라이버 매니저를 통해서 연결 객체 생성
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			pst.executeQuery();
			
			pst.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			try {
				pst.close();
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(-1);
			}
			e.printStackTrace();
		}finally {
			
		}
		request
		.getRequestDispatcher("index.jsp")
		.forward(request, response);
		
	}
	
}
