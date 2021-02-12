package web.controller.admin.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.controller.admin.board.file.FileProcessor;
import web.entity.Post;
import web.service.PostService;

@MultipartConfig(
		fileSizeThreshold = 1024*1024, //byte단위, 1024Byte * 1024Byte = 1MB, 전송하는데 용량이 1MB가 초과하는 경우 location에 저장한다
		maxFileSize = 1024*1024*50, // 서버에게 보내는 파일 용량의 최대값, 하나의 파일 사이즈 (최대 50MB)
		maxRequestSize = 1024*1024*50*5 // 첨부파일을 여러개 보낼수 있는 개수, 50MB *5 = 최대 5개
)
@WebServlet("/admin/board/edit")
public class AdminEditController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		PostService service = new PostService();
		
		Post post = service.getPost(id);
		
		request.setAttribute("post", post);
		
		request
		.getRequestDispatcher("/WEB-INF/view/admin/board/board_edit.jsp")
		.forward(request, response);		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String writer_id = request.getParameter("writer_id");
		String regdate = request.getParameter("regdate");
		StringBuilder builder = FileProcessor.processFile(request, request.getParts());
		String form_fileName = request.getParameter("form_fileName");
		String content = request.getParameter("content");
		
		builder.append(form_fileName);
		
		System.out.println("제목 : " + title);
		System.out.println("작성자 : " + writer_id);
		System.out.println("작성일 : " + regdate);
		System.out.println("첨부파일 : " + builder.toString());
		System.out.println("내용 : " + content);
	}
}
