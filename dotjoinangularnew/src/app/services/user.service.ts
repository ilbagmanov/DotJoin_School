import {Injectable} from '@angular/core';
import {User} from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  getUsers(): User[] {
    return [
      new User(0,'Ildarik', false),
      new User(0,'Bro', false)
    ];
  }

  constructor() {
  }
}
