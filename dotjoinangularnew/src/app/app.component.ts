import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'dotjoinangularnew';
  flag = false;

  constructor(private http: HttpClient, private route: Router) {
    if (localStorage.getItem('Authorization') == null) {
      this.flag = true;
    }
  }

  logout(): void {
    localStorage.removeItem('Authorization');
    this.route.navigate(['/']);
  }
}
