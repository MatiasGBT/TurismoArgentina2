import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityHeaderComponent } from './activity-header.component';

describe('ActivityHeaderComponent', () => {
  let component: ActivityHeaderComponent;
  let fixture: ComponentFixture<ActivityHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActivityHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivityHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
