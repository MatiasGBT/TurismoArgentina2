import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CouponsComponent } from './coupons.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { KeycloakAngularModule } from 'keycloak-angular';
import { TranslateModule } from '@ngx-translate/core';

describe('CouponsComponent', () => {
  let component: CouponsComponent;
  let fixture: ComponentFixture<CouponsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, KeycloakAngularModule, TranslateModule.forRoot()],
      declarations: [ CouponsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CouponsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
