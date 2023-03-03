import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvincesHeaderComponent } from './provinces-header.component';

describe('ProvincesHeaderComponent', () => {
  let component: ProvincesHeaderComponent;
  let fixture: ComponentFixture<ProvincesHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProvincesHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProvincesHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
