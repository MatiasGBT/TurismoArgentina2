import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvincePaginationComponent } from './province-pagination.component';

describe('ProvincePaginationComponent', () => {
  let component: ProvincePaginationComponent;
  let fixture: ComponentFixture<ProvincePaginationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProvincePaginationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProvincePaginationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
