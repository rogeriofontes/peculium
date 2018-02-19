import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovementsFormComponent } from './movements-form.component';

describe('MovementsFormComponent', () => {
  let component: MovementsFormComponent;
  let fixture: ComponentFixture<MovementsFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovementsFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovementsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
