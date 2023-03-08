import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { BuyFormComponent } from './buy-form.component';

describe('BuyFormComponent', () => {
  let component: BuyFormComponent;
  let fixture: ComponentFixture<BuyFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TranslateModule.forRoot()],
      declarations: [ BuyFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
