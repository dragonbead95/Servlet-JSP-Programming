import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Nana extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
	{
		PrintWriter out = response.getWriter();
		out.println("Hello Servlet!!");
	}
}
