<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">READ BOARD</h3>
					
					<div class="box-body">
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="title" 
								class="form-control" 
								placeholder="ENTER TITLE" 
								value="${board.title}" readonly/> 
						</div>
						<div class="form-group">
							<label>CONTENT</label>
							<textarea name="content" class="form-control" rows="3"
							 placeholder="ENTER... CONTENT" readonly>${board.content}</textarea>
						</div>
						<div class="form-group">
							<label>WRITER</label>
							<input type="text" name="writer" 
								class="form-control" 
								placeholder="ENTER WRITER" 
								value="${board.writer}" readonly/> 
						</div>
					</div>
					<div class="box-footer">
						<input type="button" class="btn btn-warning" value="MODIFY"/>
						<input type="button" class="btn btn-danger" value="DELETE"/>
						<input type="button" class="btn btn-primary" value="LIST"/>
					</div>
					<form id="readForm" method="post">
						<input type="hidden" name="bno" value="${board.bno}"/>
						<input type="hidden" name="page" value="${cri.page}"/>
						<input type="hidden" name="perPageNum" value="${cri.perPageNum}"/>
						<input type="hidden" name="searchType" value="${cri.searchType}" />
						<input type="hidden" name="keyword" value="${cri.keyword}"/>
					</form>					
				</div>
			</div>
		</div>
	</div>	
</section>

<script>
	var result= "${result}";
	
	if(result == "SUCCESS"){
		alert("수정 처리가 완료되었습니다.");
	}
	
	if(result == "FAIL"){
		alert("수정 처리가 정상적으로 완료 되지 않았습니다.");
	}

	$(function(){
		var formObj = $("#readForm");
		
		$(".btn-warning").on("click",function(){
			formObj.attr("action","/sboard/modifyPage");
			formObj.attr("method","get");
			formObj.submit();
		});
		
		$(".btn-danger").click(function(){
			formObj.attr("action","/sboard/removePage").submit();
		});
		
		$(".btn-primary").click(function(){
			formObj.attr("action","/sboard/list");
			formObj.attr("method","get").submit();
			//location.href="/board/listAll";
		});
		
	});
</script>

<%@ include file="../include/footer.jsp" %>









