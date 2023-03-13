import { TestBed } from '@angular/core/testing';

import { CatchErrorService } from './catch-error.service';

describe('CatchErrorService', () => {
  let service: CatchErrorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CatchErrorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
