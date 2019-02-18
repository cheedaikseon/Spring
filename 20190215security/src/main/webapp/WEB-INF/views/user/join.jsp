<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JOIN</title>
</head>
<body>
	<h1>회원가입!</h1>
	<hr/>
	<form action="/user/joinPost" method="post">
		<div>
			<input type="text" name="uid" placeholder="uid" required/>
		</div>
		<div>
			<input type="password" name="upw" placeholder="upw" required/>
		</div>
		<div>
			<input type="password" name="repw" placeholder="repw" required/>
		</div>
		<div>
			<input type="text" name="uname" placeholder="name" required/>
		</div>
		<div>
			<input type="submit" value="SAVE"/>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
	</form>
</body>
</html>