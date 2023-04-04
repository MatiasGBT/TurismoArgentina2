import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NonDeletedActivitiesTableComponent } from './non-deleted-activities-table.component';
import { TranslateModule } from '@ngx-translate/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('NonDeletedActivitiesTableComponent', () => {
  let component: NonDeletedActivitiesTableComponent;
  let fixture: ComponentFixture<NonDeletedActivitiesTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, TranslateModule.forRoot()],
      declarations: [ NonDeletedActivitiesTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NonDeletedActivitiesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
