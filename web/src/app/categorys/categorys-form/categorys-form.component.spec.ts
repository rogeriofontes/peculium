import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CategorysFormComponent } from './categorys-form.component';

describe('CategorysFormComponent', () => {
  let component: CategorysFormComponent;
  let fixture: ComponentFixture<CategorysFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CategorysFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CategorysFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
