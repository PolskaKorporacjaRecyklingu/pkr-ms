import { TestBed } from '@angular/core/testing';

import { RaportsService } from './raports.service';

describe('RaportsService', () => {
  let service: RaportsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RaportsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
