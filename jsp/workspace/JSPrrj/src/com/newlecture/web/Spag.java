package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*Controller (Java Code)*/
	    int num = 0;
		String num_ = request.getParameter("n");
		if (num_ != null && !num_.equals(""))
		{
			num = Integer.parseInt(num_);
		}
		String result;
		if (num%2!=0)
		{
			result = "홀수"; // Model data
		}else
		{
			result = "짝수"; // Model data
		}
		
		
		// string 값을 request에 저장
		request.setAttribute("result", result);	//"result" 저장소에 result 저장, 추후 spag.jsp에서 request를 통해서 꺼내 올수 있음.
		
		// list 변수를 request에 저장
		String[] names = {"newlec","dragon"};
		request.setAttribute("names",names);
		
		// Map 변수를 request에 저장
		Map<String,Object> notice = new HashMap<String,Object>();
		notice.put("id", 1);
		notice.put("title", "EL은 좋아요");
		request.setAttribute("notice",notice);
		
		//redirect, redirect는 현재 작업한 내용과 무관하게 새로 전달하는 것
		//forward, forward는 현재 작업한 내용을 이어갈수 있도록 무언가를 공유하는 것
		RequestDispatcher dispatcher 
			=  request.getRequestDispatcher("spag.jsp"); //dispatcher 역할
		dispatcher.forward(request, response); // request, response 공유
		//forwarding을 하면 Spage.java에서 사용된 request, response를 spag.jsp에서 사용할 수 있음
		//spag.jsp를 수행하기 위해서는 Controller가 있는 Spag.java에서 실행한다.
		
	}
}
