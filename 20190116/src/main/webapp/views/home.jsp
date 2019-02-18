<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">HOME PAGE</h3>
				</div>
				<a href="/board/register" class="btn btn-primary">글쓰기</a>
				<a href="/board/listAll" class="btn btn-primary">전체게시글</a>
				<a href="/board/listCri" class="btn btn-primary">Cri게시글</a>
				<a href="/board/listPage" class="btn btn-primary">Paging</a>
				<a href="/sboard/list" class="btn btn-primary">검색 게시판</a>
			</div>
		</div>
	</div>	
</section>

<%@ include file="include/footer.jsp" %>