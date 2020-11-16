package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		
		ServletContext application = request.getServletContext();	//Application �����
		HttpSession session = request.getSession();
		
		Cookie[] cookies = request.getCookies();
		
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		int v = 0;
		int result = 0;
		if(!v_.equals(""))
			v = Integer.parseInt(v_);
		
		if(op.equals("=")) {	//���� ���
			
			//int x= (Integer)application.getAttribute("value");
//			int x= (Integer)session.getAttribute("value");
			int x = 0;
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			}
			
			
			
			int y = v;
			//String operator = (String)application.getAttribute("op");
//			String operator = (String)session.getAttribute("op");
			String operator = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}
			}
			
			
			if(operator.equals("+")) {
				result = x+y;
			}else {
				result = x-y;
			}
			out.println("��� ��� : "+result);
		}else { // ����, �����϶� ����
			//application.setAttribute("value", v);
			//application.setAttribute("op", op);			
			//session.setAttribute("value", v);
			//session.setAttribute("op", op);			
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie OpCookie = new Cookie("op", op);
			
			// ����Ű�� ��� ��쿡 ����ڷκ��� ���޵Ǿ�� �ϴ��� ������, "/"�� �����ϸ� ��� �������� ��û�Ҷ����� �׻� valueCookie�� �����´�.
			// "/notice/"��� �����ϸ� notice ������ �������� ��û�ɶ��� ��Ű�� �����´�.
			valueCookie.setPath("/calc2");
			valueCookie.setMaxAge(60*60*24); // 1000-> 1000���Ŀ� ����ȴ�. 60��*60*24 = �Ϸ�, ������ �����ų� ��ǻ�Ͱ� ������ ��Ű�� ������ �� �־����
			OpCookie.setPath("/calc2");
			
			response.addCookie(valueCookie);
			response.addCookie(OpCookie);
			
			response.sendRedirect("calc2.html");
		}
		

		

		
		
		

		
		
	}

}
