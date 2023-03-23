import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocationsCartSectionComponent } from './locations-cart-section.component';

describe('LocationsCartSectionComponent', () => {
  let component: LocationsCartSectionComponent;
  let fixture: ComponentFixture<LocationsCartSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
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
