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

import web.entity.Post;
import web.service.PostService;

@MultipartConfig(
		fileSizeThreshold = 1024*1024, //byte����, 1024Byte * 1024Byte = 1MB, �����ϴµ� �뷮�� 1MB�� �ʰ��ϴ� ��� location�� �����Ѵ�
		maxFileSize = 1024*1024*50, // �������� ������ ���� �뷮�� �ִ밪, �ϳ��� ���� ������ (�ִ� 50MB)
		maxRequestSize = 1024*1024*50*5 // ÷�������� ������ ������ �ִ� ����, 50MB *5 = �ִ� 5��
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
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String writer_id = request.getParameter("writer_id");
		String content = request.getParameter("content");
		
		PostService service = new PostService();
		
		Collection<Part> parts = request.getParts();
		StringBuilder builder = new StringBuilder();
		
		
		
		for(Part p : parts)
		{
			if(!p.getName().equals("file"))
			{
				continue;
			}
			if(p.getSize()==0) // ÷�������� ����ִ� ���
			{
				continue;
			}
			Part filePart = p; // ���� ��Ʈ �ϳ� ��������
			String fileName = filePart.getSubmittedFileName(); // ����� ���� �̸� ����
			builder.append(fileName);
			builder.append(",");
			InputStream fis = filePart.getInputStream(); // ���� ���̳ʸ� ����
			
			// ������ ����
			String realPath = request.getServletContext().getRealPath("/upload");
			
			File path = new File(realPath);
			if(!path.exists())
			{
				path.mkdirs();
			}
			
			String filePath = realPath + File.separator + fileName; // "\\" : windows�� ���ѵǴ� ��� ������(x), ���� File.separator�� ȯ�濡 �°� ����
			FileOutputStream fos = new FileOutputStream(filePath);
			
			// fis.read() => 1byte ���� ��, ���������� ��ȯ, �� �о������ -1 ��ȯ
			byte[] buf = new byte[1024];
			int size=0;
			//fis�� �о buf�� �����ϰ� ���� size�� �����Ѵ�.
			while((size=fis.read(buf))!=-1)
			{
				fos.write(buf,0,size); // �Ű������� buf �ϳ��� ����ϸ� buf ũ��(1024)��ŭ ���� ���, int off : off��°���� �ۼ�����, int len : len��°���� �ۼ�
			}
			
			fos.close();
			fis.close();
		}
		
		builder.delete(builder.length()-1, builder.length());	// ������ "," ����
		
		
		
		service.insertPost(title,writer_id,content,builder.toString(),0);
		int count = service.getPostCount();
		
		AdminListController alc = new AdminListController();
		alc.getInitalizedPostList(request, response);
		

		request
		.getRequestDispatcher("/WEB-INF/view/admin/index.jsp")
		.forward(request, response);	
	}
}
