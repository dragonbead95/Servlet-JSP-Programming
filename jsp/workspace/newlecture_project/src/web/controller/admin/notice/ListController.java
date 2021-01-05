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
	//404 url ����
	//405 method�� �������� ����
	//403 ���ѿ���
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
		
		if(cmd.equals("�ϰ�����"))
		{
			List<String> oids = Arrays.asList(openIds);
			// 1 2 3 ... 10 - // 3 5 8
			// 1,2,4,6,7,9,10 => �����ؾ��� �Խù� ��ȣ
			List<String> cids = new ArrayList<String>(Arrays.asList(ids));
			cids.removeAll(oids);
			
			System.out.println(Arrays.asList(ids));
			System.out.println(oids);
			System.out.println(cids);
			
			//Transaction ó��
			service.pubNoticeAll(oids, cids);
			
			
		}
		else if(cmd.equals("�ϰ�����")) 
		{	
			int[] ids1 = new int[delIds.length];
			for(int i=0;i<delIds.length;i++)
			{
				ids1[i] = Integer.parseInt(delIds[i]);
			}
			int result = service.deleteNoticeAll(ids1); // result : ������ �Ǿ��ٴ� �Ǵ��ϴ� ��
		}
		
		response.sendRedirect("list"); // list ��� ���û -> doGet ����
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//list?f=title&q=a

		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title"; //title �⺻��
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
		
		// Servlet���� �ٸ� Servelt���� �̵��ϴ� 2���� ���
		// 1. redirect => request,response �������� �ʰ� �׳� Servelt�� �̵���Ŵ
		// 2. forward => request,response�� �ٸ� Servelt���� �����ϸ鼭 �̵���Ŵ
		request
		.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp")
		.forward(request, response);

	}
}
