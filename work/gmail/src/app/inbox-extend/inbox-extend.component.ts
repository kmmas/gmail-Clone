import { Component, OnInit } from '@angular/core';
import { BackendService } from '../backend.service';
import { DeleteInfo } from '../models/DeleteParameters';
import { Email } from '../models/email';
import { MoveInfo } from '../models/MoveParameters';
import { HttpErrorResponse, HttpEvent, HttpEventType } from '@angular/common/http';
import {saveAs} from 'file-saver';

@Component({
  selector: 'app-inbox-extend',
  templateUrl: './inbox-extend.component.html',
  styleUrls: ['./inbox-extend.component.css']
})
export class InboxExtendComponent implements OnInit {
  fileStatus = { status: '', requestType: '', percent: 0 };filenames: string[] = [];
  constructor(private backend: BackendService) {

  }
  ngOnInit(): void {
    this.ViewedEmail = this.backend.GetEmailForView();
    this.folder = this.backend.GetFolderOfOrigin();
    this.GetFolders();
    if (this.folder === 'trash' || this.folder === 'sent' || this.folder === 'draft') {
      this.EnableReply = false;
    }
    if (this.folder === 'draft'){
      this.EnableEdit = true;
    }
  }
  ViewedEmail!: Email;
  folder!: string;
  DefaultFolders: string[] = ['inbox', 'trash', 'sent','draft'];
  UserMadeFolders: string[] = [];
  EnableReply: boolean = true;
  EnableEdit: boolean = false;
  GetFolders() {
    this.backend.GetFolders().subscribe((Response) => { this.UserMadeFolders = Response; console.log(Response); });
  }

  MoveEmail() {
    let tofolder: string;
    tofolder = prompt("Enter folder name", "new folder")!;
    if (!this.UserMadeFolders.includes(tofolder) && !this.DefaultFolders.includes(tofolder)) {
      alert("folder doesn't exist");
      return;
    }
    let ids: number[] = [];
    ids.push(this.ViewedEmail.id);
    console.log(ids);
    this.backend.MoveEmail(new MoveInfo(ids, this.folder, tofolder)).subscribe((Response) => { alert(Response); });
  }

  DeleteEmail() {
    let ids: number[] = [];
    ids.push(this.ViewedEmail.id);
    if (this.folder !== 'trash') {
      console.log(ids);
      this.backend.MoveEmail(new MoveInfo(ids, this.folder, "trash")).subscribe((Response) => { alert(Response); });
    } else {
      this.backend.DeleteEmail(new DeleteInfo(ids, this.folder)).subscribe((Response) => { alert(Response); });
    }
  }

  Reply() {
    this.backend.SetComposeMode(1);
  }

  Edit(){
    this.backend.SetComposeMode(2);
  }
  downloadFile(){
    let path= prompt("please Enter a path","suggested path");
    this.backend.download(path!).subscribe(
      event => {
        console.log(event);
        this.resportProgress(event);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }
  private resportProgress(httpEvent: HttpEvent<string[] | Blob>): void {
    switch(httpEvent.type) {
      case HttpEventType.UploadProgress:
        this.updateStatus(httpEvent.loaded, httpEvent.total!, 'Uploading... ');
        break;
      case HttpEventType.DownloadProgress:
        this.updateStatus(httpEvent.loaded, httpEvent.total!, 'Downloading... ');
        break;
      case HttpEventType.ResponseHeader:
        console.log('Header returned', httpEvent);
        break;
      case HttpEventType.Response:
        if (httpEvent.body instanceof Array) {
          this.fileStatus.status = 'done';
          for (const filename of httpEvent.body) {
            this.filenames.unshift(filename);
          }
        } else {
          saveAs(new File([httpEvent.body!], httpEvent.headers.get('File-Name')!, 
                  {type: `${httpEvent.headers.get('Content-Type')};charset=utf-8`}));
          // saveAs(new Blob([httpEvent.body!], 
          //   { type: `${httpEvent.headers.get('Content-Type')};charset=utf-8`}),
          //    httpEvent.headers.get('File-Name'));
        }
        this.fileStatus.status = 'done';
        break;
        default:
          console.log(httpEvent);
          break;
      
    }
  }

  private updateStatus(loaded: number, total: number, requestType: string): void {
    this.fileStatus.status = 'progress';
    this.fileStatus.requestType = requestType;
    this.fileStatus.percent = Math.round(100 * loaded / total);
  }
  
}
