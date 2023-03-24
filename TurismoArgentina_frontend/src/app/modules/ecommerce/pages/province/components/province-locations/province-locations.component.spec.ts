import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { ProvinceLocationsComponent } from './province-locations.component';

describe('ProvinceLocationsComponent', () => {
  let component: ProvinceLocationsComponent;
  let fixture: ComponentFixture<ProvinceLocationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TranslateModule.forRoot(), HttpClientTestingModule],
      declarations: [ ProvinceLocationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProvinceLocationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
