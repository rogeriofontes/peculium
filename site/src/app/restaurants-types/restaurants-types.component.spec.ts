import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantsTypesComponent } from './restaurants-types.component';

describe('RestaurantsTypesComponent', () => {
  let component: RestaurantsTypesComponent;
  let fixture: ComponentFixture<RestaurantsTypesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RestaurantsTypesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RestaurantsTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
