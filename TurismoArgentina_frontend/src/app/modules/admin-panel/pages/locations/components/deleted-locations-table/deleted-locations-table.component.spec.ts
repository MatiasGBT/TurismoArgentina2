import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletedLocationsTableComponent } from './deleted-locations-table.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TranslateModule } from '@ngx-translate/core';

describe('DeletedLocationsTableComponent', () => {
  let component: DeletedLocationsTableComponent;
  let fixture: ComponentFixture<DeletedLocationsTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, TranslateModule.forRoot()],
      declarations: [ DeletedLocationsTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeletedLocationsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
