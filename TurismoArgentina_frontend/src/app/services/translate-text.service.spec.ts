import { TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { TranslateTextService } from './translate-text.service';

describe('TranslateTextService', () => {
  let service: TranslateTextService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [TranslateModule.forRoot()]
    });
    service = TestBed.inject(TranslateTextService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
