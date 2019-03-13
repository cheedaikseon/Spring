<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
	ul{
		list-style:none;
	}
	ul li{
		float:left;
		margin-left:10px;
		margin-right:10px;
	}
	
	.red{
		color:red;
	}
</style>
		<h3 class="box-title">Board List</h3>
		<button id='newBtn'>New Board</button>
		<h3 class="box-title">LIST PAGING</h3>
		<table border=1>
			<tr>
				<th style="width: 10px">BNO</th>
				<th>TITLE</th>
				<th>WRITER</th>
				<th>REGDATE</th>
				<th style="width: 40px">VIEWCNT</th>
			</tr>

			<c:forEach items="${list}" var="boardVO">
				<tr>
					<td>${boardVO.bno}</td>
					<td><a
						href='/board/readPage${pageMaker.makeQuery(pageMaker.cri.page) }&bno=${boardVO.bno}'>
							${boardVO.title}</a></td>
					<td>${boardVO.writer}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
							value="${boardVO.regdate}" /></td>
					<td><span>${boardVO.viewcnt }</span></td>
				</tr>

			</c:forEach>

		</table>


	<ul class="pagination">

		<c:if test="${pageMaker.prev}">
			<li><a href="${pageMaker.startPage - 1}">&laquo;</a></li>
		</c:if>

		<c:forEach begin="${pageMaker.startPage }"
			end="${pageMaker.endPage }" var="idx">
			<li
				<c:out value="${pageMaker.cri.page == idx?' class = red':''}"/>>
				<a href="${idx}">${idx}</a>
			</li>
		</c:forEach>
		<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			<li><a
				href="${pageMaker.endPage +1}">&raquo;</a></li>
		</c:if>
	</ul>

<form id="jobForm">
  <input type='hidden' name="page" value="${pageMaker.cri.page}">
  <input type='hidden' name="perPageNum" value="${pageMaker.cri.perPageNum}">
</form>



<script>
	var result = '${result}';

	if(result != null && result != ''){alert(result);}
	
	
	$(".pagination li a").on("click", function(event){
		
		event.preventDefault(); 
		
		var targetPage = $(this).attr("href");
		
		var jobForm = $("#jobForm");
		jobForm.find("[name='page']").val(targetPage);
		jobForm.attr("action","/board/listPage").attr("method", "get");
		jobForm.submit();
	});
	
	$("#newBtn").click(function(){
		location.href="/board/register";
	});
	
	
</script>
