import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {Page} from '../models/page.model';
import {Comment} from '../models/comment.model';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  id: string;
  private subscription: Subscription;
  private querySub: Subscription;
  token: string;

  title: string;
  description: string;

  pagesInfo: Page[] = [];
  pages: number[] = [];

  comments: Comment[] = [];
  newComment: string;

  pageNum = -1;
  pageTitle: string;
  pageText: string;

  constructor(private http: HttpClient, private route: Router, private activatedRoute: ActivatedRoute) {
    this.subscription = this.activatedRoute.params.subscribe(params => this.id = params['id']);
    this.querySub = this.activatedRoute.queryParams.subscribe(
      (queryParam: any) => {
        this.pageNum = Number(queryParam['page']);
        console.log(this.pageNum);
        if (isNaN(this.pageNum)) {
          this.pageNum = -1;
        }
      }
    );
    this.token = localStorage.getItem('Authorization');
    if (this.token != null) {

      const headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Authorization': this.token
      };

      console.log(headers);
      http.get('http://localhost:8080/course/' + this.id, {headers})
        .subscribe((response) => {
            console.log(response);
            this.title = response['title'];
            this.description = response['description'];
          },
          (error => {
            localStorage.removeItem('Authorization');
            route.navigate(['/login']);
          }));


      http.get('http://localhost:8080/course/' + this.id + '/pages', {headers})
        .subscribe((response) => {
            console.log(response);
            this.pagesMethod(response);
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

  pagesMethod(response: any): void {
    let i = 0;
    for (const obj of response) {
      console.log(obj);
      this.pages.push(i);
      const page = new Page(obj['id'], obj['courseId'], obj['title'], obj['text']);
      this.pagesInfo.push(page);

      if (i === this.pageNum) {
        this.pageTitle = page.title;
        this.pageText = page.text;
        this.description = '';

        const headers = {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Access-Control-Allow-Headers': 'Content-Type',
          'Authorization': this.token
        };
        this.http.get('http://localhost:8080/pageid/' + obj['id'] + '/comments', {headers})
          .subscribe((responsee) => {
              console.log(responsee);
              this.commentsMethod(responsee);
            },
            (error => {
              console.log('EEERRRRORROOOR');
            }));

      }
      i++;
    }
  }

  commentsMethod(response: any): void {
    for (const obj of response) {
      const comment = new Comment(obj['ownerEmail'], obj['text'], obj['commentData']);
      this.comments.push(comment);
    }
  }

  sendComment(): void {
    const headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Access-Control-Allow-Headers': 'Content-Type',
      'Authorization': this.token
    };

    this.http.post('http://localhost:8080/pageid/' + this.pagesInfo[this.pageNum].id + '/comments/add', {text: this.newComment}, {headers})
      .subscribe((response) => {
          console.log(response);
          this.comments.push(new Comment(response['owner_email'], response['text'], response['comment_data']));
        },
        (error => {
          console.log(error);
        }));
    this.newComment = '';
  }
}
