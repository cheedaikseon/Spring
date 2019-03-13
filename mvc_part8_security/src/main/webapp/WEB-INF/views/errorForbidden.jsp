<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERROR!!!!!</title>
</head>
<body>
	<h2>${SPRING_SECURITY_403_EXCEPTION.message}</h2>
	<h3>접근 권한이 없는 사용자 입니다. 관리자에게 문의해 주세요!</h3>
	<h2>${msg}</h2>
</body>
</html>