package web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")	//매핑되는 주소를 쓴다. "/*" 는 모든 서블릿을 대상으로 함
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, 
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response); //흐름을 다음 서블릿으로 넘긴다.
		
	}

}
