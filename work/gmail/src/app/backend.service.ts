import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Email } from './models/email';
import { MoveInfo } from './models/MoveParameters';
import { signininfo } from './models/sigininfo';
import { signupinfo } from './models/signupinfo';
import { contact } from './models/contact';
import { DeleteInfo } from './models/DeleteParameters';
import { filtering } from './models/filter';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  constructor(private http: HttpClient) { }
  address!: string;
  emailforview!: Email;
  FolderOfOrigin!: string;
  ContactAddress!: string;
  ComposeMode!: number;

  SetComposeMode(mode: number) {
    this.ComposeMode = mode;
  }
  GetComposeMode(): number {
    return this.ComposeMode;
  }
  SetAddress(address: string) {
    this.address = address;
  }

  GetAddress(): string {
    return this.address;
  }

  SetEmailForView(email: Email) {
    this.emailforview = email;
  }

  GetEmailForView(): Email {
    return this.emailforview;
  }

  SetFolderOfOrigin(folder: string) {
    this.FolderOfOrigin = folder;
  }

  GetFolderOfOrigin(): string {
    return this.FolderOfOrigin;
  }

  SetContactAddress(address: string){
    this.ContactAddress = address;
  }

  GetContactAddress(){
    return this.ContactAddress;
  }

  signin(info: signininfo): Observable<number> {
    return this.http.post<number>("http://localhost:8080/signin", info);
  }

  signup(info: signupinfo) {
    return this.http.post("http://localhost:8080/signup", info, { responseType: 'text' });
  }

  GetEmails(foldername: string, Sorting: number): Observable<Email[]> {
    return this.http.get<Email[]>("http://localhost:8080/" + this.address + "/" + foldername + "/getemailssortedby/" + Sorting);
  }

  GetEmailsFiltered(foldername: string, Sorting: number, info: filtering): Observable<Email[]> {
    return this.http.post<Email[]>("http://localhost:8080/" + this.address + "/" + foldername + "/getemailsfiltered/" + Sorting, info);
  }

  GetFolders(): Observable<string[]> {
    return this.http.get<string[]>("http://localhost:8080/" + this.address + "/getuserfolders");
  }

  CreateFolder(foldername: string) {
    return this.http.post("http://localhost:8080/" + this.address + "/newfolder", foldername, { responseType: 'text' });
  }

  RenameFolder(oldfoldername: string, newfoldername: string) {
    return this.http.post("http://localhost:8080/" + this.address + "/" + oldfoldername + "/renamefolder", newfoldername, { responseType: 'text' });
  }

  deleteFolder(foldername: string) {
    return this.http.delete("http://localhost:8080/" + this.address + "/" + foldername + "/deletefolder", { responseType: 'text' });
  }

  MoveEmail(info: MoveInfo) {
    return this.http.post("http://localhost:8080/" + this.address + "/moveemails", info, { responseType: 'text' });
  }

  DeleteEmail(info: DeleteInfo) {
    return this.http.post("http://localhost:8080/" + this.address + "/deleteemails", info, { responseType: 'text' });
  }

  GetContacts(): Observable<contact[]> {
    return this.http.get<contact[]>("http://localhost:8080/" + this.address + "/getcontacts");
  }

  AddContact(contactname: string) {
    return this.http.post("http://localhost:8080/" + this.address + "/newcontact", contactname, { responseType: 'text' });
  }

  RenameContact(oldname: string, newname: string) {
    return this.http.post("http://localhost:8080/" + this.address + "/" + oldname + "/renamecontact", newname, { responseType: 'text' });
  }

  RemoveContact(contactname: string) {
    return this.http.delete("http://localhost:8080/" + this.address + "/" + contactname + "/deletecontact", { responseType: 'text' });
  }

  AddAddress(contactname: string, newaddress: string) {
    return this.http.post("http://localhost:8080/" + this.address + "/" + contactname + "/addaddress", newaddress, { responseType: 'text' });
  }

  RemoveAddress(contactname: string, address: string) {
    return this.http.post("http://localhost:8080/" + this.address + "/" + contactname + "/deleteaddress", address, { responseType: 'text' });
  }

  SendEmail(email: Email) {
    return this.http.post("http://localhost:8080/" + this.address + "/sendemail", email, { responseType: 'text' });
  }

  SaveEmailToDraft(email: Email) {
    return this.http.post("http://localhost:8080/" + this.address + "/savetodraft", email, { responseType: 'text' });
  }
  url ="http://localhost:8080";
  upload(formData: FormData): Observable<HttpEvent<string[]>> {
    return this.http.post<string[]>("http://localhost:8080/upload", formData, {
      reportProgress: true,
      observe: 'events'
    });
  }
  download(filename: string): Observable<HttpEvent<Blob>> {
    return this.http.get(`${this.url}/download/${filename}`, {
      reportProgress: true,
      observe: 'events',
      responseType: 'blob'
    });
  }
}
