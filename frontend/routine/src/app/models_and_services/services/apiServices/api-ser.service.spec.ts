import { TestBed } from '@angular/core/testing';

import { ApiSerService } from './api-ser.service';

describe('ApiSerService', () => {
  let service: ApiSerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiSerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
