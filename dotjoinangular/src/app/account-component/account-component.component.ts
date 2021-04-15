import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {User} from '../models/user.model';

@Component({
  selector: 'app-account-component',
  templateUrl: './account-component.component.html',
  styleUrls: ['./account-component.component.css']
})
export class AccountComponentComponent {

  constructor(private http: HttpClient, private route: Router) {
    // tslint:disable-next-line:prefer-const
    let token = localStorage.getItem('Authorization');
    if (token != null) {

      const headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Authorization': token
      };

      console.log(headers);

      http.get('http://localhost:8080/home', {headers})
        .subscribe((response) => {
            console.log(response);
          },
          (error => {
            localStorage.removeItem('Authorization');
            route.navigate(['/login']);
          }));
    } else {
      route.navigate(['/']);
    }
  }

  logout(): void {
    localStorage.removeItem('Authorization');
    this.route.navigate(['/']);
  }

}
