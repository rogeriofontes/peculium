import { TestBed, inject } from '@angular/core/testing';

import { RestaurantsTypesService } from './restaurants-types.service';

describe('RestaurantsTypesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RestaurantsTypesService]
    });
  });

  it('should be created', inject([RestaurantsTypesService], (service: RestaurantsTypesService) => {
    expect(service).toBeTruthy();
  }));
});
