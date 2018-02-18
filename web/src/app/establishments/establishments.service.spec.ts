import { TestBed, inject } from '@angular/core/testing';

import { EstablishmentsService } from './establishments.service';

describe('EstablishmentsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EstablishmentsService]
    });
  });

  it('should be created', inject([EstablishmentsService], (service: EstablishmentsService) => {
    expect(service).toBeTruthy();
  }));
});
