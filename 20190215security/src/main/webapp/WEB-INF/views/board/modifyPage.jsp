<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">MODIFY BOARD</h3>
				</div>
				<!-- /.box-header -->

				<form role="form" action="/board/modifyAccept" method="post">

					<input type='hidden' name='page' value="${cri.page}">
					<input type='hidden' name='perPageNum' value="${cri.perPageNum}">

					<div>
						<div>
							<label>BNO</label> 
							<input type="text" name='bno' value="${boardVO.bno}" readonly="readonly">
						</div>
						<div>
							<label>Title</label> 
							<input type="text" name='title' value="${boardVO.title}">
						</div>
						<div>
							<label>Content</label>
							<textarea name="content" rows="3">${boardVO.content}</textarea>
						</div>
						<div>
							<label>Writer</label> 
							<input type="text" name="writer" value="${boardVO.writer}">
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<!-- /.box-body -->
				</form>
				<div class="box-footer">
					<button type="submit" class="btn btn-primary">SAVE</button>
					<button type="submit" class="btn btn-warning">CANCEL</button>
				</div>

				<script>
					$(document).ready(function() {
						var formObj = $("form[role='form']");
						console.log(formObj);
						$(".btn-warning").on("click",function() {
							self.location = "/board/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}";
						});
	
						$(".btn-primary").on("click", function() {
							formObj.submit();
						});	
					});
				</script>




			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->

