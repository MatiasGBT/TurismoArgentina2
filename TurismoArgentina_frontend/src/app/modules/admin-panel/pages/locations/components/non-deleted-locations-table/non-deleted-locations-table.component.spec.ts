import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NonDeletedLocationsTableComponent } from './non-deleted-locations-table.component';
import { TranslateModule } from '@ngx-translate/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('NonDeletedLocationsTableComponent', () => {
  let component: NonDeletedLocationsTableComponent;
  let fixture: ComponentFixture<NonDeletedLocationsTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, TranslateModule.forRoot()],
      declarations: [ NonDeletedLocationsTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NonDeletedLocationsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
