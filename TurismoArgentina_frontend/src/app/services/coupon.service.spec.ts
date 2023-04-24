import { TestBed } from '@angular/core/testing';

import { CouponService } from './coupon.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { KeycloakAngularModule } from 'keycloak-angular';

describe('CouponService', () => {
  let service: CouponService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, KeycloakAngularModule]
    });
    service = TestBed.inject(CouponService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
