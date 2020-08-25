package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ServletContext application = request.getServletContext();	//Application ÀúÀå¼Ò
		HttpSession session = request.getSession();
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		int v = 0;
		int result = 0;
		if(!v_.equals(""))
			v = Integer.parseInt(v_);
		
		if(op.equals("=")) {	//°ªÀ» °è»ê
			
			//int x= (Integer)application.getAttribute("value");
			int x= (Integer)session.getAttribute("value");
			int y = v;
			//String operator = (String)application.getAttribute("op");
			String operator = (String)session.getAttribute("op");
			if(operator.equals("+")) {
				result = x+y;
			}else {
				result = x-y;
			}
			out.println("°è»ê °á°ú : "+result);
		}else { // µ¡¼À, »¬¼ÀÀÏ¶§ ÀúÀå
			//application.setAttribute("value", v);
			//application.setAttribute("op", op);			
			session.setAttribute("value", v);
			session.setAttribute("op", op);			
		}
		

		

		
		
		

		
		
	}

}
