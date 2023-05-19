import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotfoundComponent } from './notfound/notfound.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ComposeComponent } from './compose/compose.component';
import { ContactsComponent } from './contacts/contacts.component';
import { InboxExtendComponent } from './inbox-extend/inbox-extend.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'homepage', component: HomepageComponent },
  { path: 'compose', component: ComposeComponent },
  { path: 'contacts', component: ContactsComponent },
  { path: 'viewemail', component: InboxExtendComponent },
  { path: '**', component: NotfoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
