import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocationDataComponent } from './location-data.component';

describe('LocationDataComponent', () => {
  let component: LocationDataComponent;
  let fixture: ComponentFixture<LocationDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LocationDataComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocationDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
