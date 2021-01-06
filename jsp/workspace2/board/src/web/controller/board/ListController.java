package web.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.Post;
import web.service.PostService;

@WebServlet("/board/list")
public class ListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PostService service = new PostService();
		List<Post> list = service.getPostList();
		
		request.setAttribute("list", list);
		
		request
		.getRequestDispatcher("/WEB-INF/view/board/board.jsp")
		.forward(request, response);
	}
}
