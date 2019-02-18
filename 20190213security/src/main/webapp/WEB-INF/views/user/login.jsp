<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN PAGE</title>
</head>
<body>
	<h1> CUSTOM LOGIN</h1>
	<h2>${error}</h2>
	<h2>${logout}</h2>
	
	<form method="post" action="login">
		<input type="text" name="username" /> <br/>
		<input type="password" name="password"/> <br/>
		<input type="submit"/>
		
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
	</form>
</body>
</html>