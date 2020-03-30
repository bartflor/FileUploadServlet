<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css"/>
<title>Sign in</title>
</head>
<body>
<div id=content>
<h1>Fill in registration form</h1>
<form action="SignInServlet" method=post>
	user name: <input type=text name=name><br>
	password: <input type = password name=password><br>
	<input type=submit value='sign in'>


</form>
</div>
</body>
</html>