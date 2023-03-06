import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvinceHeaderComponent } from './province-header.component';

describe('ProvinceHeaderComponent', () => {
  let component: ProvinceHeaderComponent;
  let fixture: ComponentFixture<ProvinceHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProvinceHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProvinceHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
