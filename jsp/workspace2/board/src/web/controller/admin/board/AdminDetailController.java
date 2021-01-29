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

@WebServlet("/admin/board/detail")
public class AdminDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		PostService service = new PostService();
		
		Post post = service.getPost(id);
		
		request.setAttribute("post", post);
		
		request
		.getRequestDispatcher("/WEB-INF/view/admin/board/board_detail.jsp")
		.forward(request, response);		
	}
}
