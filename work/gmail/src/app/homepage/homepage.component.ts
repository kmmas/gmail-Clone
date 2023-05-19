import { Component, OnInit } from '@angular/core';
import { BackendService } from '../backend.service';
import { DeleteInfo } from '../models/DeleteParameters';
import { Email } from '../models/email';
import { filtering } from '../models/filter';
import { MoveInfo } from '../models/MoveParameters';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  constructor(private backend: BackendService) {

  }
  ngOnInit() {
    this.address = this.backend.GetAddress();
    this.GetEmailsFromFolder('inbox');
    this.GetFolders();
  }
  address!: string;
  Emails: Email[] = [];
  DefaultFolders: string[] = ['inbox', 'trash', 'sent','draft'];
  UserMadeFolders: string[] = [];
  SelectedTab: string = 'inbox';
  sorting: number = 0;
  choosedfilter: filtering = new filtering();
  SelectedEmailsId: number[] = [];
  DisplayEdit: boolean = false;
  SortByArray = [
    { id: 0, name: "Sort by Date" },
    { id: 1, name: "Sort By priority" },
    { id: 2, name: "Sort By subject" },
    { id: 3, name: "Sort By sender" },
    { id: 4, name: "Sort By body" },
    { id: 5, name: "Sort By number of attachment" },
    { id: 6, name: "Sort By number of receivers" }
  ]
  SubjectFilter: boolean = false;
  SenderFilter: boolean = false;
  ApplyFilter: boolean = false;

  Update() {
    this.GetEmailsFromFolder(this.SelectedTab);
  }

  ResetFilter() {
    this.choosedfilter = new filtering();
    this.ApplyFilter = false;
    this.Update();
  }

  SelectEmail(id: number) {
    if (this.SelectedEmailsId.includes(id)) {
      this.SelectedEmailsId.splice(this.SelectedEmailsId.indexOf(id), 1);
    } else {
      this.SelectedEmailsId.push(id);
    }
    if (this.SelectedEmailsId.length === 0) {
      this.DisplayEdit = false;
    } else {
      this.DisplayEdit = true;
    }
    console.log(this.SelectedEmailsId);
  }

  GetEmailsFromFolder(Foldername: string) {
    this.SelectedTab = Foldername;
    if (!this.ApplyFilter) {
      this.backend.GetEmails(Foldername, this.sorting).subscribe((Response) => { this.Emails = Response });
    }
    else {
      this.backend.GetEmailsFiltered(Foldername, this.sorting, this.choosedfilter).subscribe((Response) => { this.Emails = Response });
    }
    this.SelectedEmailsId=[];
    this.DisplayEdit=false;
  }
  GetFolders() {
    this.backend.GetFolders().subscribe((Response) => { this.UserMadeFolders = Response; console.log(Response); });
  }
  CreateFolder() {
    let foldername: string;
    foldername = prompt("Enter folder name", "new folder")!;
    this.backend.CreateFolder(foldername).subscribe((Response) => { alert(Response); this.GetFolders(); });
  }
  RenameFolder(oldfoldername: string) {
    let foldername: string;
    foldername = prompt("Enter folder name", "new folder")!;
    this.backend.RenameFolder(oldfoldername, foldername).subscribe((Response) => { alert(Response); this.GetFolders(); });
  }
  DeleteFolder(foldername: string) {
    if (!confirm("all emails in that folder will be lost")) {
      return;
    }
    this.backend.deleteFolder(foldername).subscribe((Response) => { alert(Response); this.GetFolders(); });
  }
  MoveEmails() {
    let tofolder: string;
    tofolder = prompt("Enter folder name", "new folder")!;
    if (!this.UserMadeFolders.includes(tofolder) && !this.DefaultFolders.includes(tofolder)) {
      alert("folder doesn't exist");
      this.SelectedEmailsId=[];
      this.DisplayEdit=false;
      this.GetEmailsFromFolder(this.SelectedTab);
      return;
    }
    this.backend.MoveEmail(new MoveInfo(this.SelectedEmailsId, this.SelectedTab, tofolder)).subscribe((Response) => { alert(Response); this.GetEmailsFromFolder(this.SelectedTab); this.SelectedEmailsId = []; this.DisplayEdit = false; });
  }
  DeleteEmailsTmp() {
    if (this.SelectedTab !== 'trash') {
      this.backend.MoveEmail(new MoveInfo(this.SelectedEmailsId, this.SelectedTab, "trash")).subscribe((Response) => { alert(Response); this.GetEmailsFromFolder(this.SelectedTab); this.SelectedEmailsId = []; this.DisplayEdit = false; });
    } else {
      this.backend.DeleteEmail(new DeleteInfo(this.SelectedEmailsId, this.SelectedTab)).subscribe((Response) => { alert(Response); this.GetEmailsFromFolder(this.SelectedTab); this.SelectedEmailsId = []; this.DisplayEdit = false; });
    }
  }

  ViewEmail(email: Email) {
    this.backend.SetEmailForView(email);
    this.backend.SetFolderOfOrigin(this.SelectedTab);
  }
}