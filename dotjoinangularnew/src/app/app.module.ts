import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {RouterModule, Routes} from '@angular/router';
import {WelcomeComponentComponent} from './welcome-component/welcome-component.component';
import {LoginComponentComponent} from './login-component/login-component.component';
import {RegistrationComponentComponent} from './registration-component/registration-component.component';
import {AccountComponentComponent} from './account-component/account-component.component';
import {AdminComponentComponent} from './admin-component/admin-component.component';
import {TestComponent} from './test/test.component';
import {CreatecourseComponent} from './createcourse/createcourse.component';
import {CreatecoursepageComponent} from './createcoursepage/createcoursepage.component';
import {MycoursesComponent} from './mycourses/mycourses.component';
import {CourseComponent} from './course/course.component';
import {CourselistComponent} from './courselist/courselist.component';
import {CreatedcoursesComponent} from './createdcourses/createdcourses.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {NgbPaginationModule, NgbAlertModule} from '@ng-bootstrap/ng-bootstrap';

const appRoutes: Routes = [
  {path: '', component: WelcomeComponentComponent},
  {path: 'login', component: LoginComponentComponent},
  {path: 'registration', component: RegistrationComponentComponent},
  {path: 'home', component: AccountComponentComponent},
  {path: 'admin', component: AdminComponentComponent},
  {path: 'test', component: TestComponent},

  {path: 'create_course', component: CreatecourseComponent},
  {path: 'course/:id/add', component: CreatecoursepageComponent},
  {path: 'mycourses', component: MycoursesComponent},
  {path: 'course/:id', component: CourseComponent},
  {path: 'courses', component: CourselistComponent},
  {path: 'createdcourses', component: CreatedcoursesComponent},

  {path: '**', component: NotFoundComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponentComponent,
    RegistrationComponentComponent,
    WelcomeComponentComponent,
    AccountComponentComponent,
    AdminComponentComponent,
    NotFoundComponent,
    TestComponent,
    CourselistComponent,
    CreatecourseComponent,
    CreatecoursepageComponent,
    CourseComponent,
    MycoursesComponent,
    CreatedcoursesComponent
  ],
  imports: [
    NgbPaginationModule, NgbAlertModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
