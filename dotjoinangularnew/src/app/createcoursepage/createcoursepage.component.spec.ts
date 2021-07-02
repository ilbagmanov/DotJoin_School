import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatecoursepageComponent } from './createcoursepage.component';

describe('CreatecoursepageComponent', () => {
  let component: CreatecoursepageComponent;
  let fixture: ComponentFixture<CreatecoursepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatecoursepageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatecoursepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
