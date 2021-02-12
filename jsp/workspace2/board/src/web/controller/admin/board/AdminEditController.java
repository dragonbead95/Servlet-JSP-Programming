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
		fileSizeThreshold = 1024*1024, //byte����, 1024Byte * 1024Byte = 1MB, �����ϴµ� �뷮�� 1MB�� �ʰ��ϴ� ��� location�� �����Ѵ�
		maxFileSize = 1024*1024*50, // �������� ������ ���� �뷮�� �ִ밪, �ϳ��� ���� ������ (�ִ� 50MB)
		maxRequestSize = 1024*1024*50*5 // ÷�������� ������ ������ �ִ� ����, 50MB *5 = �ִ� 5��
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
		
		System.out.println("���� : " + title);
		System.out.println("�ۼ��� : " + writer_id);
		System.out.println("�ۼ��� : " + regdate);
		System.out.println("÷������ : " + builder.toString());
		System.out.println("���� : " + content);
	}
}
