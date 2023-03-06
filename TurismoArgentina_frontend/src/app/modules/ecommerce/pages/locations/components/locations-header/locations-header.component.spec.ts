import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocationsHeaderComponent } from './locations-header.component';

describe('LocationsHeaderComponent', () => {
  let component: LocationsHeaderComponent;
  let fixture: ComponentFixture<LocationsHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LocationsHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocationsHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
