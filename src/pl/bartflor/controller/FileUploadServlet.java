package pl.bartflor.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.bartflor.Dao.FileDao;
import pl.bartflor.Dao.StorageInfo;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public FileUploadServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileDao filesDB = new FileDao(request.getServletContext());
		StorageInfo storage = filesDB.getStorageInfo(request.getSession());
		request.setAttribute("spaceLeft", storage.getSpaceLeftString());
		RequestDispatcher statusDispatcher = request.getRequestDispatcher("upload.jsp");
		statusDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
