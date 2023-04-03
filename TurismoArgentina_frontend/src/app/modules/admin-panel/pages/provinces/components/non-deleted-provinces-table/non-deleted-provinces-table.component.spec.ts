import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NonDeletedProvincesTableComponent } from './non-deleted-provinces-table.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TranslateModule } from '@ngx-translate/core';

describe('NonDeletedProvincesTableComponent', () => {
  let component: NonDeletedProvincesTableComponent;
  let fixture: ComponentFixture<NonDeletedProvincesTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, TranslateModule.forRoot()],
      declarations: [ NonDeletedProvincesTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NonDeletedProvincesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
