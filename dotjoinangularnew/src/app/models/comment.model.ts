import {Timestamp} from 'rxjs';

export class Comment {

  email: string;
  text: string;
  date: Timestamp<string>;

  constructor(email: string, text: string, date: Timestamp<string>) {
    this.email = email;
    this.text = text;
    this.date = date;
  }
}
