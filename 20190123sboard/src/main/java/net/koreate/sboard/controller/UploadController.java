package net.koreate.sboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.koreate.sboard.util.UploadFileUtils;

@Controller
public class UploadController {
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		System.out.println("업로드 화면 요청");
		System.out.println("uploadPath : " + uploadPath);
		
		File file = new File(uploadPath);
		
		if(!file.exists()) {
			file.mkdirs();
			System.out.println("경로 생성 완료");
		}
	}
	
	@PostMapping("/uploadForm")
	public String uploadForm(MultipartFile file,Model model) throws IOException{
		
		System.out.println("file name : " + file.getOriginalFilename());
		System.out.println("file size : " + file.getSize());
		System.out.println("file type : " + file.getContentType());
		
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
		model.addAttribute("savedName",savedName);
		return "uploadResult";
	}
	
	@PostMapping("/uploadForm1")
	public String uploadForm(MultipartFile[] file, Model model) throws IOException{
		
		String[] savedNames = new String[file.length];
		
		for(int i=0; i< file.length; i++) {
			
			savedNames[i] = uploadFile(file[i].getOriginalFilename(), file[i].getBytes());
			
		}
		
		model.addAttribute("savedNames",savedNames);
		
		return "uploadResult";
	}
	
	@PostMapping("/uploadForm2")
	public String uploadForm(MultipartHttpServletRequest request, Model model) throws IOException{
		
		String auth = request.getParameter("auth");
		MultipartFile file = request.getFile("file");
		MultipartFile file1 = request.getFile("file1");
		
		System.out.println("auth : " + auth);
		String originalName = file.getOriginalFilename();
		String originalName1 = file1.getOriginalFilename();
		
		String[] savedNames = new String[2];
		savedNames[0] = uploadFile(originalName,file.getBytes());
		savedNames[1] = uploadFile(originalName1,file1.getBytes());
		
		model.addAttribute("savedNames",savedNames);
		return "uploadResult";
	}
	
	@PostMapping("/uploadForm3")
	public String uploadForm3(MultipartHttpServletRequest request, Model model) throws IOException{
		String auth = request.getParameter("auth");
		System.out.println("auth : "+ auth);
		
		MultipartFile file1 = request.getFile("file1");
		String originalName1 = file1.getOriginalFilename();
		System.out.println("file1 : "+originalName1);
		List<MultipartFile> fileList = request.getFiles("file");
		String[] savedNames = new String[fileList.size() + 1];
		
		for(int i=0; i<fileList.size(); i++) {
			String savedName = uploadFile(fileList.get(i).getOriginalFilename(),fileList.get(i).getBytes());
			savedNames[i] = savedName;
		}
		
		savedNames[savedNames.length-1] = uploadFile(originalName1,file1.getBytes());
		
		model.addAttribute("savedNames",savedNames);
		return "uploadResult";
	}
	
	@GetMapping("/uploadAjax")
	public String uploadAjax(){
		return "uploadAjax";
	}
	
	@ResponseBody
	@PostMapping(value="/uploadAjax", produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws IOException{
		String originalName = file.getOriginalFilename();
		
		System.out.println("file : " + originalName);
		
		return new ResponseEntity<>(
						UploadFileUtils.uploadFile(
								file.getOriginalFilename(), 
								uploadPath, 
								file.getBytes()
						),
					HttpStatus.CREATED);
	}
	
	@ResponseBody
	@GetMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws IOException{
		return UploadFileUtils.displayFile(uploadPath, fileName);
	}
	
	@ResponseBody
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){
		return UploadFileUtils.deleteFile(uploadPath,fileName);
	}
	
	@ResponseBody
	@PostMapping("/deleteAllFiles")
	public ResponseEntity<String> deleteFiles(@RequestParam("files[]") String[] files){
		ResponseEntity<String> entity = null;
		if(files == null || files.length == 0) {
			entity = new ResponseEntity<>("deleted",HttpStatus.OK);
		}else {
			entity = new ResponseEntity<>(
					UploadFileUtils.deleteAllFiles(
							uploadPath,
							files
							),
					HttpStatus.OK);
		}		
		return entity;
	}
	
	
	public String uploadFile(String originalName, byte[] fileData) throws IOException{
		
		UUID uid = UUID.randomUUID();
		
		System.out.println(uid.toString());
		
		String savedName = uid.toString().replace("-", "")+"_"+originalName;
		System.out.println(savedName);		

		File file = new File(uploadPath,savedName);
		
		FileCopyUtils.copy(fileData, file);
		
		return savedName;
	}
	
	
}
