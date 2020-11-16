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
		
		ServletContext application = request.getServletContext();	//Application 저장소
		HttpSession session = request.getSession();
		
		Cookie[] cookies = request.getCookies();
		
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		int v = 0;
		int result = 0;
		if(!v_.equals(""))
			v = Integer.parseInt(v_);
		
		if(op.equals("=")) {	//값을 계산
			
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
			out.println("계산 결과 : "+result);
		}else { // 덧셈, 뺄셈일때 저장
			//application.setAttribute("value", v);
			//application.setAttribute("op", op);			
			//session.setAttribute("value", v);
			//session.setAttribute("op", op);			
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie OpCookie = new Cookie("op", op);
			
			// 이쿠키가 어느 경우에 사용자로부터 전달되어야 하는지 설정함, "/"로 설정하면 모든 페이지를 요청할때마다 항상 valueCookie를 가져온다.
			// "/notice/"라고 설정하면 notice 하위의 서블릿들이 요청될때만 쿠키를 가져온다.
			valueCookie.setPath("/calc2");
			valueCookie.setMaxAge(60*60*24); // 1000-> 1000초후에 만료된다. 60초*60*24 = 하루, 브라어작 닫히거나 컴퓨터가 꺼져도 쿠키를 가져올 수 있어야함
			OpCookie.setPath("/calc2");
			
			response.addCookie(valueCookie);
			response.addCookie(OpCookie);
			
			response.sendRedirect("calc2.html");
		}
		

		

		
		
		

		
		
	}

}
