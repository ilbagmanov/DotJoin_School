import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration-component',
  templateUrl: './registration-component.component.html',
  styleUrls: ['./registration-component.component.css']
})
export class RegistrationComponentComponent {

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

  registration(): void {
    this.http.post('http://localhost:8080/registration', {email: this.email, password: this.password})
      .subscribe((response) => {
          this.route.navigate(['/login']);
        },
        (error) => {
          this.message = 'Please, change email/password';
        });
    this.email = '';
    this.password = '';
  }

}
