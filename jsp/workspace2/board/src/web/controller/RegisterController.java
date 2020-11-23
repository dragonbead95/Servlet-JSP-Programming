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

@WebServlet("/register")
public class RegisterController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Servlet���� �ٸ� Servelt���� �̵��ϴ� 2���� ���
		// 1. redirect => request,response �������� �ʰ� �׳� Servelt�� �̵���Ŵ
		// 2. forward => request,response�� �ٸ� Servelt���� �����ϸ鼭 �̵���Ŵ
		//response.sendRedirect("/WEB-INF/view/register/register.jsp");
		request
		.getRequestDispatcher("/WEB-INF/view/register/register.jsp")
		.forward(request, response);
		
	}
	
}
