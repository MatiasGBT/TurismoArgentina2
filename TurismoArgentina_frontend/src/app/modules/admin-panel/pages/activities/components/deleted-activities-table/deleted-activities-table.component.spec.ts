import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletedActivitiesTableComponent } from './deleted-activities-table.component';
import { TranslateModule } from '@ngx-translate/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('DeletedActivitiesTableComponent', () => {
  let component: DeletedActivitiesTableComponent;
  let fixture: ComponentFixture<DeletedActivitiesTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, TranslateModule.forRoot()],
      declarations: [ DeletedActivitiesTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeletedActivitiesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
