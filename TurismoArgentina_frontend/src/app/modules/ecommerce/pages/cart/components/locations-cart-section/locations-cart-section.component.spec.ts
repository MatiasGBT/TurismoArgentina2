import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { LocationsCartSectionComponent } from './locations-cart-section.component';

describe('LocationsCartSectionComponent', () => {
  let component: LocationsCartSectionComponent;
  let fixture: ComponentFixture<LocationsCartSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TranslateModule.forRoot(), HttpClientTestingModule],
      declarations: [ LocationsCartSectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocationsCartSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
