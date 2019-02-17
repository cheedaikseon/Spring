<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
	.fileDrop{
		width:100%;
		height:200px;
		border:1px solid blue;
	}
</style>
</head>
<body>
	<h1>Upload Ajax</h1>
	<div class="fileDrop">
	
	</div>
	<div class="uploadList">
	
	</div>
	
	<script>
		$(".fileDrop").on("dragenter dragover",function(event){
			event.preventDefault();
		});
		
		$(".fileDrop").on("drop",function(event){
			event.preventDefault();
			
			// DOM 실제 일어난 브라우저의 이벤트에서 데이터가 포함이 되어있는  파일 객체를 가져온다.
			var files =  event.originalEvent.dataTransfer.files;
			var file = files[0];
			console.log(file);
			
			var maxSize = 10485760;
			
			if(file.size > maxSize){
				alert("파일의 크기가 너무 큽니다." + file.size);
				return;
			}
			
			var formData = new FormData();
			formData.append("file",file);
			
			$.ajax({
				type : 'POST',
				url : '/uploadAjax',
				data : formData,
				dataType : 'text',
				contentType : false,
				processData : false,
				success : function(result){
					console.log(result);
					var str = "";
					
					if(checkImageType(result)){
						// 이미지 파일
						str = "<div>";
						str += "<a href='displayFile?fileName="+getImageLink(result)+"' target='_blank'>";
						str += "<img src='displayFile?fileName="+result+"' />"
						str += "</a>";
						str += "<small data-src='"+result+"'>&times;</small>"
						str += "</div>";
					}else{
						// 일반 파일
						str = "<div>";
						str += "<a href='displayFile?fileName="+result+"' >";
						str += getOriginalName(result);
						str += "</a>";
						str += "<small data-src='"+result+"'>&times;</small>"
						str += "</div>";
					}
					$(".uploadList").append(str);
				}
			});
		});
		
		
		$(".uploadList").on("click","small",function(){
			var target = $(this);
			console.log(target.attr("data-src"));
			$.ajax({
				type : 'post',
				url : 'deleteFile',
				dataType : 'text',
				data : {
					fileName : target.attr("data-src")
				},
				success : function(result){
					if(result == 'deleted'){
						alert("삭제완료");
						target.parent("div").remove();	
					}							
				}
			});			
		});
		
		
		
		
		
		// 업로드한 이름
		function getOriginalName(fileName){
			
			var idx = fileName.indexOf("_")+1;
			return fileName.substr(idx);			
		}
		
		// 이미지 체크
		function checkImageType(fileName){
			var pattern = /jpg|gif|png|jpeg/i;
			return fileName.match(pattern);
		}
		
		// 원본이미지 링크
		function getImageLink(fileName){
			if(!checkImageType(fileName)){
				return;
			}
			
			console.log(fileName);
			
			var front = fileName.substr(0,12);
			
			var end = fileName.substr(14);
			
			console.log(front+end);
			
			return front+end;
		}
		
		
		
	</script>
	
</body>
</html>