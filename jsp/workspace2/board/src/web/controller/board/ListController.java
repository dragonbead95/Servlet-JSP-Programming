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
		
		String field_ = request.getParameter("field");
		String query_ = request.getParameter("query");
		String p_ = request.getParameter("p");
		
		String field = "title"; // 검색 필드 기본값 : 제목
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
		if(p_ != null && !p_.equals(""))
		{
			page = Integer.parseInt(p_);
		}
		List<Post> list = service.getPostPubList(field, query, page);
		int count = service.getPostCount();
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
	}
	
	public void getInitalizedPostList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
