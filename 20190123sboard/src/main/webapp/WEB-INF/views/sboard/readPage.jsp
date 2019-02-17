<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<script type="text/javascript" src="/resources/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">READ PAGE</h3>
					</div>
					<div class="box-body">
						<div class="form-group">
							<label>TITLE</label>
							<div class="panel panel-primary well-sm">
								${boardVo.title}
							</div>
						</div>
						<div class="form-group">
							<label>CONTENT</label>
							<div class="panel panel-primary well-sm">
								${boardVo.content}
							</div>
						</div>
						<div class="form-group">
							<label>WRITER</label>
							<div class="panel panel-primary well-sm">
								${boardVo.writer}
							</div>
						</div>
					</div>
					<div class="box-footer">
						<div><hr/></div>
						
						<ul class="mailbox-attachments clearfix uploadedList"></ul>
						<c:if test="${!empty userInfo}">
							<c:if test="${userInfo.uno == boardVo.uno}">											
								<input type="button" id="modifyBtn" class="btn btn-default" value="MODIFY"/>
								<input type="button" id="deleteBtn" class="btn btn-default" value="DELETE"/>
							</c:if>
							<input type="button" id="replyBtn" class="btn btn-default" value="REPLY"/>
						</c:if>
						<input type="button" id="listBtn" class="btn btn-default" value="LIST"/>
					</div>
					<form id="readForm" method="post">
						<input type="hidden" name="bno" value="${boardVo.bno}"/>
						<input type="hidden" name="csrf_token" value="${csrf_token}"/>
					</form>
				</div>
			</div>			
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<c:if test="${!empty userInfo}">
				<div class="box">
					<div class="box-header with-border">
						<h3>ADD NEW COMMENT</h3>
					</div>
					<div class="box-body">
						<label>WRITER</label>
						<input type="text" class="form-control" id="newCommentAuth" name="commentAuth" value="${userInfo.uname}" readonly/>
						<label>COMMENT TEXT</label>
						<input type="text" class="form-control" id="newCommentText" name="commentText" />
					</div>
					<div class="box-footer">
						<input type="button" class="btn btn-primary" id="commentAddBtn" value="ADD COMMENT"/>
					</div>
				</div>
				</c:if>
				<ul class="timeline">
					<li class="time-label" id="commentDiv">
						<span class="bg-green">COMMENT LIST <small id="commentCntSmall">[${boardVo.commentCnt}]</small></span>
					</li>	
				</ul>
				<div class="text-center">
					<ul id="pagination" class="pagination pagination-sm no-margin">
						<!-- <li class="active"><a href="1">1</a></li>
						<li><a href="2">2</a></li>
						<li><a href="3">3</a></li> -->
					</ul>
				</div>
			</div>
		</div>
		
		<div id="modifyModal" class="modal modal-primary fade" role="dialog">
			
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body">
						<p>
							<input type="text" id="commentText" class="form-control"/>
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" id="commentModBtn" class="btn btn-info">MODIFY</button>
						<button type="button" id="commentDelBtn" class="btn btn-danger">DELETE</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">CLOSE</button>
					</div>
				</div>
			</div>
		
		</div>
		
		
		
	</section>
	
	<script id="templateTimeLine" type="text/x-handlebars-template">
		{{#each.}}
		<li class='commentLi' data-cno='{{cno}}' >
			<i class='fa fa-comments bg-blue'></i>
			<div class='timeline-item'>
				<span class='time'>
					<i class='fa fa-clock-o'></i>
					{{dateHelper updatedate}}
				</span>
				<h3 class='timeline-header'><strong>{{cno}}</strong> - {{commentAuth}}</h3>
				<div class='timeline-body'>{{commentText}}</div>
				<div class='timeline-footer'>
					{{#isCheckAuth uno}}
						<a class='btn btn-primary btn-xs' data-toggle='modal' data-target='#modifyModal'>MODIFY</a>
					{{else}}
						댓글 작성자가 아닙니다.
					{{/isCheckAuth}}
				</div>
			</div>
		</li>
		{{/each}}
	</script>
	
	<script>
		Handlebars.registerHelper("dateHelper",function(timeValue){
			var dateObj = new Date(timeValue);
			var year = dateObj.getFullYear();
			var month = dateObj.getMonth()+1;
			var date  = dateObj.getDate();
			return year +"/"+month+"/"+date;
		});
		
		Handlebars.registerHelper("isCheckAuth",function(uno, options){
			var userUno = "${userInfo.uno}";
			if(userUno == uno){
				return options.fn(this);
			}else{
				return options.inverse(this);
			}			
		});
		
	
		
		var bno = ${boardVo.bno};
		var commentPage = 1;
	
		console.log(bno);
		
		$("#commentAddBtn").click(function(){
			var auth = $("#newCommentAuth").val();
			var text = $("#newCommentText").val();
			var uno = "${userInfo.uno}";
			
			console.log(auth + " : " + text+" : " + uno);
			
			$.ajax({
				type : 'post',
				url : '/comments',
				headers : {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'POST'
				},
				dataType : 'text',
				data : JSON.stringify({
					bno : bno,
					commentText : text,
					commentAuth : auth,
					uno : uno
				}),
				success : function(result){
					commentPage = 1;
					getPage("/comments/"+bno+"/"+commentPage);
				}
			});			
		});
		
		$("#commentDiv").click(function(){
			commentPage = 1;
			var pageInfo = "/comments/"+bno+"/"+commentPage;
			getPage(pageInfo);
		});
		
		function getPage(pageInfo){
			console.log(pageInfo);
			$.getJSON(pageInfo,function(data){
				console.log(data.list.length);
				printComment(data.list);
				$("#commentCntSmall").html("["+data.pageMaker.totalCount+"]");
				
				$("#modifyModal").modal("hide");
				//printPaging(data.pageMaker,$("#pagination"));
				
				/* var str = "";
				for(var i=0; i<data.list.length;i++){
					str += "<li class='commentLi' data-cno='"+data.list[i].cno+"' >";
					str += "<i class='fa fa-comments bg-blue'></i>"
					str += "<div class='timeline-item'>";
					str += "<span class='time'>";
					str += "<i class='fa fa-clock-o'></i>"
					str += data.list[i].updatedate
					str += "</span>";
					str += "<h3 class='timeline-header'>";
					str += "<strong>"+data.list[i].cno+"</strong>";
					str += " - "+data.list[i].commentAuth+"</h3>";
					str += "<div class='timeline-body'>"+data.list[i].commentText+"</div>";
					str += "<div class='timeline-footer'>";
					str += "<a class='btn btn-primary btn-xs' href=''>MODIFY</a>";
					str += "</div>";
					str += "</div>";
					str += "</li>";
				} */

			});
		}
		
		var printComment = function(data){
			var template = $("#templateTimeLine").html();
			var tempSource = Handlebars.compile(template);
			if(commentPage == 1){
				$(".commentLi").remove();
			}
			$("#commentDiv").parent().append(tempSource(data));			
		}
		
		var printPaging = function(pageMaker,target){
			
			var str = "";
			
			for(var i = pageMaker.startPage; i<= pageMaker.endPage; i++){
				str += "<li><a href='"+i+"'>"+i+"</a></li>";
			}
			
			target.html(str);
		}
		
		$("#pagination").on("click","li a",function(event){
			event.preventDefault();
			var commentPage = $(this).attr("href");
			getPage("/comments/"+bno+"/"+commentPage);
		});
		
		
		$(".timeline").on("click",".commentLi",function(){
			var comment = $(this);
			var cno = comment.attr("data-cno");
			$(".modal-title").html(cno);
			console.log(cno);
			
			var commentText = comment.find(".timeline-body").text();
			$("#commentText").val(commentText);
			console.log(commentText);
		});
		
		
		$("#commentModBtn").click(function(){
			var cno = $(".modal-title").html();
			var cText = $("#commentText").val();
			console.log(cno+" : " + cText);
			
			$.ajax({
				type : 'put',
				url : '/comments/'+cno,
				headers : {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'PUT'					
				},
				dataType : 'text',
				data : JSON.stringify({
					commentText : cText
				}),
				success : function(result){
					alert(result);
					commentPage = 1;
					var pageInfo = "/comments/"+bno+"/"+commentPage;
					getPage(pageInfo);
				}
			});
		});
		
		$("#commentDelBtn").click(function(){
			var cno = $(".modal-title").html();
			console.log("del : " + cno);
			$.ajax({
				type : 'delete',
				url : '/comments/'+cno,
				headers : {
					'X-HTTP-Method-Override' : 'DELETE'
				},
				dataType : 'text',
				success : function(data){
					alert(data);
					commentPage = 1;
					var pageInfo = "/comments/"+bno+"/"+commentPage;
					getPage(pageInfo);
				}				
			});
		});
		
		
		
		$(window).scroll(function(){
			var dh = $(document).height();
			var wh = $(window).height();
			var wt = $(window).scrollTop();
			
			console.log("document height : " + dh);
			console.log("window height : " + wh);
			console.log("window top : " + wt);
			
			if((wt+wh) >= (dh-10)){
				console.log("load data");
				
				if($(".timeline li").size() <= 1){
					return;
				}				
				
				commentPage++;
				var pageInfo = "/comments/"+bno+"/"+commentPage;
				getPage(pageInfo);
			}		
		});
		
		
	
	
		$(function(){
			var obj = $("#readForm");
			
			$("#replyBtn").click(function(){
				obj.attr("action","/sboard/replyRegister");
				obj.attr("method","get");
				obj.submit();
			});	
			
			$("#modifyBtn").click(function(){
				obj.attr("action","/sboard/modifyPage");
				obj.attr("method","get");
				obj.submit();
			});
			
			$("#deleteBtn").click(function(){
				
				var isDelete = confirm("첨부된 내용과 게시물이 함께 삭제됩니다. 삭제하시겠습니까?");
				
				if(isDelete){
					alert("삭제요청");
					
					var arr = [];
					$(".uploadedList li").each(function(index){
						arr.push($(this).attr("data-src"));
					});
					if(arr.length > 0){
						$.post("/deleteAllFiles",{files : arr},function(result){
							alert(result);
						});
					}
					
					obj.attr("action","/sboard/remove");
					obj.submit();
					
				}else{
					alert("취소 되었습니다.");
				}
				
			});
		});	
	</script>
	<!-- 첨부파일 -->
	<script id="attachTemp" type="text/x-handlebars-template">
		<li data-src="{{fullName}}">
			<span class="mailbox-attachment-icon has-img">
				<img src="{{imgSrc}}" alt="attachment"/>
			</span>
			<div class="mailbox-attachment-info">
				<a href="{{getLink}}" class="mailbox-attachment-name" target="_blank">
					{{fileName}}
				</a>	
			</div>
		</li>
	</script>
	<script>
		var temp = Handlebars.compile($("#attachTemp").html());
	
		$.getJSON("/sboard/getAttach/"+bno,function(list){
			console.log(list.length);
			$(list).each(function(){
				var fileInfo = getFileInfo(this);
				var html = temp(fileInfo);
				$(".uploadedList").append(html);
			});			
		});
	</script>
	
	
<%@ include file="../include/footer.jsp" %>






