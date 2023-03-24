import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { ProvinceSelectorComponent } from './province-selector.component';

describe('ProvinceSelectorComponent', () => {
  let component: ProvinceSelectorComponent;
  let fixture: ComponentFixture<ProvinceSelectorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        TranslateModule.forRoot(),
        HttpClientTestingModule
      ],
      declarations: [ ProvinceSelectorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProvinceSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
