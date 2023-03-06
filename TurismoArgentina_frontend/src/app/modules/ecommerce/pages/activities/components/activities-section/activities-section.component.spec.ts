import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { ActivitiesPageSectionComponent } from './activities-section.component';

describe('ActivitiesPageSectionComponent', () => {
  let component: ActivitiesPageSectionComponent;
  let fixture: ComponentFixture<ActivitiesPageSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TranslateModule.forRoot()],
      declarations: [ ActivitiesPageSectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivitiesPageSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
