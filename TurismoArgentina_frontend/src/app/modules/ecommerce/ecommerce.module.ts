import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './pages/index/index.component';
import { SharedModule } from '../shared/shared.module';
import { ECommerceRoutingModule } from './ecommerce-routing.module';
import { HeaderComponent } from './pages/index/components/header/header.component';
import { ProvincesSectionComponent } from './pages/index/components/provinces-section/provinces-section.component';

@NgModule({
  declarations: [
    IndexComponent,
    HeaderComponent,
    ProvincesSectionComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ECommerceRoutingModule
  ]
})
export class ECommerceModule { }
