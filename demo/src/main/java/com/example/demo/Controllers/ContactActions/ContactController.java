package com.example.demo.Controllers.ContactActions;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Account;
import com.example.demo.Models.Contact;
import com.example.demo.Models.Database;

@RestController
@CrossOrigin("http://localhost:4200/")
public class ContactController {

    @GetMapping("/{address}/getcontacts")
    public List<Contact> GetUserContacts(@PathVariable("address") String email) {
        Account userAccount = Database.getInstance().GetAccountByEmail(email);
        return userAccount.getContacts();
    }

    @PostMapping("/{address}/newcontact")
    public String CreateNewContact(@PathVariable("address") String email, @RequestBody String contactname){
        Account userAccount = Database.getInstance().GetAccountByEmail(email);
        if(userAccount.ContactNameTaken(contactname)){
            return "contact already exists";
        }
        userAccount.CreateNewContact(contactname);
        Database.getInstance().UpdateDatabase();
        return "successful";
    }

    @PostMapping("{address}/{contact}/renamecontact")
    public String RenameContact(@PathVariable("address") String email, @PathVariable("contact") String contactname, @RequestBody String newcontactname){
        Account userAccount = Database.getInstance().GetAccountByEmail(email);
        if(userAccount.ContactNameTaken(newcontactname)){
            return "name taken";
        }
        userAccount.RenameContact(contactname, newcontactname);
        Database.getInstance().UpdateDatabase();
        return "successful";
    }

    @DeleteMapping("/{address}/{contact}/deletecontact")
    public String DeleteContact(@PathVariable("address") String email, @PathVariable("contact") String contactname) {
        Account userAccount = Database.getInstance().GetAccountByEmail(email);
        userAccount.RemoveContactByName(contactname);
        Database.getInstance().UpdateDatabase();
        return "successful";
    }

    @PostMapping("/{address}/{contact}/addaddress")
    public String AddAddressToContact(@PathVariable("address") String email, @PathVariable("contact") String contactname, @RequestBody String newaddress){
        Account userAccount = Database.getInstance().GetAccountByEmail(email);
        Contact x = userAccount.GetContactByName(contactname);
        if (x.AddressTaken(newaddress)){
            return "address already there";
        }
        x.AddAddress(newaddress);
        Database.getInstance().UpdateDatabase();
        return "successful";
    }

    @PostMapping("/{address}/{contact}/deleteaddress")
    public String RemoveAddressFromContact(@PathVariable("address") String email, @PathVariable("contact") String contactname, @RequestBody String addressname){
        Account userAccount = Database.getInstance().GetAccountByEmail(email);
        userAccount.GetContactByName(contactname).RemoveAddress(addressname);
        Database.getInstance().UpdateDatabase();
        return "successful";
    }
}
