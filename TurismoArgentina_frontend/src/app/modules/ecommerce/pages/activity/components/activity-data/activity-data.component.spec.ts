import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityDataComponent } from './activity-data.component';

describe('ActivityDataComponent', () => {
  let component: ActivityDataComponent;
  let fixture: ComponentFixture<ActivityDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ], 
      declarations: [ ActivityDataComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivityDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
