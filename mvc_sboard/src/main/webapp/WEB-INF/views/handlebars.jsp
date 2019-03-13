<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com//jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>

	<div id="displayDiv"></div>
	
	<script>
		var source = "<h1><p>{{name}}</p><p>{{userid}}</p><p>{{addr}}</p></h1>";
		var template = Handlebars.compile(source);
		var data = {name:"최기근" , userid:"id001" , addr : "부산"}
		$("#displayDiv").html(template(data));
	</script>
	
	<ul id="comments">
	
	</ul>
	
	<script id="template" type="text/x-handlebars-template">
		{{#each.}}
		<li class="commentLi">
			<div>{{cno}}</div>
			<div>{{commentAuth}}</div>
			<div>{{commentText}}</div>
		</li>
		{{/each}}
	</script>
	<script>
		var source = $("#template").html();
		var template = Handlebars.compile(source);
		var data =[
					{cno:1,commentAuth:"최기근1",commentText:"안녕1"},
					{cno:2,commentAuth:"최기근2",commentText:"안녕2"},
					{cno:3,commentAuth:"최기근3",commentText:"안녕3"},
					{cno:4,commentAuth:"최기근4",commentText:"안녕4"},
					{cno:5,commentAuth:"최기근5",commentText:"안녕5"},
					{cno:6,commentAuth:"최기근6",commentText:"안녕6"},
					{cno:7,commentAuth:"최기근7",commentText:"안녕7"}
				]
		$("#comments").html(template(data));
	</script>
	<div id="comment">
	
	</div>
	<script id="temp" type="text/x-handlebars-template">
		{{#each.}}
			<li data-cno='{{cno}}' class='commentLi'>
				{{cno}} : {{commentText}}
				<button>MODIFY</button>
			</li>
		{{/each}}
	</script>
	
	<script>
		$.getJSON("/comments/1/1",function(data){
			var source = $("#temp").html();
			var template = Handlebars.compile(source);
			$("#comment").html(template(data.list));
		});	
	</script>
	
	
	
	
	
	

</body>
</html>