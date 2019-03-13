/**
 *  upload Helper
 */
// 이미지 타입 확인 정규식 내용을 가지고 해당되는 문자열과 일치하는지 확인
// match 함수는 내부의 정규식 형태를 가지고 true / false 반환
function checkImageType(fileName){
	var pattern = /jpg|gif|png|jpeg/i;
	return fileName.match(pattern);
}

// 업로드된 파일 경로와이름을 가지고 javascript object 형태로 반환
function getFileInfo(fullName){
	var fullName,imgSrc,fileName,getLink;
	
	if(checkImageType(fullName)){
		imgSrc = "/displayFile?fileName="+fullName;
		getLink = "/displayFile?fileName="+fullName.replace("s_","");
	}else{
		imgSrc = "/resources/dist/img/file.png";
		getLink = "/displayFile?fileName="+fullName;
	}
	fileName = fullName.substr(fullName.lastIndexOf("_")+1);
	
	return {fileName:fileName , imgSrc:imgSrc , fullName:fullName , getLink:getLink};
}


