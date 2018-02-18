import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EstablishmentsFormComponent } from './establishments-form.component';

describe('EstablishmentsFormComponent', () => {
  let component: EstablishmentsFormComponent;
  let fixture: ComponentFixture<EstablishmentsFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EstablishmentsFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EstablishmentsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
