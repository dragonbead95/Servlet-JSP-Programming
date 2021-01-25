package web.controller.member;

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

import web.controller.board.ListController;
import web.entity.Member;
import web.service.MemberService;

@WebServlet("/member/signUp")
public class SignUpController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request
		.getRequestDispatcher("/WEB-INF/view/signUp/signUp.jsp")
		.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemberService service = new MemberService();
		ListController lc = new ListController();
		
		String user_id= request.getParameter("user_id");
		String password = request.getParameter("password");
		String re_password = request.getParameter("re_password");
				
		int result = service.Register(user_id, re_password);
		
		if(result!=0)
		{
			request.setAttribute("isSignUp", 1);
		}
		else
		{
			request.setAttribute("isSignUp", 0);
			request
			.getRequestDispatcher("/WEB-INF/view/signUp/signUp.jsp")
			.forward(request, response);
		}
		
		lc.getInitalizedPostList(request, response);
		
		request
		.getRequestDispatcher("/WEB-INF/view/index/index.jsp")
		.forward(request, response);
		
	}
	
}
