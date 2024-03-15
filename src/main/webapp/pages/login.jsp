<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/login.css" />
</head>
<body>
	<div class="login-box">
		<h2>Login</h2>
		<form action="../login" method="post">
			<div class="row">
				<div class="col">
					<label for="username">Username:</label> <input type="text"
						id="username" name="username" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" required>
				</div>
			</div>
			<button type="submit" class="login-button">Login</button>

			<%
			String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
			String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);

			if (errMsg != null) {
				// print
				%>
				<h2 class="success-msg"><% out.println(errMsg); %></h2>
				<%
			}

			if (successMsg != null) {
				// print
				out.println(successMsg);
			}
			%>
		</form>
	</div>
</body>
</html>