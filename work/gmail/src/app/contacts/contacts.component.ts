import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../backend.service';
import { contact } from '../models/contact';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {

  constructor(private backend: BackendService, private route: Router) {

  }

  ngOnInit(): void {
    this.GetContacts();
    this.address = this.backend.GetAddress();
  }
  address!: string;
  listofcontacts!: contact[];
  GetContacts() {
    this.backend.GetContacts().subscribe((Response) => { this.listofcontacts = Response; });
  }

  addcontact() {
    let name: string = prompt("enter contact name", "name")!;
    this.backend.AddContact(name).subscribe((Response) => { alert(Response); this.GetContacts(); });
  }

  renamecontact(oldname: string) {
    let name: string = prompt("enter contact name", "name")!;
    this.backend.RenameContact(oldname, name).subscribe((Response) => { alert(Response); this.GetContacts(); });
  }
  removecontact(contactname: string) {
    this.backend.RemoveContact(contactname).subscribe((Response) => { alert(Response); this.GetContacts(); });
  }
  addemail(contactname: string) {
    let newaddress: string = prompt("enter email address", "email")!;
    this.backend.AddAddress(contactname, newaddress).subscribe((Response) => { alert(Response); this.GetContacts(); });
  }
  removeemail(contactname: string, address: string) {
    this.backend.RemoveAddress(contactname, address).subscribe((Response) => { alert(Response); this.GetContacts(); });
  }
  Sendto(emailaddress: string){
    this.backend.SetContactAddress(emailaddress);
    this.backend.SetComposeMode(3);
  }
}
