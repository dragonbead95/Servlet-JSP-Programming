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
			result = "Ȧ��"; // Model data
		}else
		{
			result = "¦��"; // Model data
		}
		
		
		// string ���� request�� ����
		request.setAttribute("result", result);	//"result" ����ҿ� result ����, ���� spag.jsp���� request�� ���ؼ� ���� �ü� ����.
		
		// list ������ request�� ����
		String[] names = {"newlec","dragon"};
		request.setAttribute("names",names);
		
		// Map ������ request�� ����
		Map<String,Object> notice = new HashMap<String,Object>();
		notice.put("id", 1);
		notice.put("title", "EL�� ���ƿ�");
		request.setAttribute("notice",notice);
		
		//redirect, redirect�� ���� �۾��� ����� �����ϰ� ���� �����ϴ� ��
		//forward, forward�� ���� �۾��� ������ �̾�� �ֵ��� ���𰡸� �����ϴ� ��
		RequestDispatcher dispatcher 
			=  request.getRequestDispatcher("spag.jsp"); //dispatcher ����
		dispatcher.forward(request, response); // request, response ����
		//forwarding�� �ϸ� Spage.java���� ���� request, response�� spag.jsp���� ����� �� ����
		//spag.jsp�� �����ϱ� ���ؼ��� Controller�� �ִ� Spag.java���� �����Ѵ�.
		
	}
}
