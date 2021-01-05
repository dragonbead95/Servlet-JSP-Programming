package web.controller.admin.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.Notice;
import web.entity.NoticeView;
import web.service.NoticeService;

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet{
	//404 url 오류
	//405 method가 존재하지 않음
	//403 권한오류
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String[] openIds = request.getParameterValues("open-id"); // 3 5 8
		String[] delIds = request.getParameterValues("del-id");
		String cmd = request.getParameter("cmd");
		String ids_ = request.getParameter("ids");
		String[] ids = ids_.trim().split(" "); // 1 2 3 4 5 6 ... 10
		NoticeService service = new NoticeService();
		
		if(cmd.equals("일괄공개"))
		{
			List<String> oids = Arrays.asList(openIds);
			// 1 2 3 ... 10 - // 3 5 8
			// 1,2,4,6,7,9,10 => 공개해야할 게시물 번호
			List<String> cids = new ArrayList<String>(Arrays.asList(ids));
			cids.removeAll(oids);
			
			System.out.println(Arrays.asList(ids));
			System.out.println(oids);
			System.out.println(cids);
			
			//Transaction 처리
			service.pubNoticeAll(oids, cids);
			
			
		}
		else if(cmd.equals("일괄삭제")) 
		{	
			int[] ids1 = new int[delIds.length];
			for(int i=0;i<delIds.length;i++)
			{
				ids1[i] = Integer.parseInt(delIds[i]);
			}
			int result = service.deleteNoticeAll(ids1); // result : 삭제가 되었다는 판단하는 값
		}
		
		response.sendRedirect("list"); // list 목록 재요청 -> doGet 수행
		
	}
	
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
		List<NoticeView> list = service.getNoticeList(field, query, page);
		int count = service.getNoticeCount(field, query);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		// Servlet에서 다른 Servelt으로 이동하는 2가지 방법
		// 1. redirect => request,response 유지하지 않고 그냥 Servelt을 이동시킴
		// 2. forward => request,response를 다른 Servelt으로 유지하면서 이동시킴
		request
		.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp")
		.forward(request, response);

	}
}
