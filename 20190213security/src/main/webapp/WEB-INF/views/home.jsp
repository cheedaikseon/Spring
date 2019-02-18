<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>
</head>
<body>
	<a href="/test/all">all</a> <br/>
	<a href="/test/memberShip">MEMBERSHIP</a> <br/>
	<a href="/test/master">MASTER</a>
	<c:if test="${!empty SPRING_SECURITY_CONTEXT}">
	${SPRING_SECURITY_CONTEXT.authentication.principal.username}
	<a href="/logout">logout</a>
	</c:if>	
</body>
</html>