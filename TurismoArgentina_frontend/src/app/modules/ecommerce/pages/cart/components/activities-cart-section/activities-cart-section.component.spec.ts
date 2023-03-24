import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { ActivitiesCartSectionComponent } from './activities-cart-section.component';

describe('ActivitiesCartSectionComponent', () => {
  let component: ActivitiesCartSectionComponent;
  let fixture: ComponentFixture<ActivitiesCartSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TranslateModule.forRoot(), HttpClientTestingModule],
      declarations: [ ActivitiesCartSectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivitiesCartSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
