package web.controller.board;

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
		fileSizeThreshold = 1024*1024, //byte단위, 1024Byte * 1024Byte = 1MB, 전송하는데 용량이 1MB가 초과하는 경우 location에 저장한다
		maxFileSize = 1024*1024*50, // 서버에게 보내는 파일 용량의 최대값, 하나의 파일 사이즈 (최대 50MB)
		maxRequestSize = 1024*1024*50*5 // 첨부파일을 여러개 보낼수 있는 개수, 50MB *5 = 최대 5개
)
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
			if(p.getSize()==0) // 첨부파일이 비어있는 경우
			{
				continue;
			}
			Part filePart = p; // 파일 파트 하나 가져오기
			String fileName = filePart.getSubmittedFileName(); // 제출된 파일 이름 추출
			builder.append(fileName);
			builder.append(",");
			InputStream fis = filePart.getInputStream(); // 파일 바이너리 내용
			
			// 절대경로 추출
			String realPath = request.getServletContext().getRealPath("/upload");
			
			File path = new File(realPath);
			if(!path.exists())
			{
				path.mkdirs();
			}
			
			String filePath = realPath + File.separator + fileName; // "\\" : windows에 국한되는 경로 구분자(x), 따라서 File.separator로 환경에 맞게 변함
			FileOutputStream fos = new FileOutputStream(filePath);
			
			// fis.read() => 1byte 값을 얻어냄, 정수형으로 반환, 다 읽었을경우 -1 반환
			byte[] buf = new byte[1024];
			int size=0;
			//fis를 읽어서 buf에 저장하고 과를 size에 저장한다.
			while((size=fis.read(buf))!=-1)
			{
				fos.write(buf,0,size); // 매개변수로 buf 하나만 사용하면 buf 크기(1024)만큼 전부 사용, int off : off번째부터 작성시작, int len : len번째까지 작성
			}
			
			fos.close();
			fis.close();
		}
		
		builder.delete(builder.length()-1, builder.length());	// 마지막 "," 제거
		
		
		service.insertPost(title,writer_id,content,builder.toString(),1);
		int count = service.getPostCount();
		
		List<Post> list = service.getPostPubList(1);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		

		request
		.getRequestDispatcher("/WEB-INF/view/index/index.jsp")
		.forward(request, response);	
	}
}
