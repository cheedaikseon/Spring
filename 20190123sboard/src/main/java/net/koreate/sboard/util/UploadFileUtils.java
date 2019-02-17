package net.koreate.sboard.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	
	public static String uploadFile(
			String originalName,
			String uploadPath,
			byte[] fileData) throws IOException{
		
		String uploadFileName = "";
		
		UUID uuid = UUID.randomUUID();
		
		String savedName = uuid.toString().replace("-", "")+"_"+originalName;
		
		String path = calcPath(uploadPath);
		
		File file = new File(uploadPath+path,savedName);
		
		FileCopyUtils.copy(fileData, file);
		
		/*uploadFileName = uploadPath+path+File.separator+savedName;*/
		// 확장자 명 가져오기
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		
		if(MediaUtils.getMediaType(formatName) != null) {
			// 타입이 이미지 타입일때
			uploadFileName = makeThumnail(uploadPath, path, savedName);
		}else {
			// 일반 파일 타입일때
			uploadFileName = makeIcon(uploadPath, path, savedName);
		}
		return uploadFileName;
	}
	
	public static ResponseEntity<byte[]> displayFile(String uploadPath, String fileName)
	throws IOException{
		ResponseEntity<byte[]> entity = null;
		
		InputStream in = null;
				
		System.out.println("fileName : " + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			
			System.out.println("formatName : " + formatName);
			
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			in = new FileInputStream(uploadPath+fileName);
			
			HttpHeaders headers = new HttpHeaders();
			
			if(mType != null ) {
				
				headers.setContentType(mType);
				
			}else {
				fileName = fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("content-disposition", "attachment;fileName=\""
						+ new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		return entity;
	}
	
	public static ResponseEntity<String> deleteFile(String uploadPath, String fileName){
		ResponseEntity<String> entity = null;
		
		System.out.println("삭제요청 : " + fileName);
		
		String formatName  = fileName.substring(fileName.lastIndexOf(".")+1);
		
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		if(mType != null) {
			System.out.println("이미지 파일");
			String name = fileName.replace("s_", "");
			new File(uploadPath+(name).replace('/',File.separatorChar)).delete();			
		}
		
		new File(uploadPath+fileName.replace('/', File.separatorChar)).delete();
		
		entity = new ResponseEntity<>("deleted",HttpStatus.OK);
		
		System.out.println("삭제완료");
		return entity;
		
	}
	
	public static String deleteAllFiles(String uploadPath, String[] files) {
		
		for(String file : files) {
			String formatName = file.substring(file.lastIndexOf(".")+1); 
			
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			if(mType != null) {
				String name = file.replace("s_","");
				new File(uploadPath+name.replace('/', File.separatorChar)).delete();
			}
			new File(uploadPath+file.replace('/', File.separatorChar)).delete();			
		}
		
		return "deleted";
		
	}
	
	
	
	
	// 일반 파일 
	public static String makeIcon(String uploadPath,String path,String savedName) {
		String iconName = "";
		String name = uploadPath+path+File.separator+savedName;
		System.out.println("makeIcon : "+name);
		iconName = name.substring(uploadPath.length()).replace(File.separatorChar, '/');
		System.out.println("makeIcon : "+iconName);
		return iconName;
	}
	
	// 이미지 파일 thumnail 생성 및 경로 반환
	public static String makeThumnail(String uploadPath,String path,String savedName) throws IOException{
		// c://spring//upload//2019//01//29//s_121c2d1d212c1d2212311_pattern.png
		
		BufferedImage fileImage = ImageIO.read(new File(uploadPath+path,savedName));
		
		BufferedImage sourceImage = 
				Scalr.resize(fileImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		
		String thumnail = uploadPath+path+File.separator+"s_"+savedName;
		System.out.println("thumname : "+thumnail);
		File file = new File(thumnail);
		
		String formatName = savedName.substring(savedName.lastIndexOf(".")+1);
		ImageIO.write(sourceImage, formatName, file);
		
		String name = thumnail.substring(uploadPath.length()).replace(File.separatorChar, '/');
		System.out.println("thumnail : " + name);
		return name;		
	}
	
	
	
	
	public static String calcPath(String uploadPath) {
		
		String datePath= "";
		Calendar cal = Calendar.getInstance();
		// File.separator   		디렉토리 구분자 
		// File.separatorChar 		
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		
		String monthPath = yearPath
				+ File.separator
				+ new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		
		datePath = monthPath+File.separator
				+ new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		mkDir(uploadPath,yearPath,monthPath,datePath);
		
		System.out.println(datePath);
		return datePath;
	}
	
	public static void mkDir(String uploadPath,String... path) {
		if(new File(path[path.length-1]).exists()) {
			return;
		}
		for(String p : path) {
			File file = new File(uploadPath+p) ;
			if(!file.exists()) {
				file.mkdir();
			}
		}
	}
	
	

}
