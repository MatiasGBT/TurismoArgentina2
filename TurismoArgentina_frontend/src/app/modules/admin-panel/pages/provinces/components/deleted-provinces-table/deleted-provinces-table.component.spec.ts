import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletedProvincesTableComponent } from './deleted-provinces-table.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TranslateModule } from '@ngx-translate/core';

describe('DeletedProvincesTableComponent', () => {
  let component: DeletedProvincesTableComponent;
  let fixture: ComponentFixture<DeletedProvincesTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, TranslateModule.forRoot()],
      declarations: [ DeletedProvincesTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeletedProvincesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
