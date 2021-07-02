import { Component, OnInit } from '@angular/core';
import {Course} from '../models/course.model';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-createdcourses',
  templateUrl: './createdcourses.component.html',
  styleUrls: ['./createdcourses.component.css']
})
export class CreatedcoursesComponent implements OnInit {

  courses: Course[] = [];
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

    const headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Access-Control-Allow-Headers': 'Content-Type',
      'Authorization': this.token
    };

    this.http.get('http://localhost:8080/createdcourses', {headers})
      .subscribe((response) => {
          this.takeAllCourses(response);
          console.log('ok');
        },
        (error) => {
          console.log('Bad');
        });
  }

  takeAllCourses(response: any): void {
    for (const obj of response) {
      const course = new Course(obj['courseId'], obj['title'], obj['description'], obj['price']);
      this.courses.push(course);
    }

  }


}
