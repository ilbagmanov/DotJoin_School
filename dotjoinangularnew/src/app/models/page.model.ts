export class Page {
  id: number;
  courseId: number;
  text: string;
  title: string;

  constructor(id: number, courseId: number, title: string, text: string) {
    this.id = id;
    this.courseId = courseId;
    this.title = title;
    this.text = text;
  }
}
