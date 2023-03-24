import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { IndexHeaderComponent } from './index-header.component';

describe('IndexHeaderComponent', () => {
  let component: IndexHeaderComponent;
  let fixture: ComponentFixture<IndexHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TranslateModule.forRoot(), HttpClientTestingModule],
      declarations: [ IndexHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IndexHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
