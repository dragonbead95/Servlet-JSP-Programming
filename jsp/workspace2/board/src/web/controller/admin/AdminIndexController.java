package web.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.controller.board.ListController;
import web.entity.Post;
import web.service.PostService;

@WebServlet("/admin/index")
public class AdminIndexController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		PostService service = new PostService();
		
		int page = 1;
		if(!(request.getParameter("p")==null))
		{
			page = Integer.parseInt(request.getParameter("p")); 
		}
		int count = service.getPostCount();
		
		List<Post> list = service.getAllPostList(page);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		request.getRequestDispatcher("/WEB-INF/view/admin/index.jsp").forward(request, response);
	}
}
