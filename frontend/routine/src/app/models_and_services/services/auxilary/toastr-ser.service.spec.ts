import { TestBed } from '@angular/core/testing';

import { ToastrSerService } from './toastr-ser.service';

describe('ToastrSerService', () => {
  let service: ToastrSerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ToastrSerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
