<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<div class="box-header">
	<h3 class="box-title">READ BOARD</h3>
</div>
<form role="form" method="post">

		<div class="form-group">
			<label>BNO</label> 
				<input type="text" name='bno' value="${boardVO.bno}" readonly="readonly">
		</div>
		<div class="form-group">
			<label>Title</label> 
			<input type="text" name='title' value="${boardVO.title}">
		</div>
		<div class="form-group">
			<label>Content</label>
			<textarea class="form-control" name="content" rows="3">${boardVO.content}</textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label> 
			<input type="text" name="writer" value="${boardVO.writer}">
		</div>
</form>


<div class="box-footer">
	<button type="submit">SAVE</button>
	<button type="submit">CANCEL</button>
</div>

<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-warning").on("click", function() {
			self.location = "/board/listPage";
		});

		$(".btn-primary").on("click", function() {
			formObj.submit();
		});

	});
</script>


