<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript" src="/resources/smarteditor/js/service/HuskyEZCreator.js"></script>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
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
						<h3 class="box-title">REGISTER BOARD</h3>
					</div>
					<form id="registerForm" action="register" method="post">
					<input type="hidden" name="uno" value="${userInfo.uno}" />
					<div class="box-body">
						<div class="form-group">
							<label>TITLE</label>
							<input type="text" name="title" class="form-control" />
						</div>
						<div class="form-group">
							<label>CONTENT</label>
							<textarea name="content" id="ir1" rows="3" class="form-control"></textarea>
						</div>
						<div class="form-group">
							<label>WRITER</label>
							<input type="text" name="writer" class="form-control" value="${userInfo.uname}" readonly/>
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
						
						<input type="submit" class="btn btn-primary" value="SAVE"/>
					</div>
					
					</form>
				</div>
			</div>			
		</div>
	</section>
	

	
	<script id="attachTemp" type="text/x-handlebars-template">
	<li>
		<span class="mailbox-attachment-icon has-img">
			<img src="{{imgSrc}}" alt="attachment"/>
		</span>
		<div class="mailbox-attachment-info">
			<a href="{{getLink}}" class="mailbox-attachment-name" target="_blank">
				{{fileName}}		
			</a>
			<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delBtn">
				<i class="fa fa-fw fa-remove"></i>
			</a>
		</div>
	</li>		
	</script>
	<script>
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame(
			oEditors,
			"ir1",
			"/resources/smarteditor/SmartEditor2Skin.html",
			"createSEditor2"
		);
		
		$(".fileDrop").on("dragover dragenter",function(event){
			event.preventDefault();
		});
		
		var temp = Handlebars.compile($("#attachTemp").html());
		
		$(".fileDrop").on("drop",function(event){
			event.preventDefault();
			
			var files = event.originalEvent.dataTransfer.files;
			var maxSize = 10485760;
			
			for(var i=0; i<files.length; i++){
				console.log(files[i]);
				if(files[i].size > maxSize){
					alert("업로드 할 수 없는 파일 입니다."+files[i].size);
					return;
				}
				
				var formData = new FormData();
				formData.append("file",files[i]);
				
				$.ajax({
					type : 'post',
					data : formData,
					url : '/uploadAjax',
					dataType : 'text',
					processData : false,
					contentType : false,
					success : function(data){
						console.log(data);
						var fileInfo = getFileInfo(data);
						var html = temp(fileInfo);
						$(".uploadedList").append(html);
					}
				});				
			}			
		});
		
		
		$(".uploadedList").on("click",".delBtn",function(event){
			event.preventDefault();
			
			var target = $(this);
			
			$.ajax({
				type: 'post',
				url : '/deleteFile',
				data : { fileName : target.attr("href")},
				dataType : 'text',
				success : function(data){
					if(data == 'deleted'){
						target.closest("li").remove();
					}
				}				
			});
			
		});
		
		
		
		$("#registerForm").submit(function(event){
			event.preventDefault();
			
			var str = "";
			$(".uploadedList .delBtn").each(function(index){
				str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href")+"' />";
			});
			
			$(this).append(str);
 			
			
			oEditors[0].exec("UPDATE_CONTENTS_FIELD",[]);
			$(this).get(0).submit(); 
		});
	
	</script>
<%@ include file="../include/footer.jsp" %>