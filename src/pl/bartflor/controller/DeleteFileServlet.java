package pl.bartflor.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.bartflor.Dao.FileDao;


@WebServlet("/delete")
public class DeleteFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public DeleteFileServlet() {
        super();
            }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		HttpSession userSession = request.getSession();
		FileDao fileDB = new FileDao(context);
		String fileId = request.getParameter("fileId");
		//delete from server
		File serverFile = new File(fileDB.getFilePath(fileId, userSession));
		serverFile.delete();
		//delete from db
		fileDB.deleteFile(fileId, userSession);
		
		RequestDispatcher succesDispatcher = request.getRequestDispatcher("FileListServlet");
		succesDispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
