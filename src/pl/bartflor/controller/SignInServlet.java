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

@WebServlet("/SignInServlet")

public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User newUser = new User(request.getParameter("name"), request.getParameter("password"));
		LoginDao login = new LoginDao(getServletContext());
		if(login.setNew(newUser)) {
			HttpSession UserSession = request.getSession();
			UserSession.setAttribute("user", newUser);			
			RequestDispatcher WelcomeDispatcher = request.getRequestDispatcher("welcome.jsp");
			WelcomeDispatcher.forward(request, response);
		}
		else {
			RequestDispatcher FailDispatcher = request.getRequestDispatcher("registerfail.jsp");
			FailDispatcher.forward(request, response);
		}
		
	}

}

