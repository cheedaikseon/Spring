<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>REGISTERBOARD</h1>
<form method = "post">
	TITLE <input type = "text" name = "title" value = "${board.title}" > <br>
	CONTENT <textarea name="content" >${board.content}</textarea><br>
	WRITER <input type = "text" name = "writer"  value = "${board.writer}" > <br>
	<input type = "submit" value = "수정완료">
	<input type="button" value="취소"
		onclick="location.href='read?=${board.bno}'">
</form>
</body>
</html>