import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CouponFormComponent } from './coupon-form.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { KeycloakAngularModule } from 'keycloak-angular';
import { TranslateModule } from '@ngx-translate/core';
import { FormsModule } from '@angular/forms';

describe('CouponFormComponent', () => {
  let component: CouponFormComponent;
  let fixture: ComponentFixture<CouponFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule, HttpClientTestingModule,
        KeycloakAngularModule, TranslateModule.forRoot(),
        FormsModule
      ],
      declarations: [ CouponFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CouponFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
