import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './pages/index/index.component';
import { SharedModule } from '../shared/shared.module';
import { ECommerceRoutingModule } from './ecommerce-routing.module';
import { IndexHeaderComponent } from './pages/index/components/index-header/index-header.component';
import { LocationsSectionComponent } from './pages/index/components/locations-section/locations-section.component';
import { ActivitiesSectionComponent } from './pages/index/components/activities-section/activities-section.component';
import { ContactSectionComponent } from './pages/index/components/contact-section/contact-section.component';
import { ProvincesComponent } from './pages/provinces/provinces.component';
import { ParallaxDirective } from './directives/parallax.directive';
import { ProvincesHeaderComponent } from './pages/provinces/components/provinces-header/provinces-header.component';
import { ProvincesSectionComponent } from './pages/provinces/components/provinces-section/provinces-section.component';
import { SelectProvinceComponent } from './pages/provinces/components/provinces-section/select-province/select-province.component';
import { ProvincePaginationComponent } from './pages/provinces/components/provinces-section/province-pagination/province-pagination.component';

@NgModule({
  declarations: [
    IndexComponent,
    IndexHeaderComponent,
    LocationsSectionComponent,
    ActivitiesSectionComponent,
    ContactSectionComponent,
    ProvincesComponent,
    ParallaxDirective,
    ProvincesHeaderComponent,
    ProvincesSectionComponent,
    SelectProvinceComponent,
    ProvincePaginationComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ECommerceRoutingModule
  ]
})
export class ECommerceModule { }
