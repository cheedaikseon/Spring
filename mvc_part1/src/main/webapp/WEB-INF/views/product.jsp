<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset = "utf-8">
	<title>Home</title>
</head>
<body>

	<h3>1. ${requestScope.product.name}</h3>
	<h3>1. ${product.price}</h3>
	
	<h3>2. ${productVo.name}</h3>
	<h3>2. ${productVo.price}</h3>
	
</body>
</html>
