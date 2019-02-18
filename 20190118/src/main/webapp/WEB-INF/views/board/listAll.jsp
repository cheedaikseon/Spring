<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>BoardList</title>
</head>
<body>
	<h1>BOARD LIST!!</h1>
	<input type="button" value="New Board"
		onclick="location.href='register'">

	<h2>list paging</h2>
	<table>
		<tr>
			<th>bno</th>
			<th>title</th>
			<th>writer</th>
			<th>regdate</th>
			<th>biewcnt</th>
		</tr>
		<c:forEach var="boardVo" items="${boardList}">
			<tr>
				<td>${boardVo.bno}</td>
				<td><a href="/board/read?bno=${boardVo.bno}">${boardVo.title}</a></td>
				<td>${boardVo.writer}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVo.regdate}"/></td>
				<td>${boardVo.viewcnt}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>