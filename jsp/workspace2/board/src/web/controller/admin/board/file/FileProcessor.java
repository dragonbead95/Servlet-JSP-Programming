package web.controller.admin.board.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileProcessor {
	public static StringBuilder processFile(HttpServletRequest request, Collection<Part> parts) throws IOException
	{
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
		

		
		if(builder.length()==0)
		{
			return builder;
		}
		else
		{
			builder.delete(builder.length()-1, builder.length());	// 마지막 "," 제거
			return builder;
		}
	}
}
