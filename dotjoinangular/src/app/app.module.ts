import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule, HttpHeaders} from '@angular/common/http';
import { LoginComponentComponent } from './login-component/login-component.component';
import { RegistrationComponentComponent } from './registration-component/registration-component.component';
import { WelcomeComponentComponent } from './welcome-component/welcome-component.component';
import { AccountComponentComponent } from './account-component/account-component.component';
import { AdminComponentComponent } from './admin-component/admin-component.component';
import { Routes, RouterModule} from '@angular/router';
import { NotFoundComponent } from './not-found/not-found.component';
import { TestComponent } from './test/test.component';

const appRoutes: Routes = [
  {path: '', component: WelcomeComponentComponent},
  {path: 'login', component: LoginComponentComponent},
  {path: 'registration', component: RegistrationComponentComponent},
  {path: 'home', component: AccountComponentComponent},
  {path: 'admin', component: AdminComponentComponent},
  {path: 'test', component: TestComponent},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [
    AppComponent,
    LoginComponentComponent,
    RegistrationComponentComponent,
    WelcomeComponentComponent,
    AccountComponentComponent,
    AdminComponentComponent,
    NotFoundComponent,
    TestComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
