<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to basic servlet app</title>
<link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
<div id=content>
<h1>welcome<br></h1>
<form action="LoginServlet" method="Post" > 
	user:<input type="text" name="user"><br>
	password:<input type="password" name="password"><br>
	<input type="submit" value="Login">
</form>
<br><a href='signin.jsp'>Sign in</a>
</div>
</body>
</html>