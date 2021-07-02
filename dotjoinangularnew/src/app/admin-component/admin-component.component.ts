import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {User} from '../models/user.model';

@Component({
  selector: 'app-admin-component',
  templateUrl: './admin-component.component.html',
  styleUrls: ['./admin-component.component.css']
})
export class AdminComponentComponent implements OnInit {

  id: number;
  email: string;
  isBanned: boolean;
  users: User[] = [];

  ngOnInit(): void {
    // tslint:disable-next-line:prefer-const
    let token = localStorage.getItem('Authorization');
    if (token != null) {

      const headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Authorization': token
      };

      this.http.get('http://localhost:8080/users', {headers})
        .subscribe((response) => {
            console.log(response);
            this.takeAllUsers(response);
          },
          (error => {
            this.route.navigate(['/home']);
          }));
    } else {
      this.route.navigate(['/']);
    }
  }


  constructor(private http: HttpClient, private route: Router) {
    this.route = route;
    this.http = http;
  }

  takeAllUsers(response: any): void {
    for (let obj of response) {
      console.log(obj);
      this.id = obj['id'];
      this.email = obj['email'];
      this.isBanned = obj['banned'];
      this.addUser();
    }

  }

  addUser(): void {
    this.users.push(new User(this.id, this.email, this.isBanned));
    this.email = '';
    this.isBanned = false;
  }

  giveBan(id: number): void {
    let token = localStorage.getItem('Authorization');
    if (token != null) {

      const headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Authorization': token
      };

      this.http.post('http://localhost:8080/users/ban/' + id + '/', null, {headers})
        .subscribe((response) => {
            console.log('Deleted/Undeleted');
            this.changeBanned(id);
          },
          (error => {
            console.error(error);
            // this.route.navigate(['/home']);
          }));
    } else {
      this.route.navigate(['/']);
    }
  }

  changeBanned(accountId: number): void {
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.users.length; i++) {
      if (this.users[i].id === accountId) {
        this.users[i].banned = !this.users[i].banned;
        break;
      }
    }
  }


}
