<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css"/>
<title>Welcome app</title>
</head>
<body>
	<%	response.setHeader("Cache-Control", "no-cache, no-store");
		if (session.getAttribute("user") == null){
			RequestDispatcher MainDispatcher = request.getRequestDispatcher("index.jsp");
			MainDispatcher.forward(request, response);
			}
	%>
	<div id="header">
		<h3>Servlet testing app</h3>
	</div>
	<%@ include file="Menu.html"%>
	<div id="content">
	<jsp:include page="${contentPage}"/>
	</div>
</body>
</html>