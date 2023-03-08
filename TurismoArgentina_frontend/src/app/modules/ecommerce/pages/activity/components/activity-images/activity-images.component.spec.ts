import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityImagesComponent } from './activity-images.component';

describe('ActivityImagesComponent', () => {
  let component: ActivityImagesComponent;
  let fixture: ComponentFixture<ActivityImagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActivityImagesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivityImagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
