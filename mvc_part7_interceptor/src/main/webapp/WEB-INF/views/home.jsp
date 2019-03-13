<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>
<body>
	<h1>${result}</h1>
	<a href="test1">test1</a>
	<br/>
	<a href="test2">test2</a><br/>
	<input type="button" id="testBtn" value="testBtn"/>
	<script>
		$("#testBtn").click(function(){
			$.ajax({
				type : 'get',
				datatype : 'text',
				url : 'test3',
				success : function(result){
					alert(result);
				}
			});
		});
	
	</script>
</body>
</html>



