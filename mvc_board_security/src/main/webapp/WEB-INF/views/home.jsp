<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	
<h3 class="box-title">HOME PAGE</h3>
<a href="/board/register">글쓰기</a><br/>
<a href="/board/listPage">전체글목록</a><br/>
<sec:authorize access="isAnonymous()">
	<a href="/user/login">로그인</a>
	<a href="/user/join">회원가입</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.user.uname"/>
	<sec:authentication property="principal.user.authList"/>
	<a href="/user/logout">로그아웃</a>
</sec:authorize>

