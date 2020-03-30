<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css"/>
<title>File upload</title>

</head>
<body>
	<div id="header">
		<h3>Upload file</h3>
	</div>
<%@ include file="Menu.html"%>
<div id="content">		
	<h5>Choose a file from your computer (maximum size <%= request.getAttribute("spaceLeft") %>)</h5>
		<form action="UploaderServlet" method="post" enctype="multipart/form-data">
		File to upload: <input type="file" name="fileName">
		<br> <input type="submit" value="upload" id="button">
		
			
	
		</form>
</div>

</body>
</html>


