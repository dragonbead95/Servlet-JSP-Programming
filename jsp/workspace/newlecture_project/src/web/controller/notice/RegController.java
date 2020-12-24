package web.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.Notice;
import web.service.NoticeService;

@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request
		.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp")
		.forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title"); //제목
		String content = request.getParameter("content"); //내용
		String isOpen = request.getParameter("open"); //바로 공개
		boolean pub = false;
		
		if(isOpen!=null)
		{
			pub = true;
		}
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		notice.setWriter_id("newlec");
		
		NoticeService service = new NoticeService();
		int result = service.insertNotice(notice);
		
		response.sendRedirect("list");
	}
}
