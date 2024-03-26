<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/welcome.css" />
</head>
<body>
	<%
		String userSession = (String) session.getAttribute(StringUtils.USERNAME);
		String cookieUsername  = null;
		String cookieSessionID = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie: cookies){
				if(cookie.getName().equals(StringUtils.USER)) cookieUsername = cookie.getValue();
				if(cookie.getName().equals(StringUtils.JSESSIONID)) cookieSessionID = cookie.getValue();
			}
		}
	%>
	<div class="welcome-container">
		<h1>Hello <%=cookieUsername %>. Welcome to our page!</h1>
		<h3>Cookie session Id is <%=cookieSessionID %></h3>
		<p>Session username: <%=userSession %></p>
		<a href="${pageContext.request.contextPath}/index.jsp">
			<button class="home-button">Continue to Home Page</button>
		</a>
	</div>
</body>
</html>