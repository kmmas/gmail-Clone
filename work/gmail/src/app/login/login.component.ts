import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faLock } from '@fortawesome/free-solid-svg-icons';
import { BackendService } from '../backend.service';
import { signininfo } from '../models/sigininfo';
import { signupinfo } from '../models/signupinfo';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  faLock = faLock;
  username!: string;
  email!: string;
  password!: string;

  constructor(private backend: BackendService, private route: Router) {

  }
  signin() {
    if (this.email === '' || this.password === '') {
      alert("all fields are required");
      return;
    }
    this.backend.signin(new signininfo(this.email, this.password))
      .subscribe((Response) => {
        switch (Response) {
          case 1:
            this.backend.SetAddress(this.email);
            this.route.navigate(['homepage']);
            break;
          case -1:
            alert("that email isnot resigtered");
            break;
          case 0:
            alert("password is incorrect");
            break;
        }
      });
  }
  signup() {
    if (this.email === '' || this.password === '' || this.username === '') {
      alert("all fields are required");
      return;
    }
    this.backend.signup(new signupinfo(this.username,this.email,this.password)).subscribe((Response)=>{alert(Response);})
  }
}
