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
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import web.entity.Member;
import web.service.MemberService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id_ = "";
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberService service = new MemberService();
		
		if(service.Login(id, password))
		{
			id_ = id;
		}
		request.setAttribute("id", id_);
		
		request
		.getRequestDispatcher("index.jsp")
		.forward(request, response);
		
	}
	
}
