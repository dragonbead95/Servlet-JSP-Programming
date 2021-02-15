package web.controller.board;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.controller.admin.board.AdminListController;
import web.controller.admin.board.file.FileProcessor;
import web.entity.Post;
import web.service.PostService;

@MultipartConfig(
		fileSizeThreshold = 1024*1024, //byte단위, 1024Byte * 1024Byte = 1MB, 전송하는데 용량이 1MB가 초과하는 경우 location에 저장한다
		maxFileSize = 1024*1024*50, // 서버에게 보내는 파일 용량의 최대값, 하나의 파일 사이즈 (최대 50MB)
		maxRequestSize = 1024*1024*50*5 // 첨부파일을 여러개 보낼수 있는 개수, 50MB *5 = 최대 5개
)
@WebServlet("/board/edit")
public class EditController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String cmd = request.getParameter("cmd");
		PostService service = new PostService();
		
		if(cmd.equals("수정"))
		{
			Post post = service.getPost(id);
			
			request.setAttribute("post", post);
			
			request
			.getRequestDispatcher("/WEB-INF/view/board/board_edit.jsp")
			.forward(request, response);
		}
		else if(cmd.equals("삭제"))
		{
			service.deletePost(id);
			
			ListController lc = new ListController();
			lc.getInitalizedPostList(request, response);
			
			request
			.getRequestDispatcher("/WEB-INF/view/index/index.jsp")
			.forward(request, response);
		}	
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");

		StringBuilder builder = FileProcessor.processFile(request, request.getParts());
		String content = request.getParameter("content");
			
		PostService service = new PostService();
		service.updatePost(id, title, content, builder.toString());
		
		ListController lc = new ListController();
		lc.getInitalizedPostList(request, response);
		
		request
		.getRequestDispatcher("/WEB-INF/view/index.jsp")
		.forward(request, response);

	}
}
