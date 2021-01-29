package web.controller.admin.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.entity.Post;
import web.service.PostService;

@WebServlet("/admin/board/reg")
public class AdminRegController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request
		.getRequestDispatcher("/WEB-INF/view/admin/board/board_reg.jsp")
		.forward(request, response);		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String writer_id = request.getParameter("writer_id");
		String content = request.getParameter("content");
		String files = request.getParameter("files");
		PostService service = new PostService();
		
		service.insertPost(title,writer_id,content,files,0);
		int count = service.getPostCount();
		
		AdminListController alc = new AdminListController();
		alc.getInitalizedPostList(request, response);
		

		request
		.getRequestDispatcher("/WEB-INF/view/admin/index.jsp")
		.forward(request, response);	
	}
}
