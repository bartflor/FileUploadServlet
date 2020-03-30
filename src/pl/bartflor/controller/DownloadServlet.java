package pl.bartflor.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.bartflor.Dao.FileDao;


@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DownloadServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		FileDao fileDB = new FileDao(context);
		String filePath = fileDB.getFilePath(request.getParameter("fileId"), request.getSession());
		File downloadFile = new File(filePath);
		//setting http response header
		String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {        
        	mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        //TODO: change DB file name to user file name
        response.setHeader("Content-Disposition", "attachment; filename="+downloadFile.getName());

        OutputStream outStream = response.getOutputStream();
        FileInputStream inStream = new FileInputStream(downloadFile); 
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        inStream.close();
        outStream.close();    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
