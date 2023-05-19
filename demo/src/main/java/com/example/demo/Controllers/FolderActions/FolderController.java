package com.example.demo.Controllers.FolderActions;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Controllers.EmailActions.Email;
import com.example.demo.Models.Account;
import com.example.demo.Models.Database;
import com.example.demo.Models.Filtering.AndCriteria;
import com.example.demo.Models.Filtering.OrCriteria;
import com.example.demo.Models.Filtering.SenderCriteria;
import com.example.demo.Models.Filtering.SubjectCriteria;
import com.example.demo.Models.Sorting.SortByAttachment;
import com.example.demo.Models.Sorting.SortByBody;
import com.example.demo.Models.Sorting.SortByDate;
import com.example.demo.Models.Sorting.SortByPriority;
import com.example.demo.Models.Sorting.SortByRecievers;
import com.example.demo.Models.Sorting.SortBySender;
import com.example.demo.Models.Sorting.SortBySubject;

@RestController
@CrossOrigin("http://localhost:4200/")
public class FolderController {
    @GetMapping("/{address}/{folder}/getemailssortedby/{sorting}")
    public List<Email> GetEmailsFromFolder(@PathVariable("address") String email,
            @PathVariable("folder") String foldername, @PathVariable("sorting") int sorting) {
        List<Email> allemails = Database.getInstance().GetAccountByEmail(email).GetFolderByName(foldername)
                .GetAllEmails();

        allemails.sort(new SortByDate());
        switch (sorting) {
            case 1:
                allemails.sort(new SortByPriority());
                break;
            case 2:
                allemails.sort(new SortBySubject());
                break;
            case 3:
                allemails.sort(new SortBySender());
                break;
            case 4:
                allemails.sort(new SortByBody());
                break;
            case 5:
                allemails.sort(new SortByAttachment());
                break;
            case 6:
                allemails.sort(new SortByRecievers());
                break;
            default:
                break;
        }
        return allemails;
    }

    @PostMapping("/{address}/newfolder")
    public String CreateNewFolder(@PathVariable("address") String email, @RequestBody String foldername) {
        Account userAccount = Database.getInstance().GetAccountByEmail(email);
        if (userAccount.FolderNameTaken(foldername)) {
            return "name taken";
        }
        userAccount.CreateNewFolder(foldername);
        Database.getInstance().UpdateDatabase();
        return "successful";
    }

    @PostMapping("/{address}/{folder}/renamefolder")
    public String RenameFolder(@PathVariable("address") String email, @PathVariable("folder") String oldfoldername,
            @RequestBody String newfoldername) {
        Account userAccount = Database.getInstance().GetAccountByEmail(email);
        if (userAccount.FolderNameTaken(newfoldername)) {
            return "name taken";
        }
        userAccount.RenameFolder(oldfoldername, newfoldername);
        Database.getInstance().UpdateDatabase();
        return "successful";
    }

    @DeleteMapping("/{address}/{folder}/deletefolder")
    public String DeleteFolder(@PathVariable("address") String email, @PathVariable("folder") String foldername) {
        Account userAccount = Database.getInstance().GetAccountByEmail(email);
        userAccount.RemoveFolderByName(foldername);
        Database.getInstance().UpdateDatabase();
        return "successful";
    }

    @GetMapping("/{address}/getuserfolders")
    public List<String> GetFolders(@PathVariable("address") String email) {
        Account userAccount = Database.getInstance().GetAccountByEmail(email);
        return userAccount.GetUserMadeFolders();
    }

    @DeleteMapping("/{address}/{folder}/clearfolder")
    public String ClearFolder(@PathVariable("address") String email, @PathVariable("folder") String foldername) {
        Account userAccount = Database.getInstance().GetAccountByEmail(email);
        userAccount.GetFolderByName(foldername).getEmailsList().clear();
        return "successful";
    }

    @PostMapping("/{address}/{folder}/getemailsfiltered/{sorting}")
    public List<Email> test(@PathVariable("address") String email,
            @PathVariable("folder") String foldername, @PathVariable("sorting") int sorting,
            @RequestBody FilterParameters x) {
        List<Email> allemails = Database.getInstance().GetAccountByEmail(email).GetFolderByName(foldername)
                .GetAllEmails();
        String subject = x.getSubject();
        String sender = x.getSender();
        boolean SubjectValid = (subject != null && !subject.isEmpty());
        boolean SenderValid = (sender != null && !sender.isEmpty());
        if (SubjectValid && SenderValid) {
            if (x.getOption() == 1) {
                allemails = new AndCriteria(new SubjectCriteria(subject), new SenderCriteria(sender))
                        .MeetCriteria(allemails);
            } else {
                allemails = new OrCriteria(new SubjectCriteria(subject), new SenderCriteria(sender))
                        .MeetCriteria(allemails);
            }
        } else if (SubjectValid) {
            allemails = new SubjectCriteria(subject).MeetCriteria(allemails);
        } else if (SenderValid) {
            allemails = new SenderCriteria(sender).MeetCriteria(allemails);
        }
        allemails.sort(new SortByDate());
        switch (sorting) {
            case 1:
                allemails.sort(new SortByPriority());
                break;
            case 2:
                allemails.sort(new SortBySubject());
                break;
            case 3:
                allemails.sort(new SortBySender());
                break;
            case 4:
                allemails.sort(new SortByBody());
                break;
            case 5:
                allemails.sort(new SortByAttachment());
                break;
            case 6:
                allemails.sort(new SortByRecievers());
                break;
            default:
                break;
        }
        return allemails;
    }
}
