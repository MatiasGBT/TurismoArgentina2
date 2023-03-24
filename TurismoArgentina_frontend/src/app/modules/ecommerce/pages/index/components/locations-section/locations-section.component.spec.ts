import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { LocationsSectionComponent } from './locations-section.component';

describe('ProvincesSectionComponent', () => {
  let component: LocationsSectionComponent;
  let fixture: ComponentFixture<LocationsSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TranslateModule.forRoot(), HttpClientTestingModule],
      declarations: [ LocationsSectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocationsSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
