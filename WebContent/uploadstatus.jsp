<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<p align="center" style="color:red"><%= request.getAttribute("uploadStatus") %> <br></p>  
<%@ include file="upload.jsp" %>  
</body>
</html>