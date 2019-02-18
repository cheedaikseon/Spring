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
							<option value="t">
								TITLE	
							</option>
							<option value="c">
								CONTENT
							</option>
							<option value="w">
								WRITER
							</option>
							<option value="tc">
								TITLE&CONTENT
							</option>
							<option value="cw">
								CONTENT&WRITER
							</option>
							<option value="tcw">
								TITLE & CONTENT & WRITER
							</option>
						</select>					
					</div>
					<div class="col-lg-3">
						<input id="keywordInput" type="text" name="keyword" class="form-control" />
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
										<td>${board.title}</td>
										<td>${board.content}</td>
										<td>${board.regdate}</td>
										<td>${board.viewcnt}</td>
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
			</div>
		</div>
	</div>	
</section>
<script>
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