import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { ActivityBuyComponent } from './activity-buy.component';

describe('ActivityBuyComponent', () => {
  let component: ActivityBuyComponent;
  let fixture: ComponentFixture<ActivityBuyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TranslateModule.forRoot()],
      declarations: [ ActivityBuyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivityBuyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
