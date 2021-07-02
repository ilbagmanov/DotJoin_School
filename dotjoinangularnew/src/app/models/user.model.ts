export class User {
  id: number;
  email: string;
  banned: boolean;

  constructor(id: number, email: string, banned: boolean) {
    this.id = id;
    this.email = email;
    this.banned = banned;
  }
}
