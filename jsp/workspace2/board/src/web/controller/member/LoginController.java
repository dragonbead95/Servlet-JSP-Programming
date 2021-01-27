package web.controller.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import web.controller.board.ListController;
import web.entity.Member;
import web.entity.Post;
import web.service.MemberService;
import web.service.PostService;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request
		.getRequestDispatcher("/WEB-INF/view/login/login.jsp")
		.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id_ = "";
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberService service = new MemberService();
		ListController lc = new ListController();
		
		if(service.Login(id, password))
		{
			id_ = id;
		}
		else 
		{
			request.setAttribute("isLogin", 0);
			request
			.getRequestDispatcher("/WEB-INF/view/login/login.jsp")
			.forward(request, response);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("id", id_);
		
		lc.getInitalizedPostList(request, response);
		
		if(id_.equals("admin"))
		{
			request
			.getRequestDispatcher("/WEB-INF/view/admin/index.jsp")
			.forward(request, response);	
		}
		else 
		{
			request
			.getRequestDispatcher("/WEB-INF/view/index/index.jsp")
			.forward(request, response);	
		}
		
		
		
	}
	
}
