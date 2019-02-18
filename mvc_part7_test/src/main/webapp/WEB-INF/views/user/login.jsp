<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN PAGE</title>
</head>
<body>
	<h1>LOGIN PAGE</h1>
	<form method="post" action="/user/loginPost">
		<input type="text" name="uid" /> <br/>
		<input type="text" name="upw" /> <br/>
		<label>
			<input type="checkbox" name="useCookie" />
			Remeber me
		</label>
		<input type="submit" />
	</form>
</body>
</html>