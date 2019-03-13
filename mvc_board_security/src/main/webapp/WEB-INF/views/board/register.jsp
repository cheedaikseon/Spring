<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h3 class="box-title">REGISTER BOARD</h3>

<form method="post">
		<div>
			<label>Title</label> 
			<input type="text" name='title'>
		</div>
		<div>
			<label>Content</label>
			<textarea name="content" rows="3" ></textarea>
		</div>
		<div >
			<label>Writer</label> 
			<input type="text" name="writer" 
			value="<sec:authentication property='principal.user.uname' />" readonly>
		</div>

		<div>
			<button type="submit" class="btn btn-primary">Submit</button>
			<input type="hidden" name="uid" 
			value="<sec:authentication property='principal.user.uid' />">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</div>
</form>



