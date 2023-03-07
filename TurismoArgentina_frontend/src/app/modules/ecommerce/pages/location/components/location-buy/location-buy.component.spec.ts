import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateModule } from '@ngx-translate/core';

import { LocationBuyComponent } from './location-buy.component';

describe('LocationBuyComponent', () => {
  let component: LocationBuyComponent;
  let fixture: ComponentFixture<LocationBuyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TranslateModule.forRoot()],
      declarations: [ LocationBuyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocationBuyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
