<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<script src="/resources/js/upload.js" type="text/javascript"></script>
<script type="text/javascript" src="/resources/smarteditor/js/service/HuskyEZCreator.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<style>
	.fileDrop{
		width:80%;
		height:150px;
		border:1px solid gray;
		background-color:lightslategray;
		margin:auto;
	}
</style>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">MODIFY BOARD</h3>
					</div>
					<form id="modifyForm" method="post">
					<div class="box-body">
						<div class="form-group">
							<label>TITLE</label>
							<input type="text" name="title" class="form-control" value="${boardVo.title}"/>
						</div>
						<div class="form-group">
							<label>CONTENT</label>
							<textarea name="content" id="ir1" rows="3" class="form-control">${boardVo.content}</textarea>
						</div>
						<div class="form-group">
							<label>WRITER</label>
							<input type="text" name="writer" class="form-control" value="${boardVo.writer}" readonly/>
						</div>
						<div class="form-group">
							<label>FILE DROP HERE</label>
							<div class="fileDrop"></div>
						</div>
					</div>
					<div class="box-footer">
						<div><hr/></div>
						<ul class="mailbox-attachments clearfix uploadedList">
						
						</ul>
						<input type="hidden" name="bno" value="${boardVo.bno}"/>
						<input type="button" id="saveBtn" class="btn btn-primary" value="SAVE"/>
						<input type="button" id="cancelBtn" class="btn btn-primary" value="CANCEL"/>
					</div>					
					</form>
				</div>
			</div>			
		</div>
	</section>
	<script id="template" type="text/x-handlebars-template">
		<li>
			<span class="mailbox-attachment-icon has-img">
				<img src="{{imgSrc}}" alt="첨부파일"/>
			</span>
			<div class="mailbox-attachment-info">
				<a href="{{getLink}}" class="mailbox-attachment-name">
					{{fileName}}
				</a>
				<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delBtn">
					<i class="fa fa-fw fa-remove"></i>	
				</a>
			</div>
		</li>
	</script>
	
	<script>
		var bno = ${boardVo.bno};
		
		var temp = Handlebars.compile($("#template").html());
		
		$.getJSON("/sboard/getAttach/"+bno,function(list){
			for(var i=0; i<list.length; i++){
				console.log(list[i]);
				
				var fileInfo = getFileInfo(list[i]);
				var html = temp(fileInfo);
				
				console.log(html);
				
				$(".uploadedList").append(html);
			}	
		});
		
		$(".uploadedList").on("click",".delBtn",function(event){
			event.preventDefault();
			
			var target = $(this);
			var fileLink = $(this).attr("href");
			
			$.ajax({
				type: 'post',
				url : '/deleteFile',
				data : {
					fileName : fileLink
				},
				dataType : 'text',
				success : function(data){
					target.closest("li").remove();
				}
			});
		});
		
		$(".fileDrop").on("dragover dragenter", function(event){
			event.preventDefault();
		});
		
		$(".fileDrop").on("drop", function(event){
			event.preventDefault();
			
			var files = event.originalEvent.dataTransfer.files;
			
			var maxSize = 1085760;
			
			for(var i= 0; i<files.length; i++){
				if(files[i].size > maxSize){
					alert("첨부파일의 크기가 너무 큽니다." + files[i].size);
					return;
				}
				
				var formData = new FormData();
				formData.append("file",files[i]);
				
				$.ajax({
					type : 'post',
					url : '/uploadAjax',
					dataType : 'text',
					data : formData,
					contentType : false,
					processData : false,
					success : function(result){
						var fileInfo = getFileInfo(result);
						var html = temp(fileInfo);
						console.log(html);
						$(".uploadedList").append(html);
					}
				});
			}
		});
	
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame(
			oEditors,
			"ir1",
			"/resources/smarteditor/SmartEditor2Skin.html",
			"createSEditor2"
		);
		
		var obj = $("#modifyForm");
		
		$("#saveBtn").click(function(){
			
			var str = "";
			
			$(".uploadedList .delBtn").each(function(index){
				var link = $(this).attr("href");
				str += "<input type='hidden' name='files["+index+"]' value='"+link+"' />";
			});
			
			obj.append(str);
			
			oEditors[0].exec("UPDATE_CONTENTS_FIELD",[]);
			obj.attr("action","/sboard/modifyPage");
			obj.submit();
		});
		
		$("#cancelBtn").click(function(){
			obj.attr("action","/sboard/listReply");
			obj.attr("method","get");
			obj.submit();
		});
	
	</script>
<%@ include file="../include/footer.jsp" %>