<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="logoutForm" action="/user/logout" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
<!--	<button type="submit">로그아웃</button>-->
 	</form>
	<script>
		window.onload = function(){
			document.getElementById("logoutForm").submit();
		}	
	</script>
</body>
</html>