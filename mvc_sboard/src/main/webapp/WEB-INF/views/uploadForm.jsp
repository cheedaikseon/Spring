<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="uploadForm" method="post" enctype="multipart/form-data">
		<input type="file" name="file"/>
		<input type="submit" />	
	</form>
	<br/>
	<hr>
	<form action="uploadForm1" method="post" enctype="multipart/form-data">
		<input type="file" name="file" multiple/>
		<input type="submit" />	
	</form>
	<br/>
	<hr>
	<form action="uploadForm2" method="post" enctype="multipart/form-data">
		<input type="text" name="auth" /><br/>
		<input type="file" name="file" /><br/>
		<input type="file" name="file1" /><br/>
		<input type="submit" />		
	</form>
	<br/>
	<hr>
	<form action="uploadForm3" method="post" enctype="multipart/form-data">
		<input type="text" name="auth"/>		    <br/>
		<input type="file" name="file" multiple />  <br/>
		<input type="file" name="file1" />			<br/>
		<input type="submit"/>
	</form>
</body>
</html>