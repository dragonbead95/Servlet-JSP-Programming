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
		

		
		if(builder.length()==0)
		{
			return builder;
		}
		else
		{
			builder.delete(builder.length()-1, builder.length());	// ������ "," ����
			return builder;
		}
	}
}
