package web.controller.admin.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import web.controller.admin.board.file.FileProcessor;
import web.entity.Post;
import web.service.PostService;

@MultipartConfig(
		fileSizeThreshold = 1024*1024, //byte단위, 1024Byte * 1024Byte = 1MB, 전송하는데 용량이 1MB가 초과하는 경우 location에 저장한다
		maxFileSize = 1024*1024*50, // 서버에게 보내는 파일 용량의 최대값, 하나의 파일 사이즈 (최대 50MB)
		maxRequestSize = 1024*1024*50*5 // 첨부파일을 여러개 보낼수 있는 개수, 50MB *5 = 최대 5개
)
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
		
		String title = request.getParameter("title");
		String writer_id = request.getParameter("writer_id");
		String content = request.getParameter("content");
		
		PostService service = new PostService();
		
		StringBuilder builder = FileProcessor.processFile(request, request.getParts());
		
		service.insertPost(title,writer_id,content,builder.toString(),0);
		int count = service.getPostCount();
		
		AdminListController alc = new AdminListController();
		alc.getInitalizedPostList(request, response);
		

		request
		.getRequestDispatcher("/WEB-INF/view/admin/index.jsp")
		.forward(request, response);	
	}
}
