package pl.bartflor.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.bartflor.Dao.LoginDao;
import pl.bartflor.Dao.User;

@WebServlet("/LoginServlet")
public class SimpleLoginServlet extends HttpServlet{
	private static final long serialVersionUID = 122L;

	protected void doPost(HttpServletRequest Request, HttpServletResponse Response) throws IOException, ServletException {
		Response.setHeader("Cache-Control", "no-cache, no-store");
		User user = new User(Request.getParameter("user"), Request.getParameter("password"));
		LoginDao login = new LoginDao(getServletContext());
		if(login.validate(user)) {
			HttpSession UserSession = Request.getSession();
			UserSession.setAttribute("user", user);			
			RequestDispatcher MainDispatcher = Request.getRequestDispatcher("welcome.jsp");
			MainDispatcher.forward(Request, Response);
			System.out.print("login");
		}else {
			RequestDispatcher FailDispatcher = Request.getRequestDispatcher("loginfail.jsp");
			FailDispatcher.forward(Request, Response);

			System.out.print("login fail");
		}
		
	}
}