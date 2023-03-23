import { TestBed } from '@angular/core/testing';

import { TranslateTextService } from './translate-text.service';

describe('TranslateTextService', () => {
  let service: TranslateTextService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TranslateTextService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
