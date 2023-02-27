import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvincesSectionComponent } from './provinces-section.component';

describe('ProvincesSectionComponent', () => {
  let component: ProvincesSectionComponent;
  let fixture: ComponentFixture<ProvincesSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProvincesSectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProvincesSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
