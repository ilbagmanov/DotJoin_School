import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-createcoursepage',
  templateUrl: './createcoursepage.component.html',
  styleUrls: ['./createcoursepage.component.css']
})
export class CreatecoursepageComponent implements OnInit {

  private subscription: Subscription;
  id: number;
  message: string;
  title: string;
  text: string;
  token: string;

  constructor(private http: HttpClient, private route: Router, private activatedRoute: ActivatedRoute) {
    this.subscription = this.activatedRoute.params.subscribe(params => this.id = params['id']);

    this.token = localStorage.getItem('Authorization');
    if (this.token != null) {

      const headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Authorization': this.token
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

  ngOnInit(): void {
  }

  create(): void {

    const jsonObject = {
      title: this.title,
      text: this.text,
    };

    console.log(jsonObject);

    const headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Access-Control-Allow-Headers': 'Content-Type',
      'Authorization': this.token
    };

    this.http.post('http://localhost:8080/course/' + this.id + '/add', jsonObject, {headers})
      .subscribe((response) => {
          console.log(response['Status']);
          this.route.navigate(['/createdcourses']);
        },
        (error) => {
          this.message = 'Bad title';
          console.log('Bad title');
        });
    this.title = '';
    this.text = '';
  }

}
