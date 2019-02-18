<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp"%>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">SEARCH LIST PAGE</h3>
				</div>
				<div class="box-body">
					<div class="col-lg-2">
						<select class="form-control" name="searchType">
							<option value="n" ${cri.searchType==null ? 'selected' : ''}>
								----
							</option>
							<option value="t" ${cri.searchType =='t' ? 'selected' : ''}>
								TITLE	
							</option>
							<option value="c" ${cri.searchType =='c' ? 'selected' : ''}>
								CONTENT
							</option>
							<option value="w" ${cri.searchType =='w' ? 'selected' : ''}>
								WRITER
							</option>
							<option value="tc" ${cri.searchType =='tc' ? 'selected' : ''}>
								TITLE&CONTENT
							</option>
							<option value="cw" ${cri.searchType =='cw' ? 'selected' : ''}>
								CONTENT&WRITER
							</option>
							<option value="tcw" ${cri.searchType =='tcw' ? 'selected' : ''}>
								TITLE & CONTENT & WRITER
							</option>
						</select>					
					</div>
					<div class="col-lg-3">
						<input id="keywordInput" type="text" name="keyword" class="form-control" value="${cri.keyword}"/>
					</div>
					<div class="col-lg-3">
						<input id="searchBtn" type="button" class="btn btn-warning" value="SEARCH" />
						<input id="newBtn" type="button" class="btn btn-primary" value="NEWBOARD" />
					</div>
				</div>
			</div>
			
			<div class="box">
				<div class="box-header with-border">
					<h3>SEARCH BOARD</h3>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th>BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th>VIEWCNT</th>
						</tr>
						<c:choose>
							<c:when test="${!empty list}">
								<c:forEach var="board" items="${list}">
									<tr>
										<td>${board.bno}</td>
										<td><a href="readPage${maker.makeSearchQuery(maker.cri.page)}&bno=${board.bno}">${board.title}</a></td>
										<td>${board.writer}</td>
										<td>
											<f:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regdate}"/>
										</td>
										<td><span class="badge bg-red">${board.viewcnt}</span></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="5">게시물이 존재하지 않습니다.</td>
								</tr>
							</c:otherwise>	
						</c:choose>
					</table>
				</div>
				<div class="box-footer">
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${maker.prev}">
								<li>
									<a href="list${maker.makeSearchQuery(maker.startPage-1)}">&laquo;</a>
								</li>
							</c:if>						
							<c:forEach var="i" begin="${maker.startPage}" end="${maker.endPage}" step="1">
								<li ${maker.cri.page == i ? 'class=active' : ''}>
									<%-- <a href="list?page=${i}&perPageNum=${maker.cri.perPageNum}&serchType=${cri.searchType}&keyword=${cri.keyword}">${i}</a> --%>
									<a href="list${maker.makeSearchQuery(i)}">${i}</a>
								</li>
							</c:forEach>
							<c:if test="${maker.next}">
								<li>
									<a href="list${maker.makeSearchQuery(maker.endPage +1)}">&raquo;</a>
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
		alert("처리 완료");
	}else if(result == 'FAIL'){
		alert("처리 실패");
	}


	$(function(){
		$("#searchBtn").click(function(){
			var keywordVal = $("#keywordInput").val();
			var searchTypeVal = $("select option:selected").val();
			
			location.href="/sboard/list?searchType="+searchTypeVal+"&keyword="+keywordVal;
		});
		
		$("#newBtn").click(function(){
			location.href="/sboard/register";
		});
	});
</script>
<%@ include file="../include/footer.jsp" %>