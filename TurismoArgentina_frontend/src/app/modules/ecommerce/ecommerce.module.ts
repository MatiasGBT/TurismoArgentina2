import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './pages/index/index.component';
import { SharedModule } from '../shared/shared.module';
import { ECommerceRoutingModule } from './ecommerce-routing.module';
import { HeaderComponent } from './pages/index/components/header/header.component';
import { LocationsSectionComponent } from './pages/index/components/locations-section/locations-section.component';
import { ActivitiesSectionComponent } from './pages/index/components/activities-section/activities-section.component';

@NgModule({
  declarations: [
    IndexComponent,
    HeaderComponent,
    LocationsSectionComponent,
    ActivitiesSectionComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ECommerceRoutingModule
  ]
})
export class ECommerceModule { }
