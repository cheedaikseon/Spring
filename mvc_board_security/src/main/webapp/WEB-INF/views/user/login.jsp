<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>
<body>
	<form action="/login" method="post">
		<div>
			<input type="text" name="uid" placeholder="USER ID"/>
		</div>
		<div>
			<input type="password" name="upw" placeholder="USER PW"/>
		</div>
		<div>
			<label>Remember Me
			<input type="checkbox" name="remember-me"/>
			</label>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" 
		value="${_csrf.token}"/>
		<button type="submit">LOGIN</button>
	</form>
</body>
</html>