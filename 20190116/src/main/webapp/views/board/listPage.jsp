<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp"%>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">게시글 목록</h3>
				</div>
				<!-- 목록 boardList -->
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th>BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th>VIEWCNT</th>
						</tr>
						<c:forEach var="boardVo" items="${boardList}">
							<tr>
								<td>${boardVo.bno}</td>
								<td><a href="/board/readPage?bno=${boardVo.bno}&page=${pageMaker.cri.page}">${boardVo.title}</a></td>
								<td>${boardVo.writer}</td>
								<td>
									<fmt:formatDate pattern="yyyy-MM-dd HH:mm" 
										value="${boardVo.regdate}"/>
								</td>
								<td>
									<span class="badge bg-red">${boardVo.viewcnt}</span>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div> <!-- end box-body -->
				<div class="box-footer">
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}">
								<li>
									<a href="/board/listPage?page=${pageMaker.startPage-1}">&laquo;</a>
								</li>
							</c:if>
							<!-- for(int i = 1; i<=10; i++) -->
							<%-- <c:forEach var="i" begin="1" end="10" step="1"> --%>
							<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
								<li ${pageMaker.cri.page == i ? 'class=active' : ''}>
									<a href="/board/listPage?page=${i}">${i}</a>
								</li>
							</c:forEach>
							<c:if test="${pageMaker.next}">
								<li>
									<a href="/board/listPage?page=${pageMaker.endPage+1}">&raquo;</a>
								</li>
							</c:if>
						</ul>	
					</div>
				</div>
			</div>
		</div>
	</div>	
</section>
<script>
	var result = '${result}';
	
	if(result == 'SUCCESS'){
		alert('처리가 완료 되었습니다.');
	}
	
	if(result=='FAIL'){
		alert('요청 처리가 실패 하였습니다.');
	}
</script>
<%@ include file="../include/footer.jsp" %>