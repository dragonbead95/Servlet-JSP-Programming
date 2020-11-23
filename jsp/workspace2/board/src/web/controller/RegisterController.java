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
		
		// Servlet에서 다른 Servelt으로 이동하는 2가지 방법
		// 1. redirect => request,response 유지하지 않고 그냥 Servelt을 이동시킴
		// 2. forward => request,response를 다른 Servelt으로 유지하면서 이동시킴
		//response.sendRedirect("/WEB-INF/view/register/register.jsp");
		request
		.getRequestDispatcher("/WEB-INF/view/register/register.jsp")
		.forward(request, response);
		
	}
	
}
