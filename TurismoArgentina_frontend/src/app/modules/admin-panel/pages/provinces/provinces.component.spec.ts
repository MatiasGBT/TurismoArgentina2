import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvincesComponent } from './provinces.component';
import { TranslateModule } from '@ngx-translate/core';

describe('ProvincesComponent', () => {
  let component: ProvincesComponent;
  let fixture: ComponentFixture<ProvincesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TranslateModule.forRoot()],
      declarations: [ ProvincesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProvincesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
