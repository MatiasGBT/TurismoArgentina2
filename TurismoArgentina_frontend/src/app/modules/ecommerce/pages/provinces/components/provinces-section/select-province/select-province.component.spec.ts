import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectProvinceComponent } from './select-province.component';

describe('SelectProvinceComponent', () => {
  let component: SelectProvinceComponent;
  let fixture: ComponentFixture<SelectProvinceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectProvinceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelectProvinceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
