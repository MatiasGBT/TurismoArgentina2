import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { ActivitiesSectionComponent } from './activities-section.component';

describe('ActivitiesSectionComponent', () => {
  let component: ActivitiesSectionComponent;
  let fixture: ComponentFixture<ActivitiesSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TranslateModule.forRoot(), HttpClientTestingModule],
      declarations: [ ActivitiesSectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivitiesSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
