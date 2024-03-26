<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/header.css" />
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/footer.css" />
</head>
<body>
	<jsp:include page="<%=StringUtils.PAGE_URL_HEADER%>" />
	<h2>Welcome to our Home Page!</h2>
	<a href="<%=contextPath + StringUtils.PAGE_URL_REGISTER%>">Register
		a new Student</a> OR
	<a href="<%=contextPath + StringUtils.PAGE_URL_LOGIN%>">Login</a>
	<jsp:include page="<%=StringUtils.PAGE_URL_FOOTER%>" />
</body>
</html>
