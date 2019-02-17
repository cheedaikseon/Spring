<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp" %>

	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">REPLY BOARD</h3>
					</div>
					<div class="box-body">
						<table class="table with-bordered">
							<tr>
								<th>BNO</th>
								<th>TITLE</th>
								<th>WRITER</th>
								<th>REGDATE</th>
								<th>VIEWCNT</th>
							</tr>
							<c:forEach var="b" items="${list}">
								<c:choose>
									<c:when test="${b.showboard == 'y'}">
										<tr>
											<td>${b.bno}</td>
											<td>
											<a href="/sboard/readPage?bno=${b.bno}">
												<c:if test="${b.depth != 0}">
													<c:forEach var="i" begin="1" end="${b.depth}">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													</c:forEach>
													└
												</c:if>
												${b.title} [${b.commentCnt}]
											</a>
											</td>
											<td>${b.writer}</td>
											<td>
												<f:formatDate pattern="yyyy-MM-dd HH:mm" value="${b.updatedate}"/>
											</td>
											<td><span class="badge bg-red">${b.viewcnt}</span></td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td></td>
											<td>삭제된 게시물 입니다.</td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</table>
					</div>
					<div class="box-body">
						<div class="text-center">
							<ul class="pagination">
								<c:if test="${pageMaker.prev}">
									<li>
										<a href="listReply${pageMaker.makeSearchQuery(1)}">&laquo;&laquo;</a>
									</li>
									<li>
										<a href="listReply${pageMaker.makeSearchQuery(pageMaker.startPage-1)}">&laquo;</a>
									</li>
								</c:if>
								<c:forEach var = "i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
									<li ${pageMaker.cri.page == i ? 'class=active' : ''}>
										<a href="listReply${pageMaker.makeSearchQuery(i)}">${i}</a>
									</li>
								</c:forEach>
								<c:if test="${pageMaker.next}">
									<li>
										<a href="listReply${pageMaker.makeSearchQuery(pageMaker.endPage+1)}">&raquo;</a>
									</li>
									<li>
										<a href="listReply${pageMaker.makeSearchQuery(pageMaker.maxPage)}">&raquo;&raquo;</a>
									</li>
								</c:if>
							</ul>
						</div>
					</div>
					<div class="box-body">
						<div class="col-lg-2">
							<select class="form-control" name="searchType">
								<option value="n" ${cri.searchType == null ? 'selected' : ''}>
									--------------------
								</option>
								<option value="t" ${cri.searchType == 't' ? 'selected' : ''}>
									TITLE
								</option>	
								<option value="c" ${cri.searchType == 'c' ? 'selected' : ''}>
									CONTENT
								</option>
								<option value="w" ${cri.searchType == 'w' ? 'selected' : ''}>
									WRITER
								</option>
								<option value="tc" ${cri.searchType == 'tc' ? 'selected' : ''}>
									TITLE&CONTENT
								</option>
								<option value="cw" ${cri.searchType == 'cw' ? 'selected' : ''}>
									CONTENT&WRITER
								</option>
								<option value="tcw" ${cri.searchType == 'tcw' ? 'selected' : ''}>
									TITLE&CONTENT&WRITER
								</option>
							</select>
						</div>
						<div class="col-lg-3">
							<input type="text" id="keyword" class="form-control" name="keyword" value="${cri.keyword}"/>
						</div>
						<div class="col-lg-3">
							<input type="button" id="searchBtn" class="btn btn-danger" value="검색"/>
							<input type="button" id="newBtn" class="btn btn-primary" value="글작성"/>
						</div>
					</div>					
				</div>
			</div>			
		</div>
	</section>
	<script>
		$("#searchBtn").click(function(){
			location.href="/sboard/listReply?searchType="
					+$("select option:selected").val()
					+"&keyword="+$("#keyword").val();
		});
		
		$("#newBtn").click(function(){
			location.href="/sboard/register";
		});
		
		
	</script>
<%@ include file="../include/footer.jsp" %>