<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- Main content -->
<h3>HOME PAGE</h3>
<a href="/board/register">글쓰기</a><br/>
<a href="/board/listPage">전체글목록</a><br/>

<c:choose>
	<c:when test="${!empty login}">
		${login.uname} | 
		<a href="/user/logout">로그아웃</a>
	</c:when>
	<c:otherwise>
		<a href="/user/login">로그인</a>
	</c:otherwise>
</c:choose>



