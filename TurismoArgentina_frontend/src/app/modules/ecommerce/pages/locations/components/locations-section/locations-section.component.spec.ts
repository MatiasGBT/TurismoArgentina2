import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { LocationsPageLocationsSectionComponent } from './locations-section.component';

describe('LocationsPageLocationsSectionComponent', () => {
  let component: LocationsPageLocationsSectionComponent;
  let fixture: ComponentFixture<LocationsPageLocationsSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TranslateModule.forRoot(), HttpClientTestingModule],
      declarations: [ LocationsPageLocationsSectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocationsPageLocationsSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
