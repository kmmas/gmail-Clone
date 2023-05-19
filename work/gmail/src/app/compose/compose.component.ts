import { Component, OnInit } from '@angular/core';
import { BackendService } from '../backend.service';
import { DeleteInfo } from '../models/DeleteParameters';
import { Email } from '../models/email';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-compose',
  templateUrl: './compose.component.html',
  styleUrls: ['./compose.component.css']
})
export class ComposeComponent implements OnInit {
  constructor(private backend: BackendService) {

  }
  ngOnInit(): void {
    switch (this.backend.GetComposeMode()) {
      case 1:
        this.LoadEmailToReply();
        break;
      case 2:
        this.LoadEmailToEdit();
        this.OverWrite = true;
        break;
      case 3:
        this.LoadContact();
        break;
      default:
        break;
    }
    this.backend.SetComposeMode(0);
    this.address = this.backend.GetAddress();
  }
  address!: string;
  WrittenEmail!: Email;
  receivers: string = '';
  subject: string = '';
  body: string = '';
  priority: number = 0;
  OverWrite: boolean = false;
  attachmentsList:string[]=[];
  onUploadFiles(files:File[]){
    const formData = new FormData();
    for (let file of files) { 
      this.attachmentsList.push(file.name);
      formData.append('files', file, file.name);
     }
      console.log(this.attachmentsList);
    this.backend.upload(formData).subscribe(
      event => {
        console.log(event);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }
  Send() {
    if (this.receivers === '') {
      alert("no one to send to");
      return;
    }
    this.WrittenEmail = new Email(this.address, this.receivers.split(","), this.priority, this.body, this.subject, new Date(),this.attachmentsList);
    this.backend.SendEmail(this.WrittenEmail).subscribe((Response) => { alert(Response); });
  }

  LoadEmailToReply() {
    let email: Email = this.backend.GetEmailForView();
    this.receivers = email.sender;
    this.subject = 're: ' + email.subject;
    this.body = email.body + "\n------------\n";
  }

  LoadEmailToEdit() {
    let email: Email = this.backend.GetEmailForView();
    this.receivers = email.recievers.join(",");
    this.subject = email.subject;
    this.body = email.body;
  }

  LoadContact() {
    this.receivers = this.backend.GetContactAddress();
  }

  SaveToDraft() {
    this.WrittenEmail = new Email(this.address, this.receivers.split(","), this.priority, this.body, this.subject, new Date(),this.attachmentsList);
    if (this.OverWrite) {
      let ids: number[] = [];
      ids.push(this.backend.GetEmailForView().id);
      console.log(ids);
      this.backend.DeleteEmail(new DeleteInfo(ids, "draft")).subscribe((Response) => { console.log(Response); });
      this.backend.SaveEmailToDraft(this.WrittenEmail).subscribe((Response) => { alert(Response); });
    } else {
      this.backend.SaveEmailToDraft(this.WrittenEmail).subscribe((Response) => { alert(Response); });
    }
  }
}
