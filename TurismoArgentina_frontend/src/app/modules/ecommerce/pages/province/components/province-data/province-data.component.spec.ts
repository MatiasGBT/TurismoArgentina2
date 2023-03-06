import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvinceDataComponent } from './province-data.component';

describe('ProvinceDataComponent', () => {
  let component: ProvinceDataComponent;
  let fixture: ComponentFixture<ProvinceDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProvinceDataComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProvinceDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
