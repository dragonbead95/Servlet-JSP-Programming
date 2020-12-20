package web.controller;

import java.io.IOException;
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

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String sql = "select * from member where id=? and pwd=?";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"board_admin","board_admin"); // 드라이버 매니저를 통해서 연결 객체 생성
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			
			rs.next();
			
			String id = rs.getString("id");
			String pwd = rs.getString("pwd");
			
			if(email.equals(id) && password.equals(pwd))
			{
				//로그인 성공
				request.setAttribute("id",id);
				request.setAttribute("password",pwd);
			}

			rs.close();
			pst.close();
			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request
			.getRequestDispatcher("login.jsp")
			.forward(request, response);
		}
		request
		.getRequestDispatcher("index.jsp")
		.forward(request, response);
		
		
	}
}
