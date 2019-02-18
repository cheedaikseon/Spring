<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div>
				
				<!-- <form action="/board/register" method="post"> -->
				<form method="post">
					<div class="box-body">
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="title" 
								class="form-control" 
								placeholder="ENTER TITLE" 
								required/> 
						</div>
						<div class="form-group">
							<label>CONTENT</label>
							<textarea name="content" class="form-control" rows="3"
							 placeholder="ENTER... CONTENT"></textarea>
						</div>
						<div class="form-group">
							<label>WRITER</label>
							<input type="text" name="writer" 
								class="form-control" 
								placeholder="ENTER WRITER" 
								required/> 
						</div>
					</div>
					<div class="box-footer">
						<input type="submit" class="btn btn-warning" value="submit"/>
					</div>
				</form>
				
			</div>
		</div>
	</div>	
</section>

<%@ include file="../include/footer.jsp" %>