package pl.bartflor.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {	"/upload/*",
							"/delete/*", 
							"/DownloadServlet", 
							"/FileListServlet", 
							"/StatusServlet", 
							"/LogOutServlet",
							"/UploaderServlet",
							"/FileUploadServlet"})
public class SessionValidationFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpResponse.setDateHeader("Expires", 0);
		HttpSession session = httpRequest.getSession();
		if (session.getAttribute("user") == null){
			RequestDispatcher MainDispatcher = request.getRequestDispatcher("index.jsp");
			MainDispatcher.forward(request, response);
		}else {
			chain.doFilter(request, response);
		}
	}
	public void destroy() {
	}
	

}
