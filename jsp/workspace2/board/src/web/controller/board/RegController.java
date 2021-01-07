package web.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.PostService;

@WebServlet("/board/reg")
public class RegController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request
		.getRequestDispatcher("/WEB-INF/view/board/board_reg.jsp")
		.forward(request, response);		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// todo : 작성자 ID를 추출할수 있어야함
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String writer_id = request.getParameter("id");
		String content = request.getParameter("content");
		String files = "";
		PostService service = new PostService();
		
		
		System.out.println("writer_id : " + writer_id);
		//service.insertPost(title,regdate,content);
	}
}
