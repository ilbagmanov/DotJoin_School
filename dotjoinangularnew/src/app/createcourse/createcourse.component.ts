import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-createcourse',
  templateUrl: './createcourse.component.html',
  styleUrls: ['./createcourse.component.css']
})
export class CreatecourseComponent implements OnInit {

  message: string;
  title: string;
  description: string;
  price: number;
  token: string;

  constructor(private http: HttpClient, private route: Router) {
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
      description: this.description,
      price: this.price
    };

    const headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Access-Control-Allow-Headers': 'Content-Type',
      'Authorization': this.token
    };

    this.http.post('http://localhost:8080/course_create', jsonObject, {headers})
      .subscribe((response) => {
          console.log(response['Status']);
          this.route.navigate(['/createdcourses']);
        },
        (error) => {
          this.message = 'Bad title';
          console.log('Bad title');
        });
    this.title = '';
    this.description = '';
    this.price = null;
  }

}
