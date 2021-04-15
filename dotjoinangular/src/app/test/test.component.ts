import { Component, OnInit } from '@angular/core';
import {User} from '../models/user.model';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent {

  title = 'dotjoinangular';

  email: string;
  response: any;

  users: User[] = [];

  constructor(private http: HttpClient) {

  }

  // tslint:disable-next-line:typedef
  search() {
    this.http.get('http://localhost:8080/welcome')
      .subscribe((response) => {
        this.response = response;
        console.log(this.response);
      });
  }

  addUser(): void {
    this.users.push(new User(0,this.email, false));
    this.email = '';
  }

}
