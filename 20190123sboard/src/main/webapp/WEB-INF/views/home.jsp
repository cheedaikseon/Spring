<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="include/header.jsp" %>

	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">MAIN PAGE</h3>
					</div>
					<div class="box-body">
						<c:choose>
							<c:when test="${!empty userInfo}">
								<a href="#" class="btn btn-primary">${userInfo.uname}</a>
								<a href="/sboard/register" class="btn btn-primary">글작성</a>
								<a href="/user/signOut" class="btn btn-danger">signOut</a>	
							</c:when>
							<c:otherwise>
								<a href="/user/signIn" class="btn btn-primary">SignIN</a>
								<a href="/user/signUp" class="btn btn-primary">SignUP</a>							
							</c:otherwise>
						</c:choose>
						
						<a href="/sboard/listReply" class="btn btn-primary">답변형검색게시판</a>
						<a href="test" class="btn btn-default">commentTest</a>
						<a href="bars" class="btn btn-default">handleBarsTest</a>
						<a href="uploadForm" class="btn btn-default">uploadForm</a>
						<a href="uploadAjax" class="btn btn-default">uploadAjax</a>
					</div>
				</div>
			</div>			
		</div>
	</section>
	
<%@ include file="include/footer.jsp" %>