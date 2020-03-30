package pl.bartflor.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import pl.bartflor.Dao.FileDao;
import pl.bartflor.Dao.StorageInfo;
import pl.bartflor.Dao.User;


/**
 * Servlet implementation class UploaderServlet
 */
@WebServlet("/UploaderServlet")
public class UploaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String repositoryPath = getServletContext().getInitParameter("storeLocation");  
		HttpSession userSession = request.getSession();
		FileDao fileDB = new FileDao(getServletContext());
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = new File(repositoryPath);
		fileFactory.setRepository(filesDir);
		
		String userName = ((User)userSession.getAttribute("user")).getName();
		ServletFileUpload uploader = new ServletFileUpload(fileFactory);
		StorageInfo userStorageInfo = fileDB.getStorageInfo(userSession);
		try {
			List<FileItem> items = uploader.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
			    FileItem item = iter.next();
			    if(item.getSize() < userStorageInfo.getSpaceLeft()){
			    	String path = repositoryPath+"/"+LocalDateTime.now().toString()+userName+"_"+item.getName();
			    	File uploadedFile = new File(path);
			    	item.write(uploadedFile);
			       	fileDB.setNewUpload(item, userName, path);
			       	userStorageInfo = fileDB.getStorageInfo(userSession);
					request.setAttribute("spaceLeft", userStorageInfo.getSpaceLeftString());
			       	request.setAttribute("uploadStatus", "File uploaded.");
			       	RequestDispatcher succesDispatcher = request.getRequestDispatcher("uploadstatus.jsp");
			       	succesDispatcher.forward(request, response);
			    }else {
					request.setAttribute("spaceLeft", userStorageInfo.getSpaceLeftString());
			    	request.setAttribute("uploadStatus", "Upload failed. File is bigget than your left storage space.");
			    	RequestDispatcher failDispatcher = request.getRequestDispatcher("uploadstatus.jsp");
			       	failDispatcher.forward(request, response);
			    }
			    	
			    }
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
}



