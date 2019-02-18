<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>


<h3 class="box-title">REGISTER BOARD</h3>

<form role="form" method="post">

		<input type="hidden" name="uno" value="${login.uno}"/>
		
		<div>
			<label>Title</label> 
			<input type="text" name='title'>
		</div>
		<div>
			<label>Content</label>
			<textarea name="content" rows="3" ></textarea>
		</div>
		<div>
			<label>Writer</label> 
			<input type="text" name="writer" value="${login.uname}" readonly>
		</div>

		<div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
</form>



