package com.example.demo.Controllers.EmailActions;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Models.Account;
import com.example.demo.Models.Database;

@RestController
@CrossOrigin("http://localhost:4200/")
public class EmailController {
	
	private FacadeLayer f=new FacadeLayer();
    @PostMapping("/{address}/sendemail")
    public String SendEmail(@PathVariable("address") String SenderAddress, @RequestBody Email x) {
    	System.out.println(x);
        return f.sendEmail(SenderAddress, x) ;
    }
    
    @PostMapping("/upload")
    public ResponseEntity<List<String>> sendAttachment(@RequestParam("files")List<MultipartFile> multipartFiles)
    		throws IOException{
    	
    	return f.sendAttachment(multipartFiles);
    }
    @GetMapping("download/{filename}")
  public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        System.out.println(filename);
      return f.downloadFiles(filename);
  }

    @PostMapping("/{address}/moveemails")
    public String MoveEmails(@PathVariable("address") String SenderAddress, @RequestBody MoveParameters info) {
        
        return f.moveEmails(SenderAddress, info);
    }

    @PostMapping("/{address}/deleteemails")
    public String DeleteEmails(@PathVariable("address") String SenderAddress, @RequestBody DeleteParameters info) {
       
        return f.deleteEmails(SenderAddress, info);
    }

    @PostMapping("/{address}/savetodraft")
    public String SaveEmailToDraft(@PathVariable("address") String email,@RequestBody Email mailtobesaved){
        
        return f.SaveEmailToDraft(email, mailtobesaved);
    }
}
