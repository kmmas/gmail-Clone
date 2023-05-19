package com.example.demo.Controllers.EmailActions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Models.Account;
import com.example.demo.Models.Database;

public class FacadeLayer {
	final String DIRECTORY = System.getProperty("user.home") + "/Desktop/demo/data";
	public String sendEmail(String SenderAddress,Email x) {
		Database.getInstance().GetAccountByEmail(SenderAddress).GetFolderByName("sent").AddEmail(x);
        List<String> RecieversList = x.getRecievers();
        for (String reciever : RecieversList) {
            Database.getInstance().GetAccountByEmail(reciever).GetFolderByName("inbox").AddEmail(x.clone(reciever));
        }
        Database.getInstance().UpdateDatabase();
		return "success";
	}
	
	public ResponseEntity<List<String>> sendAttachment(List<MultipartFile> multipartFiles) throws IOException{
		
		List<String> filenames = new ArrayList<>();
    	for(MultipartFile file :multipartFiles) {
    	String filename = StringUtils.cleanPath(file.getOriginalFilename());
    	File attachment= new File(Database.getInstance().getDirectory(), filename);
        Path fileStorage = attachment.toPath().toAbsolutePath().normalize();
        Files.copy(file.getInputStream(), fileStorage, StandardCopyOption.REPLACE_EXISTING);
        filenames.add(filename);
    	}
    	System.out.println(filenames);
		return ResponseEntity.ok().body(filenames);
	}
	public ResponseEntity<Resource> downloadFiles(String filename) throws IOException{
		File attachment= new File(Database.getInstance().getDirectory(), filename);
       Path filePath = attachment.toPath();
		// Path filePath = Paths.get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
	      if(!Files.exists(filePath)) {
	          throw new FileNotFoundException(filename + " was not found on the server");
	      }
	      Resource resource = new UrlResource(filePath.toUri());
	      HttpHeaders httpHeaders = new HttpHeaders();
	      httpHeaders.add("File-Name", filename);
	      httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
	      return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
	              .headers(httpHeaders).body(resource);
	}
	/////////download attachments will be here
	public String moveEmails( String SenderAddress,MoveParameters info) {
		Account userAccount = Database.getInstance().GetAccountByEmail(SenderAddress);
        int[] ids = info.getIds();
        String from = info.getFromFolder();
        String to = info.getToFolder();
        for (int i = 0; i < ids.length; i++) {
            Email moving = userAccount.GetFolderByName(from).GetEmail(ids[i]);
            userAccount.GetFolderByName(to).AddEmail(moving);
            userAccount.GetFolderByName(from).RemoveEmail(ids[i]);
        }
        Database.getInstance().UpdateDatabase();
		return "success";
	}
	public String deleteEmails(String SenderAddress,DeleteParameters info) {
		 Account userAccount = Database.getInstance().GetAccountByEmail(SenderAddress);
	        int[] ids = info.getIds();
	        String folder = info.getFromFolder();
	        for (int i = 0; i < ids.length; i++) {
	            userAccount.GetFolderByName(folder).RemoveEmail(ids[i]);
	        }
	        Database.getInstance().UpdateDatabase();
	     return"deleted";
	}
	public String SaveEmailToDraft(String email,Email mailtobesaved) {
		Account userAccount = Database.getInstance().GetAccountByEmail(email);
        userAccount.GetFolderByName("draft").AddEmail(mailtobesaved);
        Database.getInstance().UpdateDatabase();
        return"successful";
		
	}
	
	
	

}
