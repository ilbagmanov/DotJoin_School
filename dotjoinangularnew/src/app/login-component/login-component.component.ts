import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent {

  email: string;
  password: string;
  message: string;

  constructor(private http: HttpClient, private route: Router) {
    let token = localStorage.getItem('Authorization');
    console.log(token);
    if (token != null) {
      this.route.navigate(['/home']);
    }
  }

  signIn(): void {
    this.http.post('http://localhost:8080/login', {email: this.email, password: this.password})
      .subscribe((response) => {
          console.log(response['token']);
          localStorage.setItem('Authorization', response['token']);
          this.route.navigate(['/home']);
        },
        (error) => {
          this.message = 'Bad login/password';
          console.log('Bad login/password');
        });
    this.email = '';
    this.password = '';
  }

}
