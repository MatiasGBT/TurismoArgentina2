import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './pages/index/index.component';
import { SharedModule } from '../shared/shared.module';
import { ECommerceRoutingModule } from './ecommerce-routing.module';
import { HeaderComponent } from './pages/index/components/header/header.component';

@NgModule({
  declarations: [
    IndexComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ECommerceRoutingModule
  ]
})
export class ECommerceModule { }
