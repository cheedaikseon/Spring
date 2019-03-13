<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COMMENT TEST</title>
<style>
	.pagination{
		width:100%;
	}
	
	.pagination li{
		list-style:none;
		float:left;
		padding:3px;
		border:1px solid skyblue;
		margin:3px;
	}
	
	.pagination li a{
		margin:3px;
		text-decoration:none;
	}
	
	.active{
		background:skyblue;
		border:1px solid black;
	}
	
	.active a{
		color:white;
	}
	
	
	

</style>
</head>
<body>
	<div id="modDiv" style="display:none;">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="commentText"/>
		</div>
		<div>
			<button id="commentModBtn">MODIFY</button>
			<button id="commentDelBtn">DELETE</button>
			<button id="closeBtn">CLOSE</button>
		</div>
	</div>
	<h2>AJAX-REST Test PAGE</h2>
	<div>
		<div>
			Comment Auth <input type="text" name="commentAuth" id="newCommentAuth"/>
		</div>
		<div>
			Comment Text <input type="text" name="commentText" id="newCommentText"/>
		</div>
		<button id="commentAddBtn">ADD COMMENT</button>
		<button id="commentListBtn">ADD LIST</button>
	</div>
	
	<ul id="comments">
	
	</ul>
	
	<ul id="pagination" class="pagination">
	
	</ul>
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		
	<script>
		var bno = 1;
	
		$("#commentAddBtn").click(function(){
			var commentText = $("#newCommentText").val();
			var commentAuth = $("#newCommentAuth").val();
			
			$.ajax({
				type : 'post',
				url : '/comments',
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : "text",
				data : JSON.stringify({
					bno : bno,
					commentText : commentText,
					commentAuth : commentAuth
				}),
				success : function(data){
					if(data == "SUCCESS"){
						alert("정상 등록 되었습니다.");
						getAllList();
					}else{
						alert(data);
					}
				},
				error : function(response, error){
					alert(error);
				}
			});
		});
		
		$("#commentListBtn").click(function(){
			getAllList();	
		});
	
		function getAllList(){
			
			$.getJSON("/comments/all/"+bno,function(data){
				var str ="";
				$(data).each(function(){
					str+= "<li data-cno='"+this.cno+"' class='commentLi'>" + this.cno +" : " +this.commentText+ " <button>MODIFY</button></li>"; 
				});
				$("#comments").html(str);
			});
		}
		
		$("#comments").on("click",".commentLi button",function(){
			var comment = $(this).parent();
			var cno = comment.attr("data-cno");
			var commentText = comment.text();
			$(".modal-title").html(cno);
			$("#commentText").val(commentText);
			
			$("#modDiv").show("slow");
			
		});
		
		$("#closeBtn").click(function(){
			$("#modDiv").hide("slow");
		});
		
		$("#commentModBtn").click(function(){
			var cno = $(".modal-title").html();
			var commentText = $("#commentText").val();
			
			$.ajax({
				type : 'patch',
				url : '/comments/'+cno,
				headers : {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'PATCH'
				},
				data : JSON.stringify({commentText : commentText}),
				dataType : 'text',
				success : function(data){
					if(data == "SUCCESS"){
						alert("수정이 완료 되었습니다.");
						$("#modDiv").hide("slow");
						getAllList();
					}
				}
			});
		});
		
		$("#commentDelBtn").click(function(){
			var cno = $(".modal-title").html();
			
			$.ajax({
				type : 'delete',
				url : '/comments/'+cno,
				headers : {
					"X-HTTP-Method-Override" : "DELETE"
				},
				dataType : "text",
				success : function(result){
					alert(result);
					$("#modDiv").hide("slow");
					getAllList();
				}
			});
		});
		
		
		getPageList(1);
		
		function getPageList(page){
			$.getJSON("/comments/"+bno+"/"+page, function(data){
				console.log("list의 길이 : "+data.list.length);
				var str ="";
				for(var i=0; i<data.list.length; i++){
					str += "<li data-cno='"+data.list[i].cno+"' class='commentLi'>"
							+ data.list[i].cno + " : " +data.list[i].commentText
							+ "<button>MODIFY</button></li>";
				}
				$("#comments").html(str);
				printPage(data.pageMaker);
				/* $(data.list).each(function(){
					console.log(this.cno);
					console.log(this.commentText);
					console.log(this.commentAuth);
				}); */
			});
		}
		
		function printPage(pageMaker){
			var str = "";
			
			if(pageMaker.prev){
				str+="<li> <a href='"+(pageMaker.startPage - 1)+"'> << </a></li>";
			}
			
			for(var i=pageMaker.startPage; i<=pageMaker.endPage; i++){
				var strClass = pageMaker.cri.page == i ? 'class=active' : '';
				str+= "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
			}
			
			if(pageMaker.next){
				str+= "<li> <a href='"+(pageMaker.endPage + 1)+"'> >> </a></li>";
			}
			
			
			$("#pagination").html(str);
		}
		
		
		$("#pagination").on("click","li a",function(event){
			event.preventDefault();
			var commentPage = $(this).attr("href");
			getPageList(commentPage);
			
		});
		
		
	</script>
	
	
	
</body>
</html>