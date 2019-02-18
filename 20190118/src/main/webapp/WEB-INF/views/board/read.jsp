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
	TITLE <input type = "text" name = "title" value = "${board.title}" readonly> <br>
	CONTENT <textarea name="content" readonly>${board.content}</textarea><br>
	WRITER <input type = "text" name = "writer"  value = "${board.writer}" readonly> <br>
</form>
	
	<input type="button" value="Modify" onclick="location.href='modify?bno=${board.bno}'">
	<input type="button" value="Remove" onclick="location.href='remove?bno=${board.bno}'">
	<input type="button" value="go List" onclick="location.href='listAll'">
</body>

<script>
	var result= "${result}";
	
	if(result == "success"){
		alert("OK!!!");
	}
	
	if(result == "fail"){
		alert("NOP!!");
	}

</script>
</html>