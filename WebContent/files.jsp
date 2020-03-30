<%@page import="pl.bartflor.Dao.UploadFile"%> 
<%@page import="java.util.LinkedList"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css"/>
<title>Files</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store");
			if (session.getAttribute("user") == null){
		RequestDispatcher MainDispatcher = request.getRequestDispatcher("index.jsp");
		MainDispatcher.forward(request, response);
		}
	%>
	<div id="header">
		<h3>Uploaded files</h3>
	</div>
	<%@ include file="Menu.html"%>
	<div id="content">
	<br>
		<table border = "2" align="center">
         <tr>
            <th>File name</th>
            <th>Type</th>
            <th>Size</th>
            <th>Upload date</th>
            <th>Options</th>
         </tr>
         <%
         	LinkedList<UploadFile> fl = (LinkedList<UploadFile>)request.getAttribute("files"); 
                 for(UploadFile f:fl){
         %> 
            <tr> 
                <td><%= f.getName() %></td> 
                <td><%= f.getType() %></td> 
                <td><%= f.getSize() %></td> 
                <td><%= f.getUpload_date() %></td> 
                <td><a href="download?fileId=<%=f.getId() %>">Download </a>
                	<a href="delete?fileId=<%=f.getId() %>">Delete</a></td>
            </tr> 
            <%}%> 
        </table>  	


	</div>
</body>
</html>
