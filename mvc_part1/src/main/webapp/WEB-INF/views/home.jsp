<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset = "utf-8">
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<a href = "doA">doA</a>
<a href = "doB">doB</a>
<a href = "doC">doC</a>
<a href = "doD?msg=hi,message=bi">doD</a>

<form action = "doD" method = "post">
	<input type = "text" name = "msg" />
	<input type = "text" name = "message" />
	<input type = "submit" value = "전송">
</form>
<a href = "doE">doE</a>
<a href = "doF">doF</a>
</body>
</html>
