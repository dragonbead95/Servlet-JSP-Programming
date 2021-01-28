package web.controller.admin.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.PostService;

@WebServlet("/admin/board/pub")
public class PubController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String[] openIds = request.getParameterValues("open-id"); // 2 3
		String[] ids = request.getParameter("ids").trim().split(" ");	// 1 2 3 4 5
		String cmd = request.getParameter("cmd"); // �ϰ����� or �ϰ�����
		PostService service = new PostService();

		if(cmd.equals("�ϰ�����"))
		{
			List<String> oids = Arrays.asList(openIds);
			// {1,2,3,4,5} - {2,3} = {1,4,5}
			List<String> cids = new ArrayList<String>(Arrays.asList(ids));
			cids.removeAll(oids);
			
			service.pubNoticeAll(oids,cids);
		}
		else if(cmd.equals("�ϰ�����"))
		{
			
		}
		
		response.sendRedirect("/admin/index");
		//request.getRequestDispatcher("/WEB-INF/view/admin/index.jsp").forward(request, response);
	}
}	
