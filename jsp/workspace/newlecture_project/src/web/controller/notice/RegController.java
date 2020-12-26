package web.controller.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import web.entity.Notice;
import web.service.NoticeService;

@MultipartConfig(
		fileSizeThreshold = 1024*1024, //byte����, 1024Byte * 1024Byte = 1MB, �����ϴµ� �뷮�� 1MB�� �ʰ��ϴ� ��� location�� �����Ѵ�
		maxFileSize = 1024*1024*50, // �������� ������ ���� �뷮�� �ִ밪, �ϳ��� ���� ������ (�ִ� 50MB)
		maxRequestSize = 1024*1024*50*5 // ÷�������� ������ ������ �ִ� ����, 50MB *5 = �ִ� 5��
)
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request
		.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp")
		.forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title"); //����
		String content = request.getParameter("content"); //����
		String isOpen = request.getParameter("open"); //�ٷ� ����
		boolean pub = false;

		Part filePart = request.getPart("file"); // ���� ��Ʈ �ϳ� ��������
		String fileName = filePart.getSubmittedFileName(); // ����� ���� �̸� ����
		InputStream fis =  filePart.getInputStream();
		
		//������ �˾Ƴ��� ���
		String realPath = request.getServletContext().getRealPath("/upload");
		
		String filePath = realPath + File.separator +fileName; // "\\" : windows�� ���ѵǴ� ��� ������ (x), ���� File.separator�� ȯ�濡 �°� ��ȯ
		FileOutputStream fos = new FileOutputStream(filePath); //����� outputstream ���� �ν��Ͻ� ����
		
		//fis.read()=> 1byte���� ��, ���������� ��ȯ, �� �о������ -1�� ��ȯ
		byte[] buf = new byte[1024];
		int size = 0;
		while((size = fis.read(buf))!= -1) // buf�� �����, size���� ���� ũ�⸦ ��ȯ����
		{
			fos.write(buf,0,size); // �Ű������� buf �ϳ��� ����ϸ� buf ũ��(1024)��ŭ ���� ���, int off : off��°���� �ۼ����� , int len : len��°���� �ۼ�
		}
		
		fos.close();
		fis.close();
		
		if(isOpen!=null)
		{
			pub = true;
		}
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		notice.setWriter_id("newlec");
		notice.setHit("0");
		
		NoticeService service = new NoticeService();
		int result = service.insertNotice(notice);
		
		response.sendRedirect("list");
	}
}
