import { TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { ProvinceService } from './province.service';

describe('ProvinceService', () => {
  let service: ProvinceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProvinceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
