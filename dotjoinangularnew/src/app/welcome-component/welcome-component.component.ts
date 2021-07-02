import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpResponse, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-welcome-component',
  templateUrl: './welcome-component.component.html',
  styleUrls: ['./welcome-component.component.css']
})
export class WelcomeComponentComponent {

  response: any;

  constructor(private http: HttpClient, private route: Router) {
    // tslint:disable-next-line:prefer-const
    let token = localStorage.getItem('Authorization');
    if (token != null) {
      route.navigate(['/home']);
    }
  }

}
