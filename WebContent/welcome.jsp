<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css"/>
<title>Welcome</title>
</head>
<body>
	<%	response.setHeader("Cache-Control", "no-cache, no-store");
		if (session.getAttribute("user") == null){
			RequestDispatcher MainDispatcher = request.getRequestDispatcher("index.jsp");
			MainDispatcher.forward(request, response);
			}
	%>
	<div id="header">
		<h3>Welcome</h3>
	</div>
	<%@ include file="Menu.html"%>
	<div id="content">
		<h5>Welcome ${sessionScope.user.getName()}</h5>
	</div>
</body>
</html>