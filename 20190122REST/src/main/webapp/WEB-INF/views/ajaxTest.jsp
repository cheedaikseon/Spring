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
	<div style="background-color:#ccc;height:900px;">
	
	</div>
	<div>
		<input type="text" id="name" name="name"/> <br/>
		<input type="number" id="age" name="age" />
		<input type="button" id="submit" value="전송"/>
		<input type="button" id="post" value="POST"/>
		<input type="button" id="put" value="PUT"/>
	</div>
	<div id="result" style="border:1px solid black;padding:10px;margin-top:10px;">
	
	</div>
	<div style="height:300px;">
	</div>
	<script>
		$("#submit").click(function(){
			var input_name = $("#name").val();
			var input_age = $("#age").val();
			$.ajax({
				type : 'GET',
				url : 'getSample',
				data : {
					name : input_name,
					age : input_age
				},
				dataType : 'json',
				success : function(data){
					/* alert("success"+data);
					alert("이름 : "+data.name);
					alert("나이 : "+data.age); */
					$("#result").html("이름 : "+data.name+" 나이 : " + data.age);
				},
				error : function(response){
					alert("error"+response);
				}
			});
		});
		
		$("#post").click(function(){
			var input_name = $("#name").val();
			var input_age = $("#age").val();
			$.ajax({
				type : 'POST',
				url : 'getSample',
				data : {
					name : input_name,
					age : input_age
				},
				dataType : 'json',
				success : function(data){
					var  result = "<table border=1>";
					result += "<tr>";
					result += "<th>이름</th>";
					result += "<th>나이</th>"
					result += "</tr>";
					for(var i=0; i<data.length; i++){
						result+="<tr>";
						result+="<td>";
						result+= data[i].name;
						result+="</td>";
						result+="<td>";
						result+= data[i].age;
						result+="</td>";
						result+="</tr>";
					}
					result += "</table>";					
					$("#result").html(result);
				},
				error : function(response){
					alert("error"+response);
				}
			});
		});
		
		$("#put").click(function(){
			$.ajax({
				type : 'PUT',
				url : 'getSample2',
				headers : {
					'Content-type' : 'application/json',
					'X-HTTP-Method-Override' : 'PUT'
				},
				dataType : 'json',
				data : JSON.stringify({
					name : $("#name").val(),
					age : $("#age").val()
				}),
				success : function(result){
					alert(result);
				}
			});
			
		});
		
		
		
	</script>
	
</body>
</html>