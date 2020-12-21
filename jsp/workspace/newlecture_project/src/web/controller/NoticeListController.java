package web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.Notice;
import web.service.NoticeService;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//list?f=title&q=a

		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title"; //title 기본값
		if(field_ != null && !field_.equals(""))
		{
			field = field_;
		}
		
		String query = "";
		if(query_ != null && !query_.equals(""))
		{
			query = query_;
		}
		
		int page = 1;
		if(page_ != null && !page_.equals(""))
		{
			page = Integer.parseInt(page_);
		}
		
		NoticeService service = new NoticeService();
		List<Notice> list = service.getNoticeList(field, query, page);

		request.setAttribute("list", list);
		// Servlet에서 다른 Servelt으로 이동하는 2가지 방법
		// 1. redirect => request,response 유지하지 않고 그냥 Servelt을 이동시킴
		// 2. forward => request,response를 다른 Servelt으로 유지하면서 이동시킴
		request
		.getRequestDispatcher("/WEB-INF/view/notice/list.jsp")
		.forward(request, response);

	}
}
