package web.controller.board;

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

@WebServlet("/board/list")
public class ListController extends HttpServlet {
	public ListController() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PostService service = new PostService();
		
		int page = 1;
		if(!(request.getParameter("p")==null))
		{
			page = Integer.parseInt(request.getParameter("p")); 
		}
		int count = service.getPostCount();
		
		List<Post> list = service.getPostList(page);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
	}
	
	public void getInitalizedPostList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
