import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatedcoursesComponent } from './createdcourses.component';

describe('CreatedcoursesComponent', () => {
  let component: CreatedcoursesComponent;
  let fixture: ComponentFixture<CreatedcoursesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatedcoursesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatedcoursesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
