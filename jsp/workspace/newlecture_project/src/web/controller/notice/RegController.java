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
		fileSizeThreshold = 1024*1024, //byte단위, 1024Byte * 1024Byte = 1MB, 전송하는데 용량이 1MB가 초과하는 경우 location에 저장한다
		maxFileSize = 1024*1024*50, // 서버에게 보내는 파일 용량의 최대값, 하나의 파일 사이즈 (최대 50MB)
		maxRequestSize = 1024*1024*50*5 // 첨부파일을 여러개 보낼수 있는 개수, 50MB *5 = 최대 5개
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
		String title = request.getParameter("title"); //제목
		String content = request.getParameter("content"); //내용
		String isOpen = request.getParameter("open"); //바로 공개
		boolean pub = false;

		Part filePart = request.getPart("file"); // 파일 파트 하나 가져오기
		String fileName = filePart.getSubmittedFileName(); // 제출된 파일 이름 추출
		InputStream fis =  filePart.getInputStream();
		
		//절대경로 알아내는 방법
		String realPath = request.getServletContext().getRealPath("/upload");
		
		String filePath = realPath + File.separator +fileName; // "\\" : windows에 국한되는 경로 구분자 (x), 따라서 File.separator로 환경에 맞게 반환
		FileOutputStream fos = new FileOutputStream(filePath); //출력할 outputstream 파일 인스턴스 생성
		
		//fis.read()=> 1byte값을 얻어냄, 정수형으로 반환, 다 읽었을경우 -1을 반환
		byte[] buf = new byte[1024];
		int size = 0;
		while((size = fis.read(buf))!= -1) // buf에 담아줌, size에는 읽은 크기를 반환해줌
		{
			fos.write(buf,0,size); // 매개변수로 buf 하나만 사용하면 buf 크기(1024)만큼 전부 사용, int off : off번째부터 작성시작 , int len : len번째까지 작성
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
