import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantTypeFormComponent } from './restaurant-type-form.component';

describe('RestaurantTypeFormComponent', () => {
  let component: RestaurantTypeFormComponent;
  let fixture: ComponentFixture<RestaurantTypeFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RestaurantTypeFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RestaurantTypeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
